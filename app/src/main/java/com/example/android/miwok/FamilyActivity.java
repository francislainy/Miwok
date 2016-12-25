package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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

        ArrayList<Word> wordsList = new ArrayList<Word>();

        for(int i=0; i<defaultWords.length; i++) {
            Word w = new Word(miwokWords[i], defaultWords[i], image_family[i]);
            wordsList.add(w);
        }

        WordAdapter wordAdapter = new WordAdapter(this, wordsList, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

    }

}
