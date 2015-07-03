package ie.landmarkdigital.shared;

import android.app.PendingIntent;
import android.content.Intent;

import com.facebook.Settings;

import ie.irishexaminer.mobile.BootCompleteReceiver;
import ie.irishexaminer.mobile.DownloadService;

/**
 * Created by adel on 2/24/14
 */
public class App extends BaseApp {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.init(getAssets());
        Settings.publishInstallAsync(this, getString(R.string.facebook_id));

        Intent intent = new Intent(this, DownloadService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_NO_CREATE);
        if (Preferences.shouldDownloadOvernight() && pendingIntent == null) {
            new BootCompleteReceiver().onReceive(this, null);
        }
    }
}
