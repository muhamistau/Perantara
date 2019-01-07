package com.app.islam.perantara;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                .getBoolean("isFirstRun", true);
//
//        if (isFirstRun) {
//            // show FormActivity
//            Intent intent = new Intent(SplashActivity.this, FormActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        }
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();

    }
}
