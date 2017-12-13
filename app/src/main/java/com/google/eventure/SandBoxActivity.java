package com.google.eventure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;


public class SandBoxActivity extends LoginActivity {

    LinearLayout mRelativeLayout;

    private Context mContext;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Request window feature action bar
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_box);

        Event Event1 = new Event(2017,12,5,3,30,5,30,0,
                0,"Final class","BIO","COB2");
        Event Event2 = new Event(2017,12,6,3,30,6,0,0,
                0,"Final class","ART","COB2");
        long Event1_id = db.createEvent(Event1, new long[]{student.getID()});
        long Event2_id = db.createEvent(Event2, new long[]{student.getID()});
        // Get the application context
        mContext = getApplicationContext();

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
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, height-=2);
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

            //Gives spacing in between events
            params.leftMargin = 50;
            params.topMargin = 50;

            // Initialize a new TextView to put in CardView
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(params);
            tv.setText(event.getName());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            tv.setTextColor(Color.YELLOW);

            // Put the TextView in CardView
            card.addView(tv);

            // Finally, add the CardView in root layout
            mRelativeLayout.addView(card);

        }
    }
}