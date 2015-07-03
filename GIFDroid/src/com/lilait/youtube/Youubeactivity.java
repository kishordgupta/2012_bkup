package com.lilait.youtube;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.quackware.gifdroid.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Youubeactivity extends Activity {
	public  static String url="";
    private class ReceivingDataFromYoutube extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog = new ProgressDialog(Youubeactivity.this);
        private String result;
		

        protected void onPreExecute() {
            dialog.setMessage("Downloading...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
        	 int begin, end;
             String tmpstr = null;
             try {
                 URL url=new URL(Youubeactivity.url);
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
                 con.setRequestMethod("GET");
                 InputStream stream=con.getInputStream();
                 InputStreamReader reader=new InputStreamReader(stream);
                 StringBuffer buffer=new StringBuffer();
                 char[] buf=new char[262144];                
                 int chars_read;
                 while ((chars_read = reader.read(buf, 0, 262144)) != -1) {
                     buffer.append(buf, 0, chars_read);
                 }
                 tmpstr=buffer.toString();

                 begin  = tmpstr.indexOf("url_encoded_fmt_stream_map=");
                 end = tmpstr.indexOf("&", begin + 27);
                 if (end == -1) {
                     end = tmpstr.indexOf("\"", begin + 27);
                 }
                 tmpstr = Uri.decode(tmpstr.substring(begin + 27, end));

             } catch (MalformedURLException e) {
                 throw new RuntimeException();
             } catch (IOException e) {
                 throw new RuntimeException();
             }

             Vector url_encoded_fmt_stream_map = new Vector();
             begin = 0;
             end   = tmpstr.indexOf(",");

             while (end != -1) {
                 url_encoded_fmt_stream_map.addElement(tmpstr.substring(begin, end));
                 begin = end + 1;
                 end   = tmpstr.indexOf(",", begin);
             }

             url_encoded_fmt_stream_map.addElement(tmpstr.substring(begin, tmpstr.length()));
             String result = "";
             Enumeration url_encoded_fmt_stream_map_enum = url_encoded_fmt_stream_map.elements();
             while (url_encoded_fmt_stream_map_enum.hasMoreElements()) {
                 tmpstr = (String)url_encoded_fmt_stream_map_enum.nextElement();
                 begin = tmpstr.indexOf("itag=");
                 if (begin != -1) {
                     end = tmpstr.indexOf("&", begin + 5);

                     if (end == -1) {
                           end = tmpstr.length();
                     }

                     int fmt = Integer.parseInt(tmpstr.substring(begin + 5, end));

                     if (fmt == 35) {
                         begin = tmpstr.indexOf("url=");
                         if (begin != -1) {
                             end = tmpstr.indexOf("&", begin + 4);
                             if (end == -1) {
                                end = tmpstr.length();
                             }
                             result = Uri.decode(tmpstr.substring(begin + 4, end));
                             this.result=result;
                             break;
                         }
                     }
                 }
             }         
             try {
               URL u = new URL(result);
               HttpURLConnection c = (HttpURLConnection) u.openConnection();
               c.setRequestMethod("GET");
 /*              c.setRequestProperty("Youtubedl-no-compression", "True");
               c.setRequestProperty("User-Agent", "YouTube");*/

               c.setDoOutput(true);
               c.connect();
               String fileName = "FILE.mp4";
	              String storagePath = Environment.getExternalStorageDirectory().toString();
	              File ofo = new File(storagePath,fileName);

               FileOutputStream f=new FileOutputStream(ofo);

               InputStream in=c.getInputStream();
               byte[] buffer=new byte[1024];
               int sz = 0;
               while ( (sz = in.read(buffer)) > 0 ) {
                    f.write(buffer,0, sz);
               }
               f.close();
             } catch (MalformedURLException e) {
                 new RuntimeException();
             } catch (IOException e) {
                 new RuntimeException();
             }
             return null;
        	/*URL u = null;
        	InputStream is = null;  

        	     try {
        	              u = new URL(Youubeactivity.url);
        	              is = u.openStream(); 
        	              HttpURLConnection huc = (HttpURLConnection)u.openConnection();//to know the size of video
        	              int size = huc.getContentLength();                 

        	          if(huc != null){
        	              String fileName = "FILE.mp4";
        	              String storagePath = Environment.getExternalStorageDirectory().toString();
        	              File f = new File(storagePath,fileName);

        	              FileOutputStream fos = new FileOutputStream(f);
        	              byte[] buffer = new byte[1024];
        	              int len1 = 0;
        	              if(is != null){
        	                 while ((len1 = is.read(buffer)) > 0) {
        	                       fos.write(buffer,0, len1);   
        	                 }
        	              }
        	              if(fos != null){
        	                 fos.close();
        	              }
        	          }                     
        	     }catch (MalformedURLException mue) {
        	            mue.printStackTrace();
        	     } catch (IOException ioe) {
        	            ioe.printStackTrace();
        	    } finally {
        	               try {                
        	                 if(is != null){
        	                   is.close();
        	                 }
        	               }catch (IOException ioe) {
        	                     // just going to ignore this one
        	               }
        	    }
				return null;*/
        }

        protected void onPostExecute(Void unused) {
            dialog.dismiss();
        }    

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final EditText e =(EditText)findViewById(R.id.bank);
        Button sButton = (Button)findViewById(R.id.search);
		sButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id="";
				String output =e.getText().toString();
	            //Imageurl.newyearsvalues=(ArrayList<String>) output;
	            String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

	            Pattern compiledPattern = Pattern.compile(pattern);
	            Matcher matcher = compiledPattern.matcher(output);

	            if(matcher.find()){
	            	id= matcher.group();
	            	url="http://www.youtube.com/watch?v="+id;
	            	 new ReceivingDataFromYoutube().execute();
	            }
	            else
	            {
	            	Toast.makeText(getApplicationContext(), "Not a valid youtubeurl", Toast.LENGTH_LONG).show();
	            }
			}
		});
       
    }   
}  