package com.example.jferris.jferris_habittracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.example.jferris.jferris_habittracker.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by jferris on 27/09/16.
 */
public class CreateHabitActivity extends Activity {
    private static final String FILENAME = "habitFile.sav";
    private EditText habitName;
    private EditText habitStartDate;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private CheckBox mondayCB,tuesdayCB, wednesdayCB, thursdayCB, fridayCB, saturdayCB, sundayCB;
    private Calendar myCalendar = Calendar.getInstance();
    LoadSaveHabits loadSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_habit);

        final Button createHabitButton = (Button) findViewById(R.id.createHabitButton);
        habitName = (EditText) findViewById(R.id.enterHabitName);
        habitStartDate = (EditText) findViewById(R.id.habitStartDate);
        createHabitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = habitName.getText().toString();

                Habit newHabit = new Habit(text);
                newHabit.setDate(myCalendar);
                onCheckboxClicked(v, newHabit);

                habitList.add(newHabit);


                loadSave.saveInFile();
            }
        });

        habitStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateHabitActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadSave = new LoadSaveHabits(this, habitList);
        habitList = loadSave.loadFromFile();
        //loadFromFile();
        updateLabel();
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();

            habitList = gson.fromJson(in,listType);

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    //http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        habitStartDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void onCheckboxClicked(View view, Habit habit) {
        // Is the view now checked?
        mondayCB = (CheckBox) findViewById(R.id.mondayCheckBox);
        tuesdayCB = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        wednesdayCB = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        thursdayCB = (CheckBox) findViewById(R.id.thursdayCheckBox);
        fridayCB = (CheckBox) findViewById(R.id.fridayCheckBox);
        saturdayCB = (CheckBox) findViewById(R.id.saturdayCheckBox);
        sundayCB = (CheckBox) findViewById(R.id.sundayCheckBox);
        // Check which checkbox was clicked
        if (mondayCB.isChecked()) {
            habit.setDaysOfWeek(1, true);
        }
        else {
            habit.setDaysOfWeek(1, false);
        }

        if (tuesdayCB.isChecked()) {
            habit.setDaysOfWeek(2, true);
        }
        else {
            habit.setDaysOfWeek(2, false);
        }

        if (wednesdayCB.isChecked()) {
            habit.setDaysOfWeek(3, true);
        }
        else {
            habit.setDaysOfWeek(3, false);
        }

        if (thursdayCB.isChecked()) {
            habit.setDaysOfWeek(4, true);
        }
        else {
            habit.setDaysOfWeek(4, false);
        }

        if (fridayCB.isChecked()) {
            habit.setDaysOfWeek(5, true);
        }
        else {
            habit.setDaysOfWeek(5, false);
        }

        if (saturdayCB.isChecked()) {
            habit.setDaysOfWeek(6, true);
        }
        else {
            habit.setDaysOfWeek(6, false);
        }

        if (sundayCB.isChecked()) {
            habit.setDaysOfWeek(0, true);
        }
        else {
            habit.setDaysOfWeek(0, false);
        }

    }


}
