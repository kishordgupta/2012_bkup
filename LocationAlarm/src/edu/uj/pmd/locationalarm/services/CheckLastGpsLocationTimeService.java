package edu.uj.pmd.locationalarm.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import edu.uj.pmd.locationalarm.utilities.LocalizationUtilities;

/**
 * User: piotrplaneta
 * Date: 26.11.2012
 * Time: 18:52
 */
public class CheckLastGpsLocationTimeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (GpsLocalizationService.isRunning) {
            LocalizationUtilities.stopGpsLocalizationService(this);
        }
        return START_STICKY;
    }
}
