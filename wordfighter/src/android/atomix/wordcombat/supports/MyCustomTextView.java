package android.atomix.wordcombat.supports;

import android.atomix.wordcombat.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomTextView extends TextView {

	public MyCustomTextView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Blaklitn.ttf");
		setTypeface(typeface);
		setTextSize(18);
		setTextColor(R.color.black);
	}
}
