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
    boolean chapterTwoFinished;
    boolean chapterThreeFinished;
    int chapterOneProgression;
    CardView entry1;
    CardView entry2;
    CardView entry3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        // Defining the coresponding CardView
        entry1 = (CardView) findViewById(R.id.entry1);
        entry2 = (CardView) findViewById(R.id.entry2);
        entry3 = (CardView) findViewById(R.id.entry3);

        // Checking if the chapter has ever been completed before
        Boolean isChapterOneDone = getSharedPreferences("PREFERENCE1", MODE_PRIVATE)
                .getBoolean("isChapterOneDone", false);

        Boolean isChapterTwoDone = getSharedPreferences("PREFERENCE2", MODE_PRIVATE)
                .getBoolean("isChapterTwoDone", false);

        Boolean isChapterThreeDone = getSharedPreferences("PREFERENCE3", MODE_PRIVATE)
                .getBoolean("isChapterThreeDone", false);

        // If the chapter already completed change the color to green at the start
        if (isChapterOneDone) {
            // change the color of CardView
            entry1.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
        }
        if (isChapterTwoDone) {
            // change the color of CardView
            entry2.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
        }
        if (isChapterThreeDone) {
            // change the color of CardView
            entry3.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
        }

    }

    public void cerita1(View view) {
        // Funtion that handle the click at Chapter 1 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "1");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void cerita2(View view) {
        // Funtion that handle the click at Chapter 2 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "2");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void cerita3(View view) {
        // Funtion that handle the click at Chapter 3 CardView

    }

    public void cerita4(View view) {
        // Funtion that handle the click at Chapter 4 CardView

    }

    public void kontak(View view) {
        // Funtion that handle the click at Contact CardView
        Intent intent = new Intent(DaftarActivity.this, ContactActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    // Function that checking the return result after calling startActivityForResult() function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HAS_FINISHED_VALUE) { // checking if the request code matchup
            if (resultCode == Activity.RESULT_OK) { // checking if the result code return RESULT_OK
                // Getting the value of the result coresponding to the finished chapter
                int whichChapterDone = Integer.parseInt(data.getStringExtra("finish"));
                if (whichChapterDone == 1) { // If the finished chapter is chapter 1
                    chapterOneFinished = true;
                    entry1 = (CardView) findViewById(R.id.entry1);
                    getSharedPreferences("PREFERENCE1", MODE_PRIVATE).edit()
                            .putBoolean("isChapterOneDone", chapterOneFinished).apply();
                    entry1.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                } else if (whichChapterDone == 2) { // If the finished chapter is chapter 2
                    chapterTwoFinished = true;
                    entry2 = (CardView) findViewById(R.id.entry2);
                    getSharedPreferences("PREFERENCE2", MODE_PRIVATE).edit()
                            .putBoolean("isChapterTwoDone", chapterTwoFinished).apply();
                    entry2.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                } else if (whichChapterDone == 3) { // If the finished chapter is chapter 3
                    chapterThreeFinished = true;
                    entry3 = (CardView) findViewById(R.id.entry3);
                    getSharedPreferences("PREFERENCE3", MODE_PRIVATE).edit()
                            .putBoolean("isChapterThreeDone", chapterThreeFinished).apply();
                    entry3.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                }
            }
        }
    }
}
