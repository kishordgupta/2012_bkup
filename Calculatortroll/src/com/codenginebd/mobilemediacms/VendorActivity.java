package com.codenginebd.mobilemediacms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VendorActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vendor, menu);
        return true;
    }
}
