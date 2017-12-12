package com.google.eventure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager; // Added in for use to prevent keyboard pop up
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Window;

import java.sql.Struct;
import java.util.Calendar;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class ScheduleActivity extends LoginActivity {

    public Student St = student;
    public static Event ev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Makes sure that the keyboard doesn't automatically pops up
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //this is a containment class.  It's purpose is to
        //contain the displacement from the current date to
        //the date shown in the app and the date being shown
        final class displace {
            //how far away from today are we?
            int displacement = 0;
            //todays date, date currently being shown
            Date TodayDate,disDate;
            displace(Date d){
                TodayDate = d;
                disDate = d;
            }
            public void Inc(){
                displacement++;
            }
            public void Dec(){
                displacement--;
            }
            Date ChangeDate(){
                Calendar c = Calendar.getInstance();
                c.setTime(TodayDate);
                c.add(Calendar.DATE, displacement);
                return disDate = c.getTime();
            }
            int getDOM(){
                Calendar c = Calendar.getInstance();
                c.setTime(disDate);
                return c.get(Calendar.DAY_OF_MONTH);
            }
            int getMON(){
                Calendar c = Calendar.getInstance();
                c.setTime(disDate);
                return c.get(Calendar.MONTH);
            }

        }


        final Date Now = new Date();
        final displace D = new displace(Now);
        final String currentDateString = DateFormat.getDateInstance().format(Now);
        final TextView textDate = (TextView) findViewById(R.id.txtDate);
        final Button buttonSuggest = (Button) findViewById(R.id.btnSuggest);
        final Button buttonEditEvent = (Button) findViewById(R.id.buttonEdit);
        final Button buttonPrev = (Button) findViewById((R.id.btnPrev));
        final Button buttonNext = (Button) findViewById((R.id.btnNext));

        LinearLayout mRelativeLayout;

        Context mContext;

            // Request window feature action bar
//           requestWindowFeature(Window.FEATURE_ACTION_BAR);


            // Get the application context
            mContext = getApplicationContext();

            // Change the action bar color
//            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

            // Get the widgets reference from XML layout
            mRelativeLayout = (LinearLayout) findViewById(R.id.rl_Container);
            int height =-2;

            final DatabaseHelper db;
            db = new DatabaseHelper(getApplicationContext());
            List<Event> events = db.getAllEventsByStudent(student.getPassword() );
            /*  int EGD, EGM, DGD, DGM;
              EGD = event.getday();
              EGM = event.getmonth();
              DGD = D.getDOM();
              DGM = D.getMON();
              if( event.getday() == D.getDOM() && event.getmonth() == D.getMON() )*/
        for (final Event event : events) {
            // Initialize a new CardView
            CardView card = new CardView(mContext);

            // Set the CardView layoutParams
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height -= 2);
            card.setLayoutParams(params);

            // Set CardView corner radius
            card.setRadius(9);

            // Set cardView content padding
            card.setContentPadding(15, 15, 15, 15);

            // Set a background color for CardView
            card.setCardBackgroundColor(Color.parseColor("#00008B"));

            // Set the CardView maximum elevation
            card.setMaxCardElevation(15);

            // Set CardView elevation
            card.setCardElevation(9);

            // Gives spacing in between events
            params.leftMargin = 50;
            params.topMargin = 50;

            // Initialize a new TextView to put in CardView
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(params);
            tv.setText(event.getName() + "\n" + "Start Time " + event.gethour() + ":" + event.getminute() + "\n" + " End Time " +
                    event.getEHour() + ":" + event.getEMinute());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
            tv.setTextColor(Color.YELLOW);

            // Put the TextView in CardView
            card.addView(tv);

            // Finally, add the CardView in root layout
            mRelativeLayout.addView(card);
            card.setOnClickListener(new CardView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ev = event;
                    Intent intent = new Intent(ScheduleActivity.this, EventActivity.class);
                    startActivity(intent);
                }
            });
        }


        // textView is the TextView view that should display it
        textDate.setText(currentDateString);


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
        buttonPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //get the date in the top date counter to be one day prev
                //start by decrementing the displacement variable
                D.Dec();
                //then change the date, and update the front.
                textDate.setText(DateFormat.getDateInstance().format(D.ChangeDate()));
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //get the date in the top date counter to be the next day
                //start by incrementing the variable
                D.Inc();
                //then change the date and update the front
                textDate.setText(DateFormat.getDateInstance().format(D.ChangeDate()));
            }
        });
    }
}
