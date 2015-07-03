package com.Fakeposter.fakeposter;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.util.Log;

public class gallaryscan implements MediaScannerConnectionClient {

	
	private MediaScannerConnection mMs;
    private File mFile;
    Context con;
    Uri uri;
    String path;
    
    
	public gallaryscan(Context context, File f) {
		con=context;
		mFile = f;
        mMs = new MediaScannerConnection(context, this);
        mMs.connect();
	}

	@Override
	public void onMediaScannerConnected() {
		// TODO Auto-generated method stub
		mMs.scanFile(mFile.getAbsolutePath(), null);
	}
	Intent intent;
	@Override
	public void onScanCompleted(String path, Uri uri) {
		// TODO Auto-generated method stub
		this.uri=uri;
		this.path=path;
		 Log.d("onScanCompleted",uri.toString() + "success"+mMs);
		  intent = new Intent(Intent.ACTION_VIEW,null);
         intent.setData(uri);
         this.uri=uri;
         con.startActivity(intent);
         mMs.disconnect();
	}

}
