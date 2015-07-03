package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codenginebd.mobilemediacms.R;
import com.codenginebd.mobilemediacms.RegistrationActivity;

import common.Constants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

public class AsyncTaskLoader extends AsyncTask<String, Void, JSONArray>
{

	private Activity caller;
	private int elementId;
	private String url;
	Map<String,String> nameValuePairs;
	int actionType;
	private ProgressDialog progressDialog;
	
	public AsyncTaskLoader(String url,Activity container,int displayElementId,Map<String,String> nameVaulePairs,int actionType)
	{
		caller = container;
		elementId = displayElementId;
		this.url = url;
		this.nameValuePairs = nameVaulePairs;
		this.actionType = actionType;
		progressDialog = new ProgressDialog(container);
	}
	
	private List<NameValuePair> GenerateNameValuePair()
	{
		if(nameValuePairs != null && !nameValuePairs.isEmpty())
		{
			ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>(nameValuePairs.size());
			String k, v;Iterator<String> itKeys = nameValuePairs.keySet().iterator();
			while (itKeys.hasNext()) 
			{
				k = itKeys.next();
				v = nameValuePairs.get(k);
				pairs.add(new BasicNameValuePair(k, v));
			}
			return pairs;
		}
		return null;
		
			
	}
	
	protected void onPreExecute() 
	{
		progressDialog.setMessage("Communicating with the server...");
		progressDialog.setCancelable(false);
		progressDialog.show();
    }
	
	@Override
	protected JSONArray doInBackground(String... params) {
		// TODO Auto-generated method stub
		ServiceProvider service = new ServiceProvider();
		JSONArray jArray = service.Insert(url, GenerateNameValuePair());
		return jArray;
	}
	
	protected void onPostExecute(JSONArray result) {
	      Log.d("The Result is: ", "");
	      //((EditText)caller.findViewById(elementId)).setText(result);
	      JSONArray jArray = result;
	      JSONObject json_data=null;
	      Log.d("The Success Result is: ", result.toString());
	      progressDialog.dismiss();
	      if(jArray != null)
			{
				for(int i = 0 ; i < jArray.length() ; i++)
				{
					try {
						json_data = jArray.getJSONObject(i);
						
						if(actionType == Constants.Actions.LOGIN)
						{
							
						}
						else if(actionType == Constants.Actions.REGISTRATION)
						{
							String merchantName = json_data.getString("merchantName");
							String merchantNo = json_data.getString("vMerchantNo");
							long wallet = json_data.getLong("agentWallet");
							String pin = json_data.getString("userPin");
							String downloadUrl = json_data.getString("downloadUrl");
							((RegistrationActivity)caller).CallOnPostAsyncTaskExecute(merchantName,merchantNo, wallet, pin, downloadUrl);
						}
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	    }
	
}
