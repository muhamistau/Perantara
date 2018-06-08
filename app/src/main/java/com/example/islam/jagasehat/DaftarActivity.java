package com.example.islam.jagasehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


    }

    void cerita1 (View view) {
        Intent intent = new Intent(DaftarActivity.this, PengenalanTokohActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    void cerita2 (View view) {

    }

    void cerita3 (View view) {

    }

    void cerita4 (View view) {

    }

    void kontak (View view) {

    }
}
