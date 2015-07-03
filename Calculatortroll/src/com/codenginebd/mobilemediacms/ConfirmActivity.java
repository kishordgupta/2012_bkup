package com.codenginebd.mobilemediacms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ConfirmActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_confirm, menu);
        return true;
    }
}
