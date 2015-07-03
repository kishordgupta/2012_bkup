package backgroundTasks;



/**
 * 
 */

import com.example.swipeuiforupclose.HelperActivity;
import com.example.swipeuiforupclose.MainActivity;
import com.example.swipeuiforupclose.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * @author little
 *
 */
public class MyNotification extends Notification {
	
	private Context ctx;
	private NotificationManager mNotificationManager;
	
	public MyNotification(Context ctx){
		super();
		this.ctx=ctx;
		String ns = Context.NOTIFICATION_SERVICE;
		mNotificationManager = (NotificationManager) ctx.getSystemService(ns);
		CharSequence tickerText = "Radio Box";
		long when = System.currentTimeMillis();
		Notification.Builder builder = new Notification.Builder(ctx);
		Notification notification=builder.getNotification();
		notification.when=when;
		notification.tickerText=tickerText;
		notification.icon=R.drawable.ic_launcher;
		
		RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout);
		
		//set the button listeners
		setListeners(contentView);
		
		notification.contentView = contentView;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		CharSequence contentTitle = "From Shortcuts";
		mNotificationManager.notify(548853, notification);
	}
	
	public void setListeners(RemoteViews view){
		//radio listener
		
		
		//reboot listener
		Intent reboot=new Intent(ctx,HelperActivity.class);
		reboot.putExtra("DO", "playlisten");
		PendingIntent pReboot = PendingIntent.getActivity(ctx, 5, reboot, 0);
		view.setOnClickPendingIntent(R.id.playlisten, pReboot);
		
		//top listener
		Intent top=new Intent(ctx,HelperActivity.class);
		top.putExtra("DO", "rep");
		PendingIntent pTop = PendingIntent.getActivity(ctx, 3, top, 0);
		view.setOnClickPendingIntent(R.id.rep, pTop);
		
		Intent close=new Intent(ctx,HelperActivity.class);
		close.putExtra("DO", "close");
		PendingIntent closea = PendingIntent.getActivity(ctx, 2, close, 0);
		view.setOnClickPendingIntent(R.id.close, closea);
		
		/*
		//app listener
		Intent app=new Intent(ctx, tsapalos11598712.bill3050.shortcuts.helper.HelperActivity.class);
		app.putExtra("DO", "app");
		PendingIntent pApp = PendingIntent.getActivity(ctx, 4, app, 0);
		view.setOnClickPendingIntent(R.id.app, pApp);*/
	}

}
