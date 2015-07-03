package com.codenginebd.mobilemediacms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VendorItemActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vendor_item, menu);
        return true;
    }
}
