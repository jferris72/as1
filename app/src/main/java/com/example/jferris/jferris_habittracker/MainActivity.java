package com.example.jferris.jferris_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This is the Main Activity for the app
 * This activity lists the habits that are to be completed for the day
 * The user may complete or delete habits shown on the list
 * There is a drop down menu that allows the user to navigate to the create habit activity or showallhabits activity
 */

public class MainActivity extends Activity {
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayList<Habit> todayHabitList;
    private ListOfHabits habitAdapter;
    private ListView dailyHabitsList;
    private LoadSaveHabits loadSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dailyHabitsList = (ListView) findViewById(R.id.dailyHabits);
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadSave = new LoadSaveHabits(this, todayHabitList);
        habitList = loadSave.loadFromFile();
        todayHabitList = new ArrayList<Habit>();
        int i;
        for(i = 0; i<habitList.size(); i++) {
            if(habitList.get(i).isToday() == true) {
                todayHabitList.add(habitList.get(i));
            }
        }
        //loadFromFile();
        habitAdapter = new ListOfHabits(this, todayHabitList);
        dailyHabitsList.setAdapter(habitAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadSave = new LoadSaveHabits(this, todayHabitList);
        int i;
        for(i = 0; i<habitList.size(); i++) {
            if(habitList.get(i).isToday() == false) {
                todayHabitList.add(habitList.get(i));
            }
        }
        loadSave.saveInFile();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    public void createHabitIntent(MenuItem menu) {
        Toast.makeText(this, "Habit Create", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, CreateHabitActivity.class);
        startActivity(intent);
    }

    public void allHabitIntent(MenuItem menu) {
        Toast.makeText(this, "Show All Habits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ShowAllHabits.class);
        startActivity(intent);
    }


}
