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
              //设置进度条风格，风格为圆形，旋转的 
              dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
              //设置ProgressDialog 标题 
              dialog.setTitle(R.string.ProgressDialog); 
              //设置ProgressDialog 提示信息 
              dialog.setMessage("NetWork available, Sync with server DB please wait"); 
              //设置ProgressDialog 标题图标 
              dialog.setIcon(android.R.drawable.ic_dialog_map); 
              //设置ProgressDialog 的一个Button 
              //dialog.setButton("确定", new DialogInterface.OnClickListener(){ 
               // @Override 
                //public void onClick(DialogInterface dialog, int which) { 
                     
                //} 
            //}); 
              //设置ProgressDialog 的进度条是否不明确 
              dialog.setIndeterminate(false); 
              //设置ProgressDialog 是否可以按退回按键取消 
              dialog.setCancelable(true); 
              //显示 
              dialog.show();
              new Thread()  
              {  int m_count=0;
                 public void run()  
                 {  
                   try 
                   {  
                       while (m_count <= 100)  
                       {  
                          // 由线程来控制进度。  
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


