package com.demo.pagol;


import java.io.File;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import android.os.Bundle;
import android.os.Environment;

import android.app.ListActivity;


import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FileChooserActivity extends ListActivity {
	
	private File currentDir;
	private FileArrayAdapter adapter;
	
	Stack<File> dirStack = new Stack<File>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		File sdCard = Environment.getExternalStorageDirectory();
		//currentDir = new File("/sdcard/");
		currentDir = sdCard;
		fill(currentDir);
		

	}
	
	
	private void fill(File file){
		File[] dirs = file.listFiles();
		this.setTitle("Current Dir: "+file.getName());
		List<Option> dir = new ArrayList<Option>();
		List<Option> fls = new ArrayList<Option>();
		try{
			for(File tempFile: dirs){
				if(tempFile.isDirectory())
					dir.add(new Option(tempFile.getName(), "Folder", tempFile.getAbsolutePath()));
				else
					fls.add(new Option(tempFile.getName(), "File Size: "+ tempFile.length()+" Bytes", tempFile.getAbsolutePath()));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if(!file.getName().contains("sdcard"))
			dir.add(0, new Option("..", "Parent Directory", file.getParent()));
		adapter = new FileArrayAdapter(getApplicationContext(), R.layout.file_view, dir);
		this.setListAdapter(adapter);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option option = adapter.getItem(position);
		if(option.getData().equalsIgnoreCase("folder")){
			dirStack.push(currentDir);
			currentDir = new File(option.getPath());
			fill(currentDir);
		}
		else if( option.getData().equalsIgnoreCase("Parent Directory")){
			currentDir = dirStack.pop();
			fill(currentDir);
		}
		else {
			onFileClick(option);
		}
	}
	private void onFileClick(Option o){
    	Toast.makeText(this, "File Clicked: "+o.getPath(), Toast.LENGTH_SHORT).show();
    	// Do your job here.
    }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		if(dirStack.size()==0){
			 finish();
			 super.onBackPressed();
			//Toast.makeText(this, "Stack empty: ", Toast.LENGTH_SHORT).show();
			return;
		}
		currentDir = dirStack.pop();
		fill(currentDir);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/

}
