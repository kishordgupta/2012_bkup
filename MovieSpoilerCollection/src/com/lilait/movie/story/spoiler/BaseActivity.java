package com.lilait.movie.story.spoiler;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity 
{
	protected void SavePreferences(String key, boolean isChecked){
		
	    SharedPreferences sharedPreferences = this.getSharedPreferences("prefs", 1);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putBoolean(key, isChecked);
	    editor.commit();
	   }
	protected Boolean LoadPreferences(String key){
		    SharedPreferences sharedPreferences = this.getSharedPreferences("prefs", 1);
		    return  sharedPreferences.getBoolean(key, false);
		    
		    
		   }
	protected void ShowDialog(Context context,String title,String[] items)
	{	
		final Dialog dialog;
		dialog = new Dialog(context);
		ListView modeList = new ListView(this);
		String[] stringArray = items;
		final ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		modeList.setAdapter(modeAdapter);
		dialog.setContentView(modeList);
		dialog.setTitle(title);
		dialog.setCancelable(true);
		modeList.setOnItemClickListener(new OnItemClickListener() {

			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long id) 
			{
				// TODO Auto-generated method stub
			
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	protected String GetTextFromEditText(int id)
	{
		return ((EditText)findViewById(id)).getText().toString();
	}
	protected void SetcheckToCheck(int id,Boolean text)
	{
		((CheckBox) findViewById(id)).setChecked(text);
	}
	protected void SetTextToTextView(int id,String text)
	{
		((TextView) findViewById(id)).setText(text);
	}
	protected void ShowMessage(String msg)
	{
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}
