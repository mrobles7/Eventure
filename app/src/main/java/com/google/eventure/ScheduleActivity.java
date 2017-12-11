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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Window;
import java.util.Calendar;
import java.text.*;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends LoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        final TextView Date = (TextView) findViewById(R.id.date);
        final Button buttonSuggest = (Button) findViewById(R.id.btnSuggest);
        final Button buttonEditEvent = (Button) findViewById(R.id.buttonEdit);

        LinearLayout mRelativeLayout;

        Context mContext;

            // Request window feature action bar
//           requestWindowFeature(Window.FEATURE_ACTION_BAR);


            // Get the application context
            mContext = getApplicationContext();

            // Change the action bar color
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

            // Get the widgets reference from XML layout
            mRelativeLayout = (LinearLayout) findViewById(R.id.rl_Container);
            int height =-2;

            DatabaseHelper db;
            db = new DatabaseHelper(getApplicationContext());
            List<Event> events = db.getAllEventsByStudent( student.getPassword() );
            for (Event event : events) {

                // Initialize a new CardView
                CardView card = new CardView(mContext);

                // Set the CardView layoutParams
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height-=2);
                card.setLayoutParams(params);

                // Set CardView corner radius
                card.setRadius(9);

                // Set cardView content padding
                card.setContentPadding(15, 15, 15, 15);

                // Set a background color for CardView
                card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

                // Set the CardView maximum elevation
                card.setMaxCardElevation(15);

                // Set CardView elevation
                card.setCardElevation(9);

                // Initialize a new TextView to put in CardView
                TextView tv = new TextView(mContext);
                tv.setLayoutParams(params);
                tv.setText(event.getName());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                tv.setTextColor(Color.RED);

                // Put the TextView in CardView
                card.addView(tv);

                // Finally, add the CardView in root layout
                mRelativeLayout.addView(card);

            }
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
