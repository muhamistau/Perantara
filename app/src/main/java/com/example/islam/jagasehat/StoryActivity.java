package com.example.islam.jagasehat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class StoryActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView viewPagerNext;
    ImageView viewPagerPrev;
    ImageView finishButton;
    Intent resultIntent;
    int chapterNumber;

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
        setContentView(R.layout.activity_story);

        final Boolean storyFirstRun = getSharedPreferences("PREFERENCE_STORY", MODE_PRIVATE)
                .getBoolean("storyFirstRun", true);

        viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
        viewPagerPrev = (ImageView) findViewById(R.id.view_pager_prev);
        finishButton = (ImageView) findViewById(R.id.finish_chapter);

        viewPagerPrev.setVisibility(View.GONE);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);

        chapterNumber = Integer.parseInt(getIntent().getStringExtra("chapterNumber"));
        ChapterOnePagerAdapter chapterOnePagerAdapter;
        ChapterTwoPagerAdapter chapterTwoPagerAdapter;
        ChapterThreePagerAdapter chapterThreePagerAdapter;
        ChapterFourPagerAdapter chapterFourPagerAdapter;

        switch (chapterNumber) {
            case 1:
                chapterOnePagerAdapter = new ChapterOnePagerAdapter(getSupportFragmentManager(), this);
                viewPager.setAdapter(chapterOnePagerAdapter);
                break;
            case 2:
                chapterTwoPagerAdapter = new ChapterTwoPagerAdapter(getSupportFragmentManager(), this);
                viewPager.setAdapter(chapterTwoPagerAdapter);
                break;
            case 3:
                chapterThreePagerAdapter = new ChapterThreePagerAdapter(getSupportFragmentManager(), this);
                viewPager.setAdapter(chapterThreePagerAdapter);
                break;
            default:
                chapterFourPagerAdapter = new ChapterFourPagerAdapter(getSupportFragmentManager(), this);
                viewPager.setAdapter(chapterFourPagerAdapter);
                break;
        }

        wormDotsIndicator.setViewPager(viewPager);

        switch (chapterNumber) {
            case 1:
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                releaseMediaPlayer();

                                // Set The Prev and Next button visibility
                                viewPagerPrev.setVisibility(View.GONE);
                                viewPagerNext.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                // Fill with code to start audio for Fragment 1
                                releaseMediaPlayer();
                                startDialog(R.raw.perkenalan_eti);

                                // Set The Prev and Next button visibility
                                viewPagerPrev.setVisibility(View.VISIBLE);
                                break;
                            case 2:

                                // Fill with code to end Fragment 1 Audio and start audio for Fragment 2
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_1_drajat);
                                break;
                            case 3:
                                // Fill with code to end Fragment 2 Audio and start audio for Fragment 3
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_1_ahyani);

                                // Set The Prev and Next button visibility
                                viewPagerNext.setVisibility(View.VISIBLE);
                                finishButton.setVisibility(View.GONE);
                                break;
                            default:
                                // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                                releaseMediaPlayer();

                                // Set The Prev and Next button visibility
                                viewPagerNext.setVisibility(View.GONE);
                                finishButton.setVisibility(View.VISIBLE);

                                if (storyFirstRun) {
                                    TapTargetView.showFor(StoryActivity.this,
                                            TapTarget.forView(findViewById(R.id.finish_chapter),
                                                    "Ketuk disini untuk menyelesaikan chapter")
                                                    .outerCircleColor(R.color.colorPrimary)
                                                    .transparentTarget(true)
                                                    .textColor(R.color.white)
                                                    .cancelable(true),
                                            new TapTargetView.Listener() {
                                                @Override
                                                public void onTargetClick(TapTargetView view) {
                                                    super.onTargetClick(view);

                                                }
                                            });
                                }

                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                if (storyFirstRun) {
                    TapTargetView.showFor(StoryActivity.this,
                            TapTarget.forView(findViewById(R.id.view_pager_next),
                                    "Tekan panah untuk melanjutkan ke dialog selanjutnya, anda juga dapat menggeser layar untuk melanjutkan dialog")
                                    .outerCircleColor(R.color.colorPrimary)
                                    .transparentTarget(true)
                                    .textColor(R.color.white)
                                    .cancelable(true),
                            new TapTargetView.Listener() {
                                @Override
                                public void onTargetClick(TapTargetView view) {
                                    super.onTargetClick(view);
//                                    viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
//                                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                                }
                            });
                }
                break;

            case 2:
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                releaseMediaPlayer();

                                // Set The Prev and Next button visibility
                                viewPagerPrev.setVisibility(View.GONE);
                                viewPagerNext.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                // Fill with code to start audio for Fragment 1
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_2_eti);

                                // Set The Prev and Next button visibility
                                viewPagerPrev.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                // Fill with code to end Fragment 2 Audio and start audio for Fragment 3
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_2_ahyani);
                                break;

                            case 3:
                                releaseMediaPlayer();

                                // Set The Prev and Next button visibility
                                viewPagerNext.setVisibility(View.VISIBLE);
                                finishButton.setVisibility(View.GONE);
                                break;

                            default:
                                // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                                releaseMediaPlayer();

                                // Set The Prev and Next button visibility
                                viewPagerNext.setVisibility(View.GONE);
                                finishButton.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;

            case 3:
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                // Fill with code to start audio for Fragment 1
                                viewPagerPrev.setVisibility(View.GONE);
                                break;

                            case 1:
                                viewPagerPrev.setVisibility(View.VISIBLE);
                                viewPagerNext.setVisibility(View.VISIBLE);
                                finishButton.setVisibility(View.GONE);
                                break;

                            default:
                                // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                                viewPagerNext.setVisibility(View.GONE);
                                finishButton.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;

            default:
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                // Fill with code to start audio for Fragment 1
                                viewPagerPrev.setVisibility(View.GONE);
                                break;
                            case 1:
                                // Fill with code to end Fragment 1 Audio and start audio for Fragment 2
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_4_1);
                                viewPagerPrev.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                releaseMediaPlayer();
                                startDialog(R.raw.chapter_4_2);
                                viewPagerNext.setVisibility(View.VISIBLE);
                                finishButton.setVisibility(View.GONE);
                                break;
                            default:
                                // Fill with code to end Fragment 3 Audio and start audio for Fragment 4
                                releaseMediaPlayer();
                                viewPagerNext.setVisibility(View.GONE);
                                finishButton.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down);
    }

    public void viewPagerNext(View view) {
        viewPagerNext = (ImageView) findViewById(R.id.view_pager_next);
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    public void viewPagerPrev(View view) {
        viewPagerPrev = (ImageView) findViewById(R.id.view_pager_prev);
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
            mediaPlayer = MediaPlayer.create(StoryActivity.this, id);

            //Start the audio file
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(completionListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't need it anymore
        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currenly palying sound
        if (mediaPlayer != null) {
            // Regardles of the current state of the media player, release its resources because we no longer need it.
            mediaPlayer.stop();
            mediaPlayer.release();

            // Set media player back to null. For our code, we've decided that setting the media player to null is an easy way
            // to tell that the media player is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public void finish_chapter(View view) {
        switch (chapterNumber) {
            case 1:
                // Remember to uncomment this line
                getSharedPreferences("PREFERENCE_STORY", MODE_PRIVATE).edit()
                        .putBoolean("storyFirstRun", false).apply();
                resultIntent = new Intent(); // Creating an empty Intent
                resultIntent.putExtra("finish", "1");  // Giving the empty Intent a value to pass, indicating chapter 1 is done
                setResult(Activity.RESULT_OK, resultIntent); // Setting result

                finish(); // Close the Activity
                overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down); // Override transition animation
                break;

            case 2:
                resultIntent = new Intent(); // Creating an empty Intent
                resultIntent.putExtra("finish", "2");  // Giving the empty Intent a value to pass, indicating chapter 1 is done
                setResult(Activity.RESULT_OK, resultIntent); // Setting result

                finish(); // Close the Activity
                overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down); // Override transition animation
                break;

            case 3:
                resultIntent = new Intent(); // Creating an empty Intent
                resultIntent.putExtra("finish", "3");  // Giving the empty Intent a value to pass, indicating chapter 1 is done
                setResult(Activity.RESULT_OK, resultIntent); // Setting result

                finish(); // Close the Activity
                overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down); // Override transition animation
                break;

            default:
                resultIntent = new Intent(); // Creating an empty Intent
                resultIntent.putExtra("finish", "4");  // Giving the empty Intent a value to pass, indicating chapter 1 is done
                setResult(Activity.RESULT_OK, resultIntent); // Setting result

                finish(); // Close the Activity
                overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down); // Override transition animation
                break;

        }
    }

}
