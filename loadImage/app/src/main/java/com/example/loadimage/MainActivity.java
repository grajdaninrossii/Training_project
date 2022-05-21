package com.example.loadimage;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Main_Activity" ;

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView image){
            bmImage = image;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL urlDisplay = null;
            try {
                urlDisplay = new URL(urls[0]);
            } catch (MalformedURLException e) {
                Log.e("Что-то пошло не так, URL не найден!", e.getMessage());
                e.printStackTrace();
            }
            Bitmap myIcon = null;
            try{
                InputStream in = (InputStream) urlDisplay.getContent();
                myIcon = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                Log.e("Что-то пошло не так, не возможно прочитать данные из URL!", e.getMessage());
                e.printStackTrace();
            }
            return myIcon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView newImage = (ImageView) findViewById(R.id.imageView1);

        new DownloadImageTask(newImage).execute("https://s1.1zoom.ru/big3/690/412727-sepik.jpg");
    }
}

//public class MainActivity extends AppCompatActivity {
//
//    private static final String LOG_TAG = "Main_Activity" ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Показать картинку
//        new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
//                .execute("https://s1.1zoom.ru/big3/690/412727-sepik.jpg");
//    }
//
////    public void onClick(View v) {
////        startActivity(new Intent(this, MainActivity.class));
////        finish();
////    }
//
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        // Метод для выполнения команд в фоне.
//        protected Bitmap doInBackground(String ... urls) {
//            Log.d(LOG_TAG, urls[0]);
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Ошибка передачи изображения", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        // Выполняется после doInBackground.
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
//}