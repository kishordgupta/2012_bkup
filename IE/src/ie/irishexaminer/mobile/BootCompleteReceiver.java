package ie.irishexaminer.mobile;

import ie.landmarkdigital.shared.Log;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {

	private static final int HOUR = 5; // 5 am
	private static final long INTERVAL = AlarmManager.INTERVAL_DAY; // one day

	@Override
	public void onReceive(Context context, Intent intent) {
		if (isInitialStickyBroadcast()) {
			Log.d("sticky alarm broadcast");
		} else {
			Log.d("normal alarm broadcast");
		}
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent operation = PendingIntent.getService(context, 0, new Intent(context, DownloadService.class), 0);

		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.HOUR_OF_DAY) > HOUR) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, HOUR);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL, operation);
	}
}
