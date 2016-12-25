package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

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

        ArrayList<Word> wordsList = new ArrayList<Word>();

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_colors[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

    }

}
