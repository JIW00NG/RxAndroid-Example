package com.anjian.RxExample.RxJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anjian.RxExample.R;

public class HomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Button moveBasicButton = (Button)findViewById(R.id.MoveBasicButton);
        Button moveRxButton = (Button)findViewById(R.id.MoveRxButton);

        moveBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BasicActivity.class);
                startActivity(intent);
            }
        });
        moveRxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RxActivity.class);
                startActivity(intent);
            }
        });
    }
}
