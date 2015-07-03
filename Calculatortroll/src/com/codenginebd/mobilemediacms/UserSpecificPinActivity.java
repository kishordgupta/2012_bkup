package com.codenginebd.mobilemediacms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class UserSpecificPinActivity extends Activity {

	private void SetTextToTextView(int id,String text)
	{
		((TextView)findViewById(id)).setText(text);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_specific_pin);
        if(getIntent() != null)
        {
        	Intent i = getIntent();
        	String marchantName = i.getStringExtra("vMerchantName");
        	String vMarchantNo = i.getStringExtra("vMerchantNo");
        	long eWalletValue = i.getLongExtra("wallet",0);
        	String monthlySale = i.getStringExtra("monthlySale");
        	SetTextToTextView(R.id.merchantName, "Merchant Name:"+marchantName);
        	SetTextToTextView(R.id.virtualMerchantNo, "Virtual Merchant No: "+vMarchantNo);
        	SetTextToTextView(R.id.eWalletValue, "E Wallet Value: "+eWalletValue);
        	SetTextToTextView(R.id.monthlySale, "Monthly Sale: "+monthlySale);        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_specific_pin, menu);
        return true;
    }
}
