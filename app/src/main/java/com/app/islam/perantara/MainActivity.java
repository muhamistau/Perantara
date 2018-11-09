package com.app.islam.perantara;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button muteButton;
    CardView nextButton;
    TextView status;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muteButton = findViewById(R.id.mute_button);
        nextButton = findViewById(R.id.next_button);
        status = findViewById(R.id.text_status);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)) {
                    case 0:
                        muteButton.setText("Mute Media Sound");
                        audioManager.setStreamVolume(audioManager.STREAM_MUSIC, 10, 0);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                        status.setText("" + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                        break;

                    default:
                        muteButton.setText("Unmute Media Sound");
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                        status.setText("" + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                        break;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DaftarActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }
}
