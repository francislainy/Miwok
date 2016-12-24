package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        String[] words = new String[]{"one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"};

        ArrayList<String> wordsList = new ArrayList<String>();
        for(String word : words){
            wordsList.add(word);
        }

        LinearLayout rootview = (LinearLayout) findViewById(R.id.rootView);
//
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(itemsAdapter);

        /**Add words to textview on the LinearLayout
         *
         */
        for(String word : wordsList){
            TextView wordView = new TextView(this);
            wordView.setText(word);
            rootview.addView(wordView);
            Log.v("NumbersActivity", "Word on the list: " + word);
        }


    }

}
