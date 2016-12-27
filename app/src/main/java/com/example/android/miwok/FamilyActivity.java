package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_family[i], audio_family[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_family);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Word word = wordsList.get(position);
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResource());
                mediaPlayer.start();
            }
        });

    }

}
