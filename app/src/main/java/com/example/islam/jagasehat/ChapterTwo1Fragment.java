package com.example.islam.jagasehat;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterTwo1Fragment extends Fragment {

    //Use MediaPlayer API and create an global variable for it
    private MediaPlayer mediaPlayer;

    //handles audio focus when playing a sound file
    private AudioManager audioManager;

    public ChapterTwo1Fragment() {
        // Required empty public constructor
    }

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create and setup the AudioManager to request audio focus
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        releaseMediaPlayer();

        int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                //Use the music stream
                AudioManager.STREAM_MUSIC,
                //Request permanent focus
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Create and setup the MediaPlayer for the audio resource associated with the current word
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chapter_2_eti);

            //Start the audio file
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(completionListener);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_two1, container, false);
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
            mediaPlayer.release();

            // Set media player back to null. For our code, we've decided that setting the media player to null is an easy way
            // to tell that the media player is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
