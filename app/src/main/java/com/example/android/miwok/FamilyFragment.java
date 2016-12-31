package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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


    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        String[] defaultWords = new String[]{"father", "mother", "son", "daughter", "older brother", "younger brother",
                "older sister", "younger sister", "grandmother", "grandfather"};

        String[] miwokWords = new String[]{"әpә", "әṭa", "angsi", "tune", "taachi", "chalitti",
                "teṭe", "kolliti", "ama", "paapa"};

        int[] image_family = new int[]{R.drawable.family_father, R.drawable.family_mother, R.drawable.family_son,
                R.drawable.family_daughter, R.drawable.family_older_brother, R.drawable.family_younger_brother,
                R.drawable.family_older_sister, R.drawable.family_younger_sister,
                R.drawable.family_grandmother, R.drawable.family_grandfather};

        int[] audio_family = new int[]{R.raw.family_father, R.raw.family_mother, R.raw.family_son,
                R.raw.family_daughter, R.raw.family_older_brother, R.raw.family_younger_brother,
                R.raw.family_older_sister, R.raw.family_younger_sister, R.raw.family_grandmother, R.raw.family_grandfather};

        final ArrayList<Word> wordsList = new ArrayList<Word>();

        for (int i = 0; i < defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_family[i], audio_family[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(getActivity(), wordsList, R.color.category_family);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Word word = wordsList.get(position);

                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResource());
                    mediaPlayer.start();

                    /**
                     * Listener for executing method once sound is finished playing
                     */
                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });


        return rootView;
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

}
