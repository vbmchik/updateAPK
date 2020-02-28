package com.vbm.updateapk;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class Globals {


    public static final String DOWNLOADS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    public static final String SERVERVALUE = "SERVER";
    public static final String REPOVALUE = "REPOVALUE";

    //public static final String ZEBRAURL = "http://192.168.0.199/1/1.apk";
    public static final String SETTINGSKEY = "SETINGSKEY";
    public static String AXELOTURL = "http://192.168.0.37/1/AxelotMobileClient.apk";
    public static final String CHOICETAG = "CHOICETAG";
    public static final String REPOTAG = "REPOTAG";
    public static String REPOURL = "http://192.168.0.37/1/config.upd";

    static String readFromSettings(Context context, String key) {
        String s = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if (sharedPreferences == null) return s;
        return sharedPreferences.getString(key, "");
    }

    static void requestAppPermissions(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                ActivityCompat.requestPermissions((MainActivity) context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET
                        },
                        1);
            }
        }
    }

    static boolean writeToSettings(Context context, String key, String value) {
        boolean success = false;

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);

        try {
            sharedPreferences.edit().putString(key, value).commit();
            return true;
        } catch (Exception e) {
            return false;
        }
        //return success;
    }

    public enum SettingsItem {
        SERVER
    }
}
