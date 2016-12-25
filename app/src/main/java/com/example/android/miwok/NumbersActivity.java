package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        String[] defaultWords = new String[]{"one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"};

        String[] miwokWords = new String[]{"lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka",
                "kenekaku", "kawinta", "wo'e", "na'aacha"};

        ArrayList<Word> wordsList = new ArrayList<Word>();

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

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

}
