package com.anjian.RxExample.RxJava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anjian.RxExample.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicActivity extends AppCompatActivity {

    TextView textView;
    ImageButton imageButton;
    SimpleDateFormat timeFormat;
    Date currentTime;

    public static BasicActivity newInstance() {
        return new BasicActivity();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_layout);

        timeFormat = new SimpleDateFormat("y년 M월 d일 H시 m분 s초");

        textView = (TextView) findViewById(R.id.TextView);
        imageButton = (ImageButton) findViewById(R.id.ImageButton);

        timer();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Not Rx", "image Download");
                downloadImage();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void timer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    currentTime = new Date();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(timeFormat.format(currentTime));
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void downloadImage(){
        String imageURL = "https://source.unsplash.com/random";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageURL);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageButton.setImageBitmap(bitmap);
                            Log.d("Basic", "image Download complete");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}