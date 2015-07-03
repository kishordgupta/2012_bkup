package com.lilait.tamago.xmass.xmasstree.cristmasstree.kidz.tap.game;



import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.link.RevMobLink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	Button wall;
	ViewPager viewPager = null;
	 private RevMobLink link;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

   viewPager = (ViewPager) findViewById(R.id.view_pager);
    ImagePagerAdapter adapter = new ImagePagerAdapter();
    viewPager.setAdapter(adapter);

	wall = (Button) findViewById(R.id.wall);
	wall .setOnClickListener(new ListenerOnClick());
	
	RevMob revmob = RevMob.start(this,"5291d12a5cf3a75903000031");
    RevMobAdsListener listener = new RevMobAdsListener() {
        public void onRevMobAdReceived() {  }
        public void onRevMobAdNotReceived(String message) {} // you can hide the More Games Button here
        public void onRevMobAdDisplayed() {}
        public void onRevMobAdDismiss() {}
        public void onRevMobAdClicked() {}
    };
    link = revmob.createAdLink(this, listener);

    Button buttonMoreGames=(Button)findViewById(R.id.img_btn_image_pager_set_as_wp); // instantiate it
    buttonMoreGames.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            link.open();
        }
    });
  }
  protected class ListenerOnClick implements OnClickListener
	{
		

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.wall)
			{
				setAsWallPaper();
			}
			
		}

		
		
		
	}
  
  private void setAsWallPaper() {
		// TODO Auto-generated method stub
		MainActivity.ratio=viewPager.getCurrentItem();
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
	}

  private class ImagePagerAdapter extends PagerAdapter {
    private int[] mImages = new int[] {
        R.drawable.a1,
        
       
    };

    @Override
    public int getCount() {
      return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      Context context = FirstActivity.this;
      ImageView imageView = new ImageView(context);
      int padding = context.getResources().getDimensionPixelSize(
          R.dimen.padding_medium);
      imageView.setPadding(padding, padding, padding, padding);
      imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      imageView.setImageResource(mImages[position]);
      ((ViewPager) container).addView(imageView, 0);
      return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      ((ViewPager) container).removeView((ImageView) object);
    }
  }
}