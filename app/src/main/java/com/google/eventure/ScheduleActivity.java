package com.google.eventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.text.*;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        final TextView Date = (TextView) findViewById(R.id.date);
        final Button buttonSuggest = (Button) findViewById(R.id.btnSuggest);
        final Button buttonEditEvent = (Button) findViewById(R.id.buttonEdit);


        String currentDateString = DateFormat.getDateInstance().format(new Date());

        // textView is the TextView view that should display it
        Date.setText(currentDateString);


        buttonSuggest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //We say we are in LoginActivity and to open the intent
                Intent EditIntent = new Intent(ScheduleActivity.this, SuggestActivity.class);
                ScheduleActivity.this.startActivity(EditIntent);
            }
        });

        buttonEditEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //An intent opens the register activity
                //We say we are in LoginActivity and to open the intent
                Intent EditIntent = new Intent(ScheduleActivity.this, EventActivity.class);
                ScheduleActivity.this.startActivity(EditIntent);
            }
        });

    }
}
