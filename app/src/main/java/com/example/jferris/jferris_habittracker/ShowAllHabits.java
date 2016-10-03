package com.example.jferris.jferris_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jferris on 30/09/16.
 * Lists all the habits the user has created regardless of days they are meant to be used on
 * This allows the user to complete and delete habits that are not listed in the today section
 * From this Activity you may click on any of the listed items to find a list of the completions for that habit
 */
public class ShowAllHabits extends Activity{
    private static final String FILENAME = "habitFile.sav";
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ListOfHabits habitAdapter;
    private ListView dailyHabitsList;
    LoadSaveHabits loadSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habits);
        dailyHabitsList = (ListView) findViewById(R.id.allHabits);
        dailyHabitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ShowAllHabits.this, CompletionListActivity.class);
                intent.putExtra("Habit", position);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadSave = new LoadSaveHabits(this, habitList);
        habitList = loadSave.loadFromFile();
        habitAdapter = new ListOfHabits(this, habitList);
        dailyHabitsList.setAdapter(habitAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadSave.saveInFile();
    }

}
