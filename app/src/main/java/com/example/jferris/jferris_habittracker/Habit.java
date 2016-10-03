package com.example.jferris.jferris_habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jferris on 22/09/16.
 * This is the main habit class used to model a habit
 * Habits have names, a creation date, a list of which days they are to be done and a list of past completions
 * getters and setters are made for each of the above fields
 * methods are provided for toString(), check if complete for the day and check if the habit is to be completed for the day of the week
 */
public class Habit {
    private String name;
    private Calendar date;
    private Boolean[] daysOfWeek;
    private ArrayList<Calendar> completionList;

    public Habit(String name) {
        this.name = name;
        this.date = Calendar.getInstance();
        this.completionList = new ArrayList<Calendar>();
        this.daysOfWeek = new Boolean[7];
        this.daysOfWeek[0] = false;
        this.daysOfWeek[1] = false;
        this.daysOfWeek[2] = false;
        this.daysOfWeek[3] = false;
        this.daysOfWeek[4] = false;
        this.daysOfWeek[5] = false;
        this.daysOfWeek[6] = false;
    }
    //sunday=0 monday=1...saturday=6
    public void setDaysOfWeek(int index, Boolean value) {
        this.daysOfWeek[index] = value;
    }

    public String getName() {
        return this.name;
    }

    public Calendar getDate() {
        return this.date;
    }

    public void setDate(Calendar calendar) {
        this.date = calendar;
    }

    public Boolean[] getDays() {
        return this.daysOfWeek;
    }

    public ArrayList<Calendar> getCompletionList() {
        return completionList;
    }
    //override to string to allow printing of habit
    @Override
    public String toString() {
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yy");
        String formatted = format1.format(date.getTime());
        return formatted + " " + name;
    }
    //Check if needs to be completed for day of week
    public Boolean isToday() {
        Calendar todayCalendar = Calendar.getInstance();
        return daysOfWeek[todayCalendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    public void addCompletion(Calendar calendar) {
        completionList.add(calendar);
    }

    public void removeCompletion(int position) {
        completionList.remove(position);
    }

    public int getNumCompletions() {
        return completionList.size();
    }
    //Has the habit been completed yet today
    public boolean isCompleteToday() {
        if(completionList.size() == 0) return false;
        else {
            Calendar today = Calendar.getInstance();
            Calendar completion = completionList.get(completionList.size()-1);
            boolean sameDay = today.get(Calendar.YEAR) == completion.get(Calendar.YEAR) &&
                    today.get(Calendar.DAY_OF_YEAR) == completion.get(Calendar.DAY_OF_YEAR);
            return sameDay;
        }
    }

}
