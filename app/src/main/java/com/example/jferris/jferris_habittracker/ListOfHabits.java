package com.example.jferris.jferris_habittracker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jferris on 29/09/16.
 * Extends the array adapter class to implement a habit adapter
 * used to list the habits and provides a delete and complete button
 * shows if the habit has been completed for the day or not
 */
public class ListOfHabits extends ArrayAdapter<Habit> {
    private Context context;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();


    public ListOfHabits(Context context, ArrayList<Habit> habitList) {
        super(context, 0, habitList);
        this.context = context;
        this.habitList = habitList;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView habitName = (TextView) convertView.findViewById(R.id.habitItem);
        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_btn);
        Button completeBtn = (Button) convertView.findViewById(R.id.complete_btn);
        Habit habit = getItem(position);
        //Set color for completion
        if(habit.isCompleteToday()) {
            habitName.setBackgroundColor(Color.parseColor("Green"));
        } else {
            habitName.setBackgroundColor(Color.parseColor("Red"));
        }
        habitName.setText(habit.getName());
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                habitList.remove(position);
                notifyDataSetChanged();
            }
            //RelativeLayout listItem = (RelativeLayout) v.getParent();
            //listItem.setBackgroundColor(ContextCompat.getColor(context, R.color.
        });
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                habitList.get(position).addCompletion(calendar);
                notifyDataSetChanged();

            }
        });
        return convertView;

    }

}


