package com.atomix.kurowiz.supports;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	
	private OnScrollStoppedListener onScrollStoppedListener;
	private int scrollingTime = 0;
	
	public interface OnScrollStoppedListener
	{
		void onScrollStopped();
	}
	
	public MyScrollView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}
	
	public void setOnScrollStoppedListener(MyScrollView.OnScrollStoppedListener listener)
	{
	    onScrollStoppedListener = listener;
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) 
	{
		View view = (View) getChildAt(getChildCount() - 1);
        
        // Calculate the scrolldiff
        int diff = (view.getBottom() - (getHeight() + getScrollY()));
        
        // if diff is zero, then the bottom has been reached
		if (ConstantValues.isScrolling) 
		{
			if (diff == 0) 
			{
				scrollingTime++;
				Log.i("value is ", "______________________"+scrollingTime);
				if(scrollingTime >= 3)
				{
					scrollingTime = 0;
					Log.i("Result___________", "MyScrollView: Bottom has been reached");
					if (ConstantValues.isBottomReached)
						onScrollStoppedListener.onScrollStopped();
				}
				
			}
		}
        
        super.onScrollChanged(l, t, oldl, oldt);
	}

}
