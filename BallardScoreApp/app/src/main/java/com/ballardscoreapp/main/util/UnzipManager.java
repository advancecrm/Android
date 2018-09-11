package com.ballardscoreapp.main.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipManager {
    public static Context localContext;
    public static boolean isDownloadInProgress;
    public static boolean isLowOnMemory;
    static String urlofvideo = "";
    private static String BASE_FOLDER;

    public static void startUnzipping(String url, File f) {
        Log.d("DEBUG", "In startUnzipping()");
        urlofvideo = url;
        UnzipManager.BASE_FOLDER = f.getPath();
        Log.d("DEBUG", "BASE_FOLDER:" + UnzipManager.BASE_FOLDER);
        UnzipManager.isLowOnMemory = false;

        new UnzipThread().start();
    }

    private static class UnzipThread extends Thread {
        @Override
        public void run() {
            UnzipManager.isDownloadInProgress = true;
            Log.d("DEBUG", "Unzipping----------------------------");
            URLConnection urlConnection;
            try {

                URL finalUrl = new URL(urlofvideo);
                urlConnection = finalUrl.openConnection();
                int contentLength = urlConnection.getContentLength();
                Log.d("DEBUG", "urlConnection.getContentLength():"
                        + contentLength);

                ZipInputStream zipInputStream = new ZipInputStream(
                        urlConnection.getInputStream());

                for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream
                        .getNextEntry()) {
                    Log.d("DEBUG", "Extracting: " + zipEntry.getName() + "...");

                    String innerFileName = BASE_FOLDER + File.separator
                            + zipEntry.getName();
                    File innerFile = new File(innerFileName);

                    if (innerFile.exists()) {
                        Log.d("DEBUG",
                                "The Entry already exits!, so deleting..");
                        innerFile.delete();
                    }

                    if (zipEntry.isDirectory()) {
                        Log.d("DEBUG", "The Entry is a directory..");
                        innerFile.mkdirs();
                    } else {
                        Log.d("DEBUG", "The Entry is a file..");
                        FileOutputStream outputStream = new FileOutputStream(
                                innerFileName);
                        final int BUFFER_SIZE = 2048;
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                outputStream, BUFFER_SIZE);

                        int count = 0;
                        byte[] buffer = new byte[BUFFER_SIZE];
                        while ((count = zipInputStream.read(buffer, 0,
                                BUFFER_SIZE)) != -1) {
                            bufferedOutputStream.write(buffer, 0, count);
                        }

                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }

                    zipInputStream.closeEntry();
                }

                zipInputStream.close();
                Log.d("DEBUG", "--------------------------------");
                Log.d("DEBUG", "Unzipping completed..");
            } catch (IOException e) {
                Log.d("DEBUG", "Exception occured: " + e.getMessage());
                if (e.getMessage().equalsIgnoreCase("No space left on device")) {
                    UnzipManager.isLowOnMemory = true;
                }
                e.printStackTrace();
            }
            UnzipManager.isDownloadInProgress = false;
        }
    }

    ;
}
