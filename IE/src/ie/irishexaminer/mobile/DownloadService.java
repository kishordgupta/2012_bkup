package ie.irishexaminer.mobile;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.stiggpwnz.asynchttpclient.AsyncHttpClient;
import com.stiggpwnz.asynchttpclient.ErrorListener;
import com.stiggpwnz.asynchttpclient.ResponseListener;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import ie.landmarkdigital.shared.Log;
import ie.landmarkdigital.shared.ManifestRequest;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.activities.SplashScreenActivity;
import ie.landmarkdigital.shared.models.Article;
import ie.landmarkdigital.shared.models.Category;
import ie.landmarkdigital.shared.models.Category.Type;
import ie.landmarkdigital.shared.requests.ArticleRequest;

public class DownloadService extends IntentService {

    public DownloadService() {
        super("Downloading stories");
    }

    private WakeLock    wakeLock;
    private WifiLock    wifiLock;
    private WifiManager wfm;

    private static final int DOWNLOAD_ID = 654684;
    private static final int FINISH_ID   = 846846;

    @Override
    public void onCreate() {
        super.onCreate();
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Downloading stories");
        wakeLock.acquire();

        wfm = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiLock = wfm.createWifiLock(WifiManager.WIFI_MODE_FULL, "Downloading stories");
        wifiLock.acquire();
    }

    private int count;
    private int item;

    static void delete(File f) {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        f.delete();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("on handle intent");

        if (!intent.hasExtra("force")) {
            if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                Log.e("its sunday");
                return;
            }

            if (!wfm.isWifiEnabled()) {
                Log.e("wifi is disabled");
                return;
            }
        }

        Log.d("downloading");

        final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(DOWNLOAD_ID, buildNotification(true));
        ManifestRequest request = new ManifestRequest(new ResponseListener<Category>() {

            @Override
            public void onResponse(Category response, long updated) {
                if (!response.download_overnight) {
                    stopForeground(true);
                    return;
                }

                count = count(response);
                item = 0;
                Log.d("imageloader is inited: " + ImageLoader.getInstance().isInited());
                download(response);
                stopForeground(true);
                notificationManager.notify(
                        FINISH_ID,
                        new NotificationCompat.Builder(DownloadService.this)
                                .setSmallIcon(R.drawable.ic_stat_download_success)
                                .setAutoCancel(true)
                                .setContentIntent(
                                        PendingIntent.getActivity(DownloadService.this, 0, new Intent(DownloadService.this, SplashScreenActivity.class), 0))
                                .setTicker("Stories downloaded").setContentTitle("Ready for offline use").setContentText("Tap to launch the app")
                                .setContentInfo("Irish Examiner").build());
            }
        }, new ErrorListener() {

            @Override
            public void onError(Exception e) {
                stopForeground(true);
                notificationManager.notify(
                        FINISH_ID,
                        new NotificationCompat.Builder(DownloadService.this)
                                .setSmallIcon(R.drawable.ic_stat_download_failure)
                                .setAutoCancel(true)
                                .setContentIntent(PendingIntent.getService(DownloadService.this, 0, new Intent(DownloadService.this, DownloadService.class), 0))
                                .setTicker("Failed to download stories").setContentTitle("Failed to download stories").setContentText("Tap to try again")
                                .setContentInfo("Irish Examiner").build());
            }
        }
        );
        request.setShouldUseCache(false);
        AsyncHttpClient.getInstance().executeSync(request);
    }

    private Notification buildNotification(boolean initializing) {
        Builder builder = new NotificationCompat.Builder(this).setContentIntent(
                PendingIntent.getActivity(this, 0, new Intent(this, SplashScreenActivity.class), 0)).setSmallIcon(R.drawable.ic_stat_download);
        if (initializing) {
            builder.setTicker("Downloading stories...");
            builder.setContentInfo("Initializing...");
        } else {
            builder.setContentInfo(item + "/" + count);
        }
        builder.setContentTitle("Irish Examiner");
        builder.setContentText("Downloading stories for offline use...");
        builder.setProgress(count, item, initializing);
        return builder.build();
    }

    private static int count(Category category) {
        if (category.categories != null) {
            int count = 0;
            for (Category subCategory : category.categories) {
                count += count(subCategory);
            }
            return count;
        }
        if (category.type == Type.CHILD) {
            return 1;
        }
        return 0;
    }

    private void download(Category category) {
        if (category.categories != null) {
            for (Category subCategory : category.categories) {
                download(subCategory);
            }
        } else if (category.type == Type.CHILD) {
            Log.d(category.name);
            ArticleRequest request = new ArticleRequest(category.url, new ResponseListener<List<Article>>() {

                @Override
                public void onResponse(final List<Article> response, long updated) {
                    AsyncHttpClient.getInstance().getUiThreadHandler().post(new Runnable() {

                        @Override
                        public void run() {
                            for (Article article : response) {
                                for (String image : article.images) {
                                    if (article.imageOptions.large) {
                                        ImageLoader.getInstance().loadImage(Article.addLarge(image), null);
                                    }
                                    ImageLoader.getInstance().loadImage(Article.removeLarge(image), null);
                                }
                            }
                        }
                    });
                }
            }, null, DownloadService.class);
            request.setShouldUseCache(false);
            AsyncHttpClient.getInstance().executeSync(request);
            item++;
            startForeground(DOWNLOAD_ID, buildNotification(false));
        }
    }

    @Override
    public void onDestroy() {
        wifiLock.release();
        wakeLock.release();
        super.onDestroy();
    }
}
