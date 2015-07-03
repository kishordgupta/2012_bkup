package radioklub.sekhontech.com.provider;


import com.example.swipeuiforupclose.R;

import radioklub.sekhontech.com.service.MusicPlayerService;



import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class MusicWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		// Get all ids
		ComponentName thisWidget = new ComponentName(context,
				MusicWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.layout_radio_widget);

			// Register an onClickListener
			Intent intent = new Intent(context, MusicWidgetProvider.class);
			Bundle bundle = new Bundle();
			bundle.putString(MusicPlayerService.PLAY_THIS_ONE, "");
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.btn_play, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}
