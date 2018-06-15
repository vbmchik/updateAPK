package com.vbm.updateapk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateApp extends AsyncTask<String,Void,Void> {
    private Context context;

    public UpdateApp( Context context ){
        this.context = context;
    }


    @Override
    protected Void doInBackground(String... arg0) {
        try {
            if (arg0.length == 0) throw new Exception("No URL");
            URL url;
            if (arg0.length == 1)
                url = new URL(arg0[0]);
            else
                url = new URL(arg0[1]);

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            //String PATH = ((MainActivity)context).getFilesDir().getPath();
            String PATH = Globals.DOWNLOADS;
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "update.apk");
            if(outputFile.exists()){
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            final byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }

            fos.close();
            is.close();

            if (arg0.length > 1) {
                ((SettingsActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Globals.AXELOTURL = new String(buffer);
                        ((SettingsActivity) context).repoUpdate(Globals.AXELOTURL);
                    }
                });

                return null;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(outputFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent);

           ((MainActivity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity)context).progress(false);
                }
            });


        } catch (Exception e) {
            final String k = e.getLocalizedMessage();
            Log.e("UpdateAPP", "Update error! " + e.getMessage());
            ((MainActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity) context).exceptmsg(k);
                }
            });

        }
        return null;
    }
}