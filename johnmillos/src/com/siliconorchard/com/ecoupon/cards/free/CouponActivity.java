package com.siliconorchard.com.ecoupon.cards.free;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Currency;

import com.model.Instancevalues;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class CouponActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupon);
		setAsWallPaper();
	}

	private File setAsWallPaper() {
		// TODO Auto-generated method stub
		// MainActivity.ratio=viewPager.getCurrentItem();
		// startActivityForResult(new Intent(getApplicationContext(),
		// Eye1Activity.class),1);

		getActionBar().setHomeButtonEnabled(true);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.coupon);
		Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		bitmap = getPoster(mutableBitmap);
		ImageView img = (ImageView) findViewById(R.id.imgview);
		img.setImageBitmap(bitmap);
		Calendar cal = Calendar.getInstance();

		File dir = Environment.getExternalStorageDirectory();
		File output = new File(dir, cal.getTime().toString().replace(".", "")
				+ "_savings2phone.jpg");
		FileOutputStream os = null;

		try {
			os = new FileOutputStream(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (os != null && bitmap != null) {
			bitmap.compress(CompressFormat.JPEG, 100, os);
		}
		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coup, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Bitmap getPoster(Bitmap background) {

		Canvas canvas = new Canvas(background);
		String THE_QUOTE = Instancevalues.currentvendor.toUpperCase();
		// // Typeface font = new CouponActivity()
		// font = Typeface.create(font, Typeface.BOLD);
		Paint paint = new Paint();
		// paint.setTypeface(font);
		paint.setAntiAlias(true);
		paint.setColor(Color.GRAY);
		paint.setStyle(Style.FILL);
		paint.setShadowLayer(2.0f, 1.0f, 1.0f, Color.BLACK);
		float fontSize = 15;// getFontSize(background.getWidth(), THE_QUOTE,
							// paint); //You'll have to define a way to find a
							// size that fits, or just use a constant size.
		paint.setTextSize(fontSize);
		String texts = Instancevalues.currentvendor.toUpperCase();
		paint.setTextSize(20);
		
		Paint paint1 = new Paint();
		// paint.setTypeface(font);
		paint1.setAntiAlias(true);
		paint1.setColor(Color.RED);
		paint1.setStyle(Style.FILL);
		paint1.setShadowLayer(2.0f, 1.0f, 1.0f, Color.BLACK);
	//	float fontSize = 15;// getFontSize(background.getWidth(), THE_QUOTE,
							// paint); //You'll have to define a way to find a
							// size that fits, or just use a constant size.
		paint.setTextSize(fontSize);
	//	String texts = Instancevalues.currentvendor.toUpperCase();
		paint1.setTextSize(20);

		canvas.drawText("Savings2phone", (background.getWidth()   - paint.measureText("Savings2phone"))/2,
				background.getHeight() / 2, paint);
		canvas.drawText(
				"Code: XXXX-XXXX-XX-XXXX"
						+ " ",
				(background.getWidth() - paint1.measureText("Code: XXXX-XXXX-XX-XXXX"))/2,
				background.getHeight() / 2 - 50, paint1);
		canvas.drawText(THE_QUOTE,
				(background.getWidth() - paint.measureText(THE_QUOTE)) / 2,
				background.getHeight() / 2 + 30, paint); // You might want to do
															// something
															// different. In my
															// case every image
															// has a filler in
															// the bottom which
															// is 50px.
		return background;
	}
}
