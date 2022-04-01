package com.example.assignment12;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.assignment12.databinding.ActivityMainBinding;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String ,Void, Bitmap> {
    HttpURLConnection httpURLConnection;
    ActivityMainBinding binding;
    ProgressDialog progressDialog;
    private final WeakReference<Context> contextWeakReference;

    public ImageDownloader(WeakReference<Context> contextWeakReference) {
        this.contextWeakReference = contextWeakReference;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(contextWeakReference.get());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Image is being Loaded");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            Bitmap temp = BitmapFactory.decodeStream(inputStream);
            return temp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            httpURLConnection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        progressDialog.dismiss();
        if (bitmap != null){
            binding.ivShowImage.setImageBitmap(bitmap);
        }

    }
}
