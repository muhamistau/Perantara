package com.example.islam.jagasehat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class DaftarActivity extends AppCompatActivity {

    static final int HAS_FINISHED_VALUE = 1;
    boolean chapterOneFinished;
    int chapterOneProgression;
    CardView entry1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        entry1 = (CardView) findViewById(R.id.entry1);

        Boolean isChapterOneDone = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isChapterOneDone", false);

        if (isChapterOneDone) {
//            change the color of CardView
            entry1.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));

        }
    }

    public void cerita1(View view) {
        Intent intent = new Intent(DaftarActivity.this, PengenalanTokohActivity.class);
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void cerita2(View view) {

    }

    public void cerita3(View view) {

    }

    public void cerita4(View view) {

    }

    public void kontak(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HAS_FINISHED_VALUE) {
            if (resultCode == Activity.RESULT_OK) {
                chapterOneFinished = true;
                entry1 = (CardView) findViewById(R.id.entry1);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isChapterOneDone", chapterOneFinished).apply();
                entry1.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
            }
        }
    }
}
