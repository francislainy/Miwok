package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        String[] defaultWords = new String[]{"red", "green", "brown", "gray", "black", "white",
                "dusty yellow", "mustart yellow"};

        String[] miwokWords = new String[]{"weṭeṭṭi", "chokokki", "ṭakaakki", "ṭopoppi", "kululli", "kelelli",
                "ṭopiisә", "chiwiiṭә"};

        int[] image_colors = new int[]{R.drawable.color_red, R.drawable.color_green, R.drawable.color_brown,
                R.drawable.color_gray, R.drawable.color_black, R.drawable.color_white,
                R.drawable.color_dusty_yellow, R.drawable.color_mustard_yellow};

        int[] audio_colors = new int[]{R.raw.color_red, R.raw.color_green, R.raw.color_brown,
                R.raw.color_gray, R.raw.color_black, R.raw.color_white,
                R.raw.color_dusty_yellow, R.raw.color_mustard_yellow};

        final ArrayList<Word> wordsList = new ArrayList<Word>();

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_colors[i], audio_colors[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_colors);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Word word = wordsList.get(position);
                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getmAudioResource());
                mediaPlayer.start();
            }
        });

    }

}
