package com.app.islam.perantara;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AchievementActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView viewPagerNext;
    ImageView viewPagerPrev;
    ImageView finishButton;

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

//        releaseMediaPlayer();
//        startDialog(R.raw.chap_achievements);

        viewPagerNext = findViewById(R.id.view_pager_next);
        viewPagerPrev = findViewById(R.id.view_pager_prev);
        finishButton = findViewById(R.id.finish_chapter);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                finish();
                overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
            }
        });

        viewPagerPrev.setVisibility(View.GONE);

        viewPager = findViewById(R.id.view_pager);
        WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);

        AchievementPagerAdapter achievementPagerAdapter =
                new AchievementPagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(achievementPagerAdapter);

        wormDotsIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        releaseMediaPlayer();
                        startDialog(R.raw.chap_achievements);

                        // Set The Prev and Next button visibility
                        viewPagerPrev.setVisibility(View.GONE);
                        viewPagerNext.setVisibility(View.VISIBLE);
                        finishButton.setVisibility(View.GONE);
                        break;

                    default:
                        releaseMediaPlayer();
                        // TODO: New dialog recording
//                        startDialog(R.raw.chap_achievements);

                        // Set The Prev and Next button visibility
                        viewPagerPrev.setVisibility(View.VISIBLE);
                        viewPagerNext.setVisibility(View.GONE);
                        finishButton.setVisibility(View.VISIBLE);

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releaseMediaPlayer();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }

    public void viewPagerNext(View view) {
        viewPagerNext = findViewById(R.id.view_pager_next);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    public void viewPagerPrev(View view) {
        viewPagerPrev = findViewById(R.id.view_pager_prev);
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
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
