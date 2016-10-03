package com.example.jferris.jferris_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CompletionListActivity extends Activity {
    private TextView completionTitle;
    private TextView numberCompletions;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ListOfCompletions habitAdapter;
    private ListView dailyHabitsList;
    private LoadSaveHabits loadSave;
    private int position;
    private Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_list);
        dailyHabitsList = (ListView) findViewById(R.id.habitCompletions);
        //get position of habit
        Intent intent= getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null)
        {
            position =(int) extras.getInt("Habit");
        }

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //get current habit
        loadSave = new LoadSaveHabits(this, habitList);
        habitList = loadSave.loadFromFile();
        habit = habitList.get(position);
        //set habit title
        completionTitle = (TextView) findViewById(R.id.completionListTitle);
        completionTitle.setText(habit.getName());
        updateTitle();
        habitAdapter = new ListOfCompletions(this, habit.getCompletionList());
        dailyHabitsList.setAdapter(habitAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadSave.saveInFile();
    }

    public void updateTitle() {
        numberCompletions = (TextView) findViewById(R.id.numberOfCompletions);
        int numComp = habit.getNumCompletions();
        numberCompletions.setText("Number of Completions: " + numComp);
    }
}

