package com.kd.mobilemediacms;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.kd.helper.Datasync;
import common.GetNetworkStatus;

public class Menuitem {

public static  Context con;
static AlertDialog progressDialog;

public static boolean onMenuItemSelected(int id,Context context) {
	// TODO Auto-generated method stub
	

	switch (id) 
	 {
        case R.id.Update:
        	   
 		   if(GetNetworkStatus.isNetworkAvailable(context)){
 	           Datasync.servicestar();
 	          final ProgressDialog  dialog = new ProgressDialog(context); 
              //���ý�������񣬷��ΪԲ�Σ���ת�� 
              dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
              //����ProgressDialog ���� 
              dialog.setTitle(R.string.ProgressDialog); 
              //����ProgressDialog ��ʾ��Ϣ 
              dialog.setMessage("NetWork available, Sync with server DB please wait"); 
              //����ProgressDialog ����ͼ�� 
              dialog.setIcon(android.R.drawable.ic_dialog_map); 
              //����ProgressDialog ��һ��Button 
              //dialog.setButton("ȷ��", new DialogInterface.OnClickListener(){ 
               // @Override 
                //public void onClick(DialogInterface dialog, int which) { 
                     
                //} 
            //}); 
              //����ProgressDialog �Ľ������Ƿ���ȷ 
              dialog.setIndeterminate(false); 
              //����ProgressDialog �Ƿ���԰��˻ذ���ȡ�� 
              dialog.setCancelable(true); 
              //��ʾ 
              dialog.show();
              new Thread()  
              {  int m_count=0;
                 public void run()  
                 {  
                   try 
                   {  
                       while (m_count <= 100)  
                       {  
                          // ���߳������ƽ��ȡ�  
      	                dialog.setProgress(m_count++);  
                          Thread.sleep(100);  
                       }  
                       dialog.cancel();  
                      
                   }  
                   catch (InterruptedException e)  
                   {  
                  	 dialog.cancel();  
                   }  
                 }  
              }.start();  

        
 	     //       ShowMessage("NetWork available, Sync with server DB please wait");
 	        }
 	        else
 	        {   Toast.makeText(context, "NetWork unavailable, Sync with server DB failed",Toast.LENGTH_LONG).show();
 	        	// ShowMessage("NetWork unavailable, Sync with server DB failed");
 	       Datasync.offline(con);
 	        }
        	
            return true;
        case R.id.Settings:
        	
            return true;
        case R.id.Help:
        	
	           Toast.makeText(context, "update",Toast.LENGTH_LONG).show();
	            return true;
	        case R.id.Contact:
	        	
	            return true;
       
    }
	return false;
	
}
}


