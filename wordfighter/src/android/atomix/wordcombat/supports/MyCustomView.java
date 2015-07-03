package android.atomix.wordcombat.supports;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomView extends TextView {

	public MyCustomView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Typeface typeface = Typeface.createFromAsset(context.getAssets(), "ARLRDBD.TTF");
		setTypeface(typeface);
		setTextColor(android.graphics.Color.WHITE);
		setTextSize(18);
	}
}
