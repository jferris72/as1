package com.example.jferris.jferris_habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jferris on 02/10/16.
 */
public class ListOfCompletions extends ArrayAdapter<Calendar> {
    Context context;
    ArrayList<Calendar> completionList = new ArrayList<Calendar>();

    public ListOfCompletions(Context context, ArrayList<Calendar> habitList) {
        super(context, 0, habitList);
        this.context = context;
        this.completionList = habitList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.completion_list_item, parent, false);
        }
        TextView completionName = (TextView) convertView.findViewById(R.id.completionItem);
        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_btn);
        Calendar completion = getItem(position);
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        String formatted = format1.format(completion.getTime());
        completionName.setText(formatted);
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                completionList.remove(position);
                //http://stackoverflow.com/questions/12142255/call-activity-method-from-adapter
                if(context instanceof CompletionListActivity){
                    ((CompletionListActivity)context).updateTitle();
                }
                notifyDataSetChanged();
            }
        });
        return convertView;

    }

}
