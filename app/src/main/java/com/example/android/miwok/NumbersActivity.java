package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        String[] defaultWords = new String[]{"one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"};

        final String[] miwokWords = new String[]{"lutti", "otiiko", "tolookosu", "oyyisa", "massokka", "temmokka",
                "kenekaku", "kawinta", "wo'e", "na'aacha"};

        int[] image_numbers = new int[]{R.drawable.number_one, R.drawable.number_two, R.drawable.number_three,
                R.drawable.number_four, R.drawable.number_five, R.drawable.number_six,
                R.drawable.number_seven, R.drawable.number_eight, R.drawable.number_nine, R.drawable.number_ten};

        ArrayList<Word> wordsList = new ArrayList<Word>();

        for (int i = 0; i < defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_numbers[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(NumbersActivity.this, "You Clicked at ",
                        Toast.LENGTH_SHORT).show();
            }
        });

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


