package com.siliconorchard.ephesustravelapp;


import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import player.DBHelper;


import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.atomix.kurowiz.supports.ConstantValues;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.siliconorchard.ephesustravelapp.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	Button button=null;
	private AutoScrollViewPager pager;

	private DisplayImageOptions options;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       String s="slider/slide";
        String[] imageUrls = new String[]{s+"1.jpg",s+"2.jpg",s+"3.jpg",s+"4.jpg",s+"5.jpg"};
		int pagerPosition = 0;

		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.cacheInMemory(true)
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.build();
        ImagePagerAdapter im = new ImagePagerAdapter(imageUrls);
		pager = (AutoScrollViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(im);
		pager.setCurrentItem(pagerPosition);
		pager.setInterval(2000);
		pager.startAutoScroll();
        
		  Button lear = (Button)rootView.findViewById(R.id.learnonelphenus);
		    lear.setOnClickListener(new OnClickListener() {
				
				@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					 ConstantValues.CategoryName  =ConstantValues.Learn_on_Ephesus;
				ConstantValues.citynamelist.clear();
				ConstantValues.citynamelist.addAll(DBHelper
						.getfavouriteset(ConstantValues.Learn_on_Ephesus));

				MainActivity.navDrawerItems.clear();
				MainActivity.navDrawerItems.add(new NavDrawerItem("Main Page",
						MainActivity.navMenuIcons.getResourceId(0, -1)));
			
				if (ConstantValues.citynamelist != null) {
					for (int i = 0; i < ConstantValues.citynamelist.size(); i++) {
						MainActivity.navDrawerItems.add(new NavDrawerItem(
								ConstantValues.citynamelist.get(i).Cityname,
								MainActivity.navMenuIcons.getResourceId(1, -1),
								ConstantValues.citynamelist.get(i).Cityname));

					}

					MainActivity.adapter.notifyDataSetChanged();
				}
				LinearLayout mDrawer = (LinearLayout) getActivity()
						.findViewById(R.id.list);
				DrawerLayout mDrawerLayout = (DrawerLayout) getActivity()
						.findViewById(R.id.drawer_layout);
				mDrawerLayout.openDrawer(mDrawer);
			}
			});
		    
		    Button attra = (Button)rootView.findViewById(R.id.learnonatractions);
		    attra.setOnClickListener(new OnClickListener() {
				
				@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					 ConstantValues.CategoryName  =ConstantValues.attractions_around_ephesus;
				ConstantValues.citynamelist.clear();
				ConstantValues.citynamelist.addAll(DBHelper
						.getfavouriteset(ConstantValues.attractions_around_ephesus));

				MainActivity.navDrawerItems.clear();
				MainActivity.navDrawerItems.add(new NavDrawerItem("Main Page",
						MainActivity.navMenuIcons.getResourceId(0, -1)));
		
				if (ConstantValues.citynamelist != null) {
					for (int i = 0; i < ConstantValues.citynamelist.size(); i++) {
						MainActivity.navDrawerItems.add(new NavDrawerItem(
								ConstantValues.citynamelist.get(i).Cityname,
								MainActivity.navMenuIcons.getResourceId(1, -1),
								ConstantValues.citynamelist.get(i).Cityname));

					}

					MainActivity.adapter.notifyDataSetChanged();
				}
				LinearLayout mDrawer = (LinearLayout) getActivity()
						.findViewById(R.id.list);
				DrawerLayout mDrawerLayout = (DrawerLayout) getActivity()
						.findViewById(R.id.drawer_layout);
				mDrawerLayout.openDrawer(mDrawer);
			}
			});
		    
		    Button tour = (Button)rootView.findViewById(R.id.tourstoephesus);
		    tour.setOnClickListener(new OnClickListener() {
				
				@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					 ConstantValues.CategoryName  =ConstantValues.tours_to_ephesus;
				ConstantValues.citynamelist.clear();
				ConstantValues.citynamelist.addAll(DBHelper
						.getfavouriteset(ConstantValues.tours_to_ephesus));

				MainActivity.navDrawerItems.clear();
				MainActivity.navDrawerItems.add(new NavDrawerItem("Main Page",
						MainActivity.navMenuIcons.getResourceId(0, -1)));
		
				if (ConstantValues.citynamelist != null) {
					for (int i = 0; i < ConstantValues.citynamelist.size(); i++) {
						MainActivity.navDrawerItems.add(new NavDrawerItem(
								ConstantValues.citynamelist.get(i).Cityname,
								MainActivity.navMenuIcons.getResourceId(1, -1),
								ConstantValues.citynamelist.get(i).Cityname));

					}

					MainActivity.adapter.notifyDataSetChanged();
				}
				LinearLayout mDrawer = (LinearLayout) getActivity()
						.findViewById(R.id.list);
				DrawerLayout mDrawerLayout = (DrawerLayout) getActivity()
						.findViewById(R.id.drawer_layout);
				mDrawerLayout.openDrawer(mDrawer);
			}
			});
       
		    Button sa = (Button)rootView.findViewById(R.id.specialarticles);
		    sa.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 ConstantValues.CategoryName  =ConstantValues.special_articles;
					ConstantValues.citynamelist.clear();
					ConstantValues.citynamelist.addAll(DBHelper
							.getfavouriteset(ConstantValues.special_articles));

					MainActivity.navDrawerItems.clear();
					MainActivity.navDrawerItems.add(new NavDrawerItem("Main Page",
							MainActivity.navMenuIcons.getResourceId(0, -1)));
				
					if (ConstantValues.citynamelist != null) {
						for (int i = 0; i < ConstantValues.citynamelist.size(); i++) {
							MainActivity.navDrawerItems.add(new NavDrawerItem(
									ConstantValues.citynamelist.get(i).Cityname,
									MainActivity.navMenuIcons.getResourceId(1, -1),
									ConstantValues.citynamelist.get(i).Cityname));

						}

						MainActivity.adapter.notifyDataSetChanged();
					}
					LinearLayout mDrawer = (LinearLayout) getActivity()
							.findViewById(R.id.list);
					DrawerLayout mDrawerLayout = (DrawerLayout) getActivity()
							.findViewById(R.id.drawer_layout);
					mDrawerLayout.openDrawer(mDrawer);
				
				}
			});
		    
		    Button daa = (Button)rootView.findViewById(R.id.dealsarroundephesus);
		    daa.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 ConstantValues.CategoryName  =	ConstantValues.deals_and_discounts_around_ephesus;
					ConstantValues.citynamelist.clear();
					ConstantValues.citynamelist.addAll(DBHelper
							.getfavouriteset(	ConstantValues.deals_and_discounts_around_ephesus));

					MainActivity.navDrawerItems.clear();
					MainActivity.navDrawerItems.add(new NavDrawerItem("Main Page",
							MainActivity.navMenuIcons.getResourceId(0, -1)));
				
					if (ConstantValues.citynamelist != null) {
						for (int i = 0; i < ConstantValues.citynamelist.size(); i++) {
							MainActivity.navDrawerItems.add(new NavDrawerItem(
									ConstantValues.citynamelist.get(i).Cityname,
									MainActivity.navMenuIcons.getResourceId(1, -1),
									ConstantValues.citynamelist.get(i).Cityname));

						}

						MainActivity.adapter.notifyDataSetChanged();
					}
					LinearLayout mDrawer = (LinearLayout) getActivity()
							.findViewById(R.id.list);
					DrawerLayout mDrawerLayout = (DrawerLayout) getActivity()
							.findViewById(R.id.drawer_layout);
					mDrawerLayout.openDrawer(mDrawer);
				
				}
			});
		    
			    Button b = (Button)rootView.findViewById(R.id.favourite);
			    b.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Fragment f;
				            f = new FavouriteFragment();
				            FragmentTransaction ft = getFragmentManager().beginTransaction();
				       
				            ft.replace(R.id.frame_container, f);
				            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				            ft.addToBackStack(null);
				            ft.commit();
					}
				});
			   
			/*    Button c = (Button)rootView.findViewById(R.id.chat);
			    c.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String s ="http://techreviewsandhelp.com/";
						//	webView.loadUrl(s);
							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
							startActivity(browserIntent);
					}
				});
	            
			    Button d = (Button)rootView.findViewById(R.id.social);
			    d.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					 LinearLayout mDrawer = (LinearLayout) getActivity().findViewById(R.id.list); 
						DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
						 mDrawerLayout.openDrawer(mDrawer);
					}
				});
	            
			    Button e = (Button)rootView.findViewById(R.id.contact);
			    e.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.setType("text/plain");
						intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "craigbennettii@techreviewsandhelp.com" }); // "craigbennettii@techreviewsandhelp.com");
						intent.putExtra(Intent.EXTRA_SUBJECT, "Carteret County Travel Guide");
						intent.putExtra(Intent.EXTRA_TEXT, "");

						startActivity(Intent.createChooser(intent, "Send Email"));;
					}
				});*/
		
        return rootView;
    }
	
	private class ImagePagerAdapter extends PagerAdapter {
		private final static String stpos="";
		private String[] images;
		private LayoutInflater inflater;

		ImagePagerAdapter(String[] images) {
			this.images = images;
			inflater = getActivity().getLayoutInflater();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return images.length;
		}

	
		@Override
		public Object instantiateItem(View view,  int position) {
			if(view!=null){
			final View imageLayout = inflater.inflate(R.layout.item_pager_image, null);
			final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_item_pager);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.progress_loading_item_pager);
		    try {
				imageView.setImageDrawable(Drawable.createFromStream(getActivity().getAssets().open(images[position]), null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*MainActivity.imageLoader.displayImage(images[position], imageView, options, new ImageLoadingListener() {
			
			
				@Override
				public void onLoadingStarted() {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(FailReason failReason) {
					String message = null;
					switch (failReason) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					//Toast.makeText(ImagePagerActivity.this, message, Toast.LENGTH_SHORT).show();

					spinner.setVisibility(View.GONE);
					imageView.setImageResource(android.R.drawable.ic_delete);
				}

				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
					Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
					imageView.setAnimation(anim);
					anim.start();
					
					
				
				}

				@Override
				public void onLoadingCancelled() {
					// Do nothing
				}
			});
*/
		
			((ViewPager) view).addView(imageLayout, 0);
			
			return imageLayout;
			}
			else
				return view;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
}
