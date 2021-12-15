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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RxActivity extends AppCompatActivity {

    TextView textView;
    ImageButton imageButton;
    SimpleDateFormat timeFormat;
    Date currentTime;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_layout);

        compositeDisposable = new CompositeDisposable();

        timeFormat = new SimpleDateFormat("y년 M월 d일 H시 m분 s초");

        textView = (TextView) findViewById(R.id.TextView);
        imageButton = (ImageButton) findViewById(R.id.ImageButton);

        rxTimer();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Rx", "image Download");
                rxDownloadImage();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void rxTimer(){
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        currentTime = new Date();
                        textView.setText(timeFormat.format(currentTime));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void rxDownloadImage(){
        String imageURL = "https://source.unsplash.com/random";

        Observable<Bitmap> imageDownloadObservable = Observable.just(imageURL)
                .subscribeOn(Schedulers.io())
                .map(URL::new)
                .map(url -> (HttpURLConnection)url.openConnection())
                .map(connection -> {
                    connection.setDoInput(true);
                    connection.connect();
                    return connection;
                })
                .map(URLConnection::getInputStream)
                .map(BitmapFactory::decodeStream)
                .observeOn(AndroidSchedulers.mainThread());

        imageDownloadObservable.subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {}

            @Override
            public void onNext(@NonNull Bitmap bitmap) {
                imageButton.setImageBitmap(bitmap);
            }

            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {
                Log.d("Rx", "image Download complete");
            }
        });
    }
}