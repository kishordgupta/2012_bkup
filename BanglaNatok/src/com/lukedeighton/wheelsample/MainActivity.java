package com.lukedeighton.wheelsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.bangla.natok.prova.MainActivity2;
import com.bangla.natok.prova.R;
import com.bangla.natok.prova.act.Hasanmasudnatok;
import com.bangla.natok.prova.act.Oldnatok;
import com.bangla.natok.prova.act.humayonahmednatok;
import com.bangla.natok.prova.act.mehjabinnatok;
import com.bangla.natok.prova.act.mosarofjorimnatok;
import com.bangla.natok.prova.act.opurbonatok;
import com.bangla.natok.prova.act.tishanatok;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements OnClickListener {
    public static Context c = null;
    private static final int ITEM_COUNT = 6;
    Button ne=null;
    Button old=null;
    Button hua=null;
    Button hm=null;
    Button mosa=null;
    Button ts=null;
    Button meh=null;
    Button opu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        ne=(Button)findViewById(R.id.latest);
        old=(Button)findViewById(R.id.old);
        hua=(Button)findViewById(R.id.huah);
        hm=(Button)findViewById(R.id.har);
        mosa=(Button)findViewById(R.id.mkar);
        ts=(Button)findViewById(R.id.tisa);
        meh=(Button)findViewById(R.id.mehja);
        opu=(Button)findViewById(R.id.opurbo);
        ne.setOnClickListener(this);
        old.setOnClickListener(this);
        hua.setOnClickListener(this);
        hm.setOnClickListener(this);
        mosa.setOnClickListener(this);
        ts.setOnClickListener(this);
        meh.setOnClickListener(this);
        opu.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.latest:
			startActivity(new Intent(getApplicationContext(), MainActivity2.class));
			break;
		case R.id.old:
			startActivity(new Intent(getApplicationContext(), Oldnatok.class));
			break;
		case R.id.huah:
			startActivity(new Intent(getApplicationContext(), humayonahmednatok.class));
			break;
		case R.id.har:
			startActivity(new Intent(getApplicationContext(), Hasanmasudnatok.class));
			break;
		case R.id.mkar:
			startActivity(new Intent(getApplicationContext(), mosarofjorimnatok.class));
			break;
		case R.id.tisa:
			startActivity(new Intent(getApplicationContext(), tishanatok.class));
			break;
		case R.id.mehja:
			startActivity(new Intent(getApplicationContext(), mehjabinnatok.class));
			break;
		case R.id.opurbo:
			startActivity(new Intent(getApplicationContext(), opurbonatok.class));
			break;
		default:
			break;
		}
	}
}