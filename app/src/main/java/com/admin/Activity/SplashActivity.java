package com.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.R;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    String flage;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences("CreateLogin",MODE_PRIVATE);

        flage = preferences.getString("flag","hello");
        //  imageView = (ImageView) findViewById(R.id.logo_img);
        //  textView = (TextView) findViewById(R.id.textView3);
        Thread timerThread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
//                    if (flage.equalsIgnoreCase("data")) {
//                        Intent intent = new Intent(SplashActivity.this, DataEntry.class);
//                        startActivity(intent);
//                    }else if (flage.equalsIgnoreCase("market")){
//                        Intent intent = new Intent(SplashActivity.this, MarketingActivity.class);
//                        startActivity(intent);
//                    }
//                    else{
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
//                    }
                }

            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
