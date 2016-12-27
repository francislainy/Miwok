package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        String[] defaultWords = new String[]{"Where are you going?", "What is your name?", "My name is...", "How are you feeling?", "I’m feeling good.", "Are you coming?",
                "Yes, I’m coming.", "I’m coming.", "Let’s go.", "Come here."};

        String[] miwokWords = new String[]{"minto wuksus", "tinnә oyaase'nә", "oyaaset...", "michәksәs?", "kuchi achit", "әәnәs'aa?",
                "hәә’ әәnәm", "әәnәm", "yoowutis", "әnni'nem"};

        int[] audio_phrases = new int[]{R.raw.phrase_where_are_you_going, R.raw.phrase_what_is_your_name, R.raw.phrase_my_name_is,
                R.raw.phrase_how_are_you_feeling, R.raw.phrase_im_feeling_good, R.raw.phrase_are_you_coming,
                R.raw.phrase_yes_im_coming, R.raw.phrase_im_coming, R.raw.phrase_lets_go, R.raw.phrase_come_here};

        final ArrayList<Word> wordsList = new ArrayList<Word>();

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], audio_phrases[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_phrases);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Word word = wordsList.get(position);
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResource());
                mediaPlayer.start();
            }
        });

    }

}
