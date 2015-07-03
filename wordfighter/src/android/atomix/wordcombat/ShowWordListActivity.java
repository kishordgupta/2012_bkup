package android.atomix.wordcombat;

import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.atomix.wordcombat.adapter.CustomBaseAdapter;
import android.atomix.wordcombat.database.DataBaseUtil;
import android.atomix.wordcombat.supports.ConstantValues;
import android.atomix.wordcombat.supports.WordCombatApp;
import android.atomix.wordcombat.supports.WordInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class ShowWordListActivity extends Activity implements OnClickListener {

	private DataBaseUtil dataBaseUtil;
	private ListView lstViewAllWord;
	private ProgressDialog progressDialog;
	private Button btnBack, btnArchive;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_word_list);
		
		lstViewAllWord = (ListView) findViewById(R.id.lst_view_show_word);
		
		btnBack = (Button) findViewById(R.id.btn_back_word_list);
		btnBack.setOnClickListener(this);
		
		btnArchive = (Button) findViewById(R.id.btn_archive_word_list);
		btnArchive.setOnClickListener(this);
		
		GetBookBoucket task = new GetBookBoucket();
		progressDialog = ProgressDialog.show(ShowWordListActivity.this, "Loading", "Please wait...", true, false);
		task.execute();
	}
	
	public class GetBookBoucket extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) 
		{
			dataBaseUtil = new DataBaseUtil(getApplicationContext());
			dataBaseUtil.open();
			dataBaseUtil.fetchAllWordNameWithMeaning();
			dataBaseUtil.close();
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) 
		{
			super.onPostExecute(result);
			if(progressDialog.isShowing())
				progressDialog.dismiss();
			Collections.reverse(WordCombatApp.getInstance().getWordInfosList());
			Collections.sort(WordCombatApp.getInstance().getWordInfosList(), new LanguageComparator());
			if(WordCombatApp.getInstance().getWordInfosList().size() != 0)
			{
				lstViewAllWord.setAdapter(new CustomBaseAdapter(getApplicationContext(), WordCombatApp.getInstance().getWordInfosList()));
			}
			
			else
			{
				lstViewAllWord.setAdapter(null);
			}
		}
		
	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		ConstantValues.archive = "25";
		finish();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		finish();
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_back_word_list:
				ConstantValues.archive = "25";
				onBackPressed();
				finish();
				break;
				
			case R.id.btn_archive_word_list:
				ConstantValues.archive = null;
				finish();
				startActivity(getIntent());
				break;
	
			default:
				break;
		}
	}
	
	public class LanguageComparator implements Comparator<WordInfo> 
	{
		@Override
		public int compare(WordInfo lhs, WordInfo rhs) 
		{
			return lhs.getLanguageName().compareTo(rhs.getLanguageName());
		}
	}
}
