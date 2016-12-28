package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

//        /**
//         * Display up navigation, allowing user to get back to the activity's parent
//         */
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        String[] defaultWords = new String[]{"one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"};

        final String[] miwokWords = new String[]{"lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka",
                "kenekaku", "kawinta", "wo'e", "na'aacha"};

        int[] image_numbers = new int[]{R.drawable.number_one, R.drawable.number_two, R.drawable.number_three,
                R.drawable.number_four, R.drawable.number_five, R.drawable.number_six,
                R.drawable.number_seven, R.drawable.number_eight, R.drawable.number_nine, R.drawable.number_ten};

        int[] audio_numbers = new int[]{R.raw.number_one, R.raw.number_two, R.raw.number_three,
                R.raw.number_four, R.raw.number_five, R.raw.number_six,
                R.raw.number_seven, R.raw.number_eight, R.raw.number_nine, R.raw.number_ten};

        final ArrayList<Word> wordsList = new ArrayList<Word>();

        for (int i = 0; i < defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_numbers[i], audio_numbers[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Word word = wordsList.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResource());
                    mediaPlayer.start();

                    /**
                     * Listener for executing method once sound is finished playing
                     */
                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /**Add words to textview on the LinearLayout
     *
     */
//        LinearLayout rootview = (LinearLayout) findViewById(R.id.rootView);
//        for(String word : wordsList){
//            TextView wordView = new TextView(this);
//            wordView.setText(word);
//            rootview.addView(wordView);
//            Log.v("NumbersActivity", "Word on the list: " + word);
//        }

}


