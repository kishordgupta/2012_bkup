package com.workforwellbeing.wow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class Firstactivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://www.workonwellbeing.com"));
		startActivity(browserIntent);
		finish();

	}

}
