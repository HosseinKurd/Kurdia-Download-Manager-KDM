package com.hosseinkurd.kdm;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hosseinkurd.kdm.db.DataBaseManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class KDM {

    private static KDM instance = null;
    private DataBaseManager mDBManager;
    private Map<String, Downloader> queue;
    private Thread thread;

    private KDM() {
        queue = new LinkedHashMap<>();
    }

    public static KDM getInstance() {
        if (instance == null) {
            synchronized (KDM.class) {
                if (instance == null) {
                    instance = new KDM();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mDBManager = DataBaseManager.getInstance(context);
    }

    public void start(String tag) {

    }

    public void startAll() {

    }

    public void pause(String tag) {

    }

    public void pauseAll() {

    }

    public void cancel(String tag) {

    }

    public void cancelAll() {

    }

    public boolean isDownloading(String tag) {
        return false;
    }

    public void download(String urlString, @Nullable OnDownload onDownload) {
        Runnable runnable = () -> {
            int downloadedSize = 0;
            int totalSize = 0;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(false);

                //connect
                urlConnection.connect();

                //set the path where we want to save the file
                File SDCardRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (!SDCardRoot.exists() || !SDCardRoot.isDirectory()) {
                    SDCardRoot.mkdirs();
                }
                //create a new file, to save the downloaded file
                File file = new File(SDCardRoot, urlString.substring(urlString.lastIndexOf("/")));

                FileOutputStream fileOutput = new FileOutputStream(file);

                //Stream used for reading the data from the internet
                InputStream inputStream = urlConnection.getInputStream();

                //this is the total size of the file which we are downloading
                totalSize = urlConnection.getContentLength();

                byte[] buffer = new byte[1024];
                int bufferLength = 0;

                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    if (onDownload != null) {
                        float per = ((float) downloadedSize / totalSize) * 100;
                        Log.d("KDM_DL_TAG", "Downloaded : " + downloadedSize + "KB / " + totalSize + "KB (" + (int) per + "%)");
                        onDownload.onDownloadProgress(totalSize, downloadedSize, per);
                    }
                }
                // Close the output stream when complete
                fileOutput.close();
                if (onDownload != null) {
                    onDownload.onDownloadSuccess(file);
                }
            } catch (final MalformedURLException e) {
                Log.e("KDM_DL_TAG", "Error : MalformedURLException " + e.getMessage(), e);
                if (onDownload != null) {
                    onDownload.onDownloadFailed(e);
                }
            } catch (final IOException e) {
                Log.e("KDM_DL_TAG", "Error : IOException " + e.getMessage(), e);
                if (onDownload != null) {
                    onDownload.onDownloadFailed(e);
                }
            } catch (final Exception e) {
                Log.e("KDM_DL_TAG", "Error : Exception " + e.getMessage(), e);
                if (onDownload != null) {
                    onDownload.onDownloadFailed(e);
                }
            }
            thread.interrupt();
        };
        thread = new Thread(runnable);
        thread.start();
    }
}