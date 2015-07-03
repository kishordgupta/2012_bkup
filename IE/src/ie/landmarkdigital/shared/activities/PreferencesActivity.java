package ie.landmarkdigital.shared.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;

import java.io.File;
import java.util.Locale;

import ie.irishexaminer.mobile.BootCompleteReceiver;
import ie.irishexaminer.mobile.DownloadService;
import ie.landmarkdigital.shared.ChangeLogDialog;
import ie.landmarkdigital.shared.Log;
import ie.landmarkdigital.shared.Preferences;
import ie.landmarkdigital.shared.R;
import ie.landmarkdigital.shared.activities.BasePreferencesActivity;

public class PreferencesActivity extends BasePreferencesActivity implements OnPreferenceChangeListener, OnPreferenceClickListener {
    static final String TEXT_SIZE = "text size";

    ListPreference     textSize;
    ListPreference     orientationLock;
    Preference         appVersion;
    CheckBoxPreference downloadOvernight;
    Preference         downloadNow;

    String[] labels;
    String[] sizes;

    String[] orientationLabels;
    String[] orientationValues;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(Preferences.getOrientation());

        setUpTextSizes();
        setUpOrientationLock();

        appVersion = findPreference("app version");
        appVersion.setOnPreferenceClickListener(this);

        downloadOvernight = (CheckBoxPreference) findPreference("download overnight");
        downloadOvernight.setOnPreferenceChangeListener(this);

        downloadNow = findPreference("download now");
        downloadNow.setOnPreferenceClickListener(this);

        getSupportActionBar().setDisplayUseLogoEnabled(false);
    }

    @Override
    public boolean onPreferenceChange(Preference arg0, Object arg1) {
        if (arg0 == textSize) {
            String size = (String) arg1;
            textSize.setSummary(labels[indexOf(size, sizes)]);

            return true;
        } else if (arg0 == orientationLock) {
            String value = (String) arg1;
            orientationLock.setSummary(orientationLabels[indexOf(value, orientationValues)]);
            setRequestedOrientation(Integer.valueOf(value));

            return true;
        } else if (arg0 == downloadOvernight) {
            Boolean value = (Boolean) arg1;
            if (value) {
                Log.d("enabling download");
                new BootCompleteReceiver().onReceive(this, null);
            } else {
                Log.d("disabling download");
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent operation = PendingIntent.getService(this, 0, new Intent(this, DownloadService.class), 0);
                alarmManager.cancel(operation);
            }
            return true;
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    private void setUpOrientationLock() {
        orientationLabels = getResources().getStringArray(R.array.orientation_labels);
        orientationValues = getResources().getStringArray(R.array.orientations);

        String value = String.valueOf(Preferences.getOrientation());
        int indexOf = indexOf(value, orientationValues);

        orientationLock = (ListPreference) findPreference("orientation lock");
        if (indexOf != -1) {
            orientationLock.setSummary(orientationLabels[indexOf]);
        }
        orientationLock.setOnPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
    private void setUpTextSizes() {
        labels = getResources().getStringArray(R.array.font_size_labels);
        sizes = getResources().getStringArray(R.array.font_sizes);

        String size = String.valueOf(Math.round(Preferences.getFontSize()));
        int indexOf = indexOf(size, sizes);

        textSize = (ListPreference) findPreference(TEXT_SIZE);
        if (indexOf != -1) {
            textSize.setSummary(labels[indexOf]);
        }
        textSize.setOnPreferenceChangeListener(this);
    }

    public static long folderSize(File directory) {
        long length = 0;
        if (directory != null) {
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    length += file.length();
                else
                    length += folderSize(file);
            }
        }
        return length;
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { // some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    private static int indexOf(String string, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (string.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format(Locale.getDefault(), "%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == appVersion) {
            new ChangeLogDialog(this, R.xml.changelog).show();
        } else if (preference == downloadNow) {
            startService(new Intent(this, DownloadService.class).putExtra("force", true));
        }
        return false;
    }

}
