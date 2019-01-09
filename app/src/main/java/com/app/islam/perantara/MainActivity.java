package com.app.islam.perantara;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    ImageView muteButton, shareButton;
    CardView nextButton;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muteButton = findViewById(R.id.mute_button);
        shareButton = findViewById(R.id.share_button);
        nextButton = findViewById(R.id.next_button);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
            muteButton.setImageResource(R.drawable.baseline_volume_off_white_48);
        } else {
            muteButton.setImageResource(R.drawable.baseline_volume_up_white_48);
        }

        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) {
                    case 0:
                        muteButton.setImageResource(R.drawable.baseline_volume_up_white_48);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 0);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                        Snackbar.make(findViewById(R.id.main_activity), "Suara dinyalakan",
                                Snackbar.LENGTH_SHORT).show();
                        break;

                    default:
                        muteButton.setImageResource(R.drawable.baseline_volume_off_white_48);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                        Snackbar.make(findViewById(R.id.main_activity), "Suara dimatikan",
                                Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                share.putExtra(Intent.EXTRA_SUBJECT, "Saya menggunakan Perantara");
                share.putExtra(Intent.EXTRA_TEXT, "Saya menggunakan aplikasi Perantara, aplikasi edukasi mengenai Kanker Payudara");
                startActivity(Intent.createChooser(share, "Bagikan"));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);

                if (isFirstRun) {
                    // show FormActivity
                    Intent intent = new Intent(MainActivity.this, FormActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                } else {
                    Intent i = new Intent(MainActivity.this, DaftarActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }
}
