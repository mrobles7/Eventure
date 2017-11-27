package com.google.eventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        String currentDateString = DateFormat.getDateInstance().format(new Date());

        // textView is the TextView view that should display it
        Date.setText(currentDateString);


    }



}
