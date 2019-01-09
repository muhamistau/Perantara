package com.app.islam.perantara;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AchievementActivity extends AppCompatActivity {

    CardView shareButton;
    //Use MediaPlayer API and create an global variable for it
    private MediaPlayer mediaPlayer;

    //handles audio focus when playing a sound file
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = (new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Pause
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Resume
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //Stop and release resource
                releaseMediaPlayer();
            }
        }
    });

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        releaseMediaPlayer();
        startDialog(R.raw.chap_achievements);

        shareButton = findViewById(R.id.share_card2);
        shareButton.setVisibility(View.GONE);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                share.putExtra(Intent.EXTRA_SUBJECT, "Saya telah menyelesaikan aplikasi Perantara");
                share.putExtra(Intent.EXTRA_TEXT, "Saya telah menyelesaikan aplikasi Perantara, aplikasi edukasi mengenai Kanker Payudara. Ayo download Aplikasinya");
                startActivity(Intent.createChooser(share, "Bagikan"));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releaseMediaPlayer();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }

    public void startDialog(int id) {
        // Create and setup the AudioManager to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        releaseMediaPlayer();

        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                //Use the music stream
                AudioManager.STREAM_MUSIC,
                //Request permanent focus
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Create and setup the MediaPlayer for the audio resource associated with the current word
            mediaPlayer = MediaPlayer.create(AchievementActivity.this, id);

            //Start the audio file
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(completionListener);
        }
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currenly palying sound
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need it.
            mediaPlayer.stop();
            mediaPlayer.release();

            // Set media player back to null. For our code, we've decided that setting the media player to null is an easy way
            // to tell that the media player is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
