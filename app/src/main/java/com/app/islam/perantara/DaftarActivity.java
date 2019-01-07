package com.app.islam.perantara;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DaftarActivity extends AppCompatActivity {

    static final int HAS_FINISHED_VALUE = 1;
    boolean chapterOneFinished;
    boolean chapterTwoFinished;
    boolean chapterThreeFinished;
    boolean chapterFourFinished;
    Boolean isChapterOneDone;
    Boolean isChapterTwoDone;
    Boolean isChapterThreeDone;
    Boolean isChapterFourDone;
    CardView entry1;
    CardView entry2;
    CardView entry3;
    CardView entry4;
    CardView achievementButton;
    int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        // Defining the corresponding CardView
        entry1 = findViewById(R.id.entry1);
        entry2 = findViewById(R.id.entry2);
        entry3 = findViewById(R.id.entry3);
        entry4 = findViewById(R.id.entry4);
        achievementButton = findViewById(R.id.achievement_button);
        achievementButton.setVisibility(View.GONE);

        achievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penghargaan(v);
            }
        });

        // Checking first run or not
        Boolean daftarFirstRun = getSharedPreferences("PREFERENCE_DAFTAR", MODE_PRIVATE)
                .getBoolean("daftarFirstRun", true);

        if (daftarFirstRun) {
            TapTargetView.showFor(DaftarActivity.this,
                    TapTarget.forView(findViewById(R.id.entry1), "Ketuk disini untuk membaca Bagian 1")
                            .outerCircleColor(R.color.colorPrimary)
                            .transparentTarget(true)
                            .textColor(R.color.white)
                            .cancelable(false),
                    new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);
                            Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
                            intent.putExtra("chapterNumber", "1");
                            startActivityForResult(intent, HAS_FINISHED_VALUE);
                            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                        }
                    });
        }

        // Checking if the chapter has ever been completed before
        isChapterOneDone = getSharedPreferences("PREFERENCE1", MODE_PRIVATE)
                .getBoolean("isChapterOneDone", false);

        isChapterTwoDone = getSharedPreferences("PREFERENCE2", MODE_PRIVATE)
                .getBoolean("isChapterTwoDone", false);

        isChapterThreeDone = getSharedPreferences("PREFERENCE3", MODE_PRIVATE)
                .getBoolean("isChapterThreeDone", false);

        isChapterFourDone = getSharedPreferences("PREFERENCE4", MODE_PRIVATE)
                .getBoolean("isChapterFourDone", false);

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

        if (isChapterFourDone) {
            // change the color of CardView
            entry4.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
        }

        // if any chapter isn't "done" make a notification that will show 7 Days
        if (!isChapterOneDone || !isChapterTwoDone || !isChapterThreeDone || !isChapterFourDone) {
            Intent intent = new Intent(DaftarActivity.this, Receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(DaftarActivity.this, REQUEST_CODE, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.set(alarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
            // Change the intervalMillis to AlarmManager.INTERVAL_DAY*7 before publish
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        }

        if (isChapterOneDone && isChapterTwoDone
                && isChapterThreeDone && isChapterFourDone) {
            achievementButton.setVisibility(View.VISIBLE);
        }

        // Remember to uncomment this line
        getSharedPreferences("PREFERENCE_DAFTAR", MODE_PRIVATE).edit()
                .putBoolean("daftarFirstRun", false).apply();

    }

    public void cerita1(View view) {
        // Function that handle the click at Chapter 1 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "1");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void cerita2(View view) {
        // Function that handle the click at Chapter 2 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "2");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void cerita3(View view) {
        // Function that handle the click at Chapter 3 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "3");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    public void cerita4(View view) {
        // Function that handle the click at Chapter 4 CardView
        Intent intent = new Intent(DaftarActivity.this, StoryActivity.class);
        intent.putExtra("chapterNumber", "4");
        startActivityForResult(intent, HAS_FINISHED_VALUE);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

    }

    public void kontak(View view) {
        // Function that handle the click at Contact CardView
        Intent intent = new Intent(DaftarActivity.this, ContactActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public void penghargaan(View view) {
        // Function that handle the click at Achievement CardView
        Intent intent = new Intent(DaftarActivity.this, AchievementActivity.class);
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
                    entry1 = findViewById(R.id.entry1);
                    cekProgres();
                    entry1.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                } else if (whichChapterDone == 2) { // If the finished chapter is chapter 2
                    chapterTwoFinished = true;
                    entry2 = findViewById(R.id.entry2);
                    cekProgres();
                    entry2.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                } else if (whichChapterDone == 3) { // If the finished chapter is chapter 3
                    chapterThreeFinished = true;
                    entry3 = findViewById(R.id.entry3);
                    cekProgres();
                    entry3.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                } else if (whichChapterDone == 4) { // If the finished chapter is chapter 3
                    chapterFourFinished = true;
                    entry4 = findViewById(R.id.entry4);
                    cekProgres();
                    entry4.setCardBackgroundColor(getResources().getColor(R.color.chapterIsDone));
                }
            }
        }
    }

    public void cekProgres() {
        if (getSharedPreferences("PREFERENCEAWARD", MODE_PRIVATE)
                .getBoolean("isDone", false)) {

            achievementButton.setVisibility(View.VISIBLE);

            if (getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("pertamaSelesai", true)) {

                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("pertamaSelesai", false).apply();

                Intent intent = new Intent(DaftarActivity.this,
                        AchievementActivity.class);
                startActivity(intent);

            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }

}
