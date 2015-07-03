package com.codenginebd.mobilemediacms;

import java.util.ArrayList;

import common.BaseActivity;

import PRTAndroidLib.PRTAndroidPrint;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PrinterDemoActivity extends BaseActivity {
	
	Button connectButton;
	PRTAndroidPrint mobilePrint;
	
	private OnClickListener clickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
			
			ArrayList<String> strList = mobilePrint.PRTGetBondedDevices();
			//Log.d("No Device Found", strList.size()+"");
			
			ShowDialog(PrinterDemoActivity.this,"Select Printer",new String[]{"Printer 1","Printer 2"});
			
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_demo);
        mobilePrint = new PRTAndroidPrint();
        boolean bRes = mobilePrint.PRTInitLib();
        connectButton = (Button)findViewById(R.id.connectButton);
        connectButton.setOnClickListener(clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_printer_demo, menu);
        return true;
    }
}
