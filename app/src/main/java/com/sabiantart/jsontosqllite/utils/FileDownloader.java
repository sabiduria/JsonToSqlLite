package com.sabiantart.jsontosqllite.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

public class FileDownloader {

    public interface ProgressListener {
        void onProgressUpdate(int progress);
    }

    public static void downloadFile(Context context, String fileUrl, String fileName, ProgressListener listener) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int fileLength = connection.getContentLength();

            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);

            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            long total = 0;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);

                total += length;

                // Update progress (same as before)
                int progress = (int) (total * 100 / fileLength);
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (listener != null) {
                        listener.onProgressUpdate(progress);
                    }
                });
            }

            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e(String.valueOf(Level.WARNING), e.getMessage(), e);
        }
    }
}