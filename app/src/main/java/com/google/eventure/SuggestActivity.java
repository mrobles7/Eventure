package com.google.eventure;

import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuggestActivity extends LoginActivity {

    ArrayList<Event> possibleEvents = new ArrayList<Event>(4);
    Event Event1 = new Event(2017,12,12,3,30,5,30,0,
            0,"Final class","Resume Workshop","COB2");
    Event Event2 = new Event(2017,12,13,3,30,6,0,0,
            0,"Final class","Study group","COB2");
    Event Event3 = new Event(2017,12,14,3,30,7,30,0,
            0,"Final class","Math tutor","COB2");
    Event Event4 = new Event(2017,12,15,3,30,8,0,0,
            0,"Final class","Free Food","COB2");



    List<Event> SuggestEvent() throws ParseException {
        //returns a list of possible events to go to
        //given what a student is currently going to
        //list of all events a student currently has going on
        DatabaseHelper db;
        db = new DatabaseHelper(getApplicationContext());
        List <Event> plannedEvents = db.getAllEventsByStudent(student.getPassword());
        //list of events a student could possibly go to
        ArrayList<Event> PE = new ArrayList<Event>();
        //populate the list with all possible events
        for( int i = 0; i < possibleEvents.size(); i ++ ) {
            PE.add(possibleEvents.get(i));
        }

        //if the student has nothing planned return all the events.
        if(plannedEvents == null) {return PE;}
        else
        {//otherwise trim the list
            for(int i  = 0; i < PE.size(); i ++) {
                Date PES = PE.get(i).getStart();
                Date PEE = PE.get(i).getEnd();
                for(Event events : plannedEvents){
                    Date planES=null;
                    DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd/kk/mm");
                    try{
                    planES =df.parse(events.getyear()+"/"+events.getmonth()+"/"+events.getday()+"/"
                            +events.gethour()+"/"+events.getminute());
                    }catch(Exception EX) {
                        System.out.println("System failed to parse the date\nError Code: " + EX);

                    }

                    Date planEE =null;
                    DateFormat de = new java.text.SimpleDateFormat("yyyy/MM/dd/kk/mm");
                    try{
                        planEE=de.parse(events.getyear()+"/"+events.getmonth()+"/"+events.getday()+"/"+events.getEHour()+"/"+events.getEMinute());
                    }catch(Exception EX) {
                        System.out.println("System failed to parse the date\nError Code: " + EX);

                    }

                    //if any times match, remove the event,
                    //if the start or end time of the possible events
                    //is between the start and end time of a class,
                    //remove the event
                    if ( (PES.equals(planES) || PES.equals(planEE) ||
                            PEE.equals(planEE) || PEE.equals(planES))
                            ||(planES.before(PES) && planEE.after(PES) )
                            || ( planES.before(PEE) && planEE.after(PEE) )
                            || ( planES.after(PES) && planEE.before(PEE) ))
                    {
                        if(i!=-1) {
                            PE.remove(i);
                            i--;
                        }
                    }
                }
            }
            return PE;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        possibleEvents.add(Event1);
        possibleEvents.add(Event2);
        possibleEvents.add(Event3);
        possibleEvents.add(Event4);

        LinearLayout mRelativeLayout; //a linear layout named relative layout

        Context mContext;

        // Request window feature action bar
//        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        // Get the application context
        mContext = getApplicationContext();

        // Change the action bar color
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        // Get the widgets reference from XML layout
        mRelativeLayout = (LinearLayout) findViewById(R.id.rl_Container);
        int height =-2;


        List<Event> events = null;
        try {
            events = SuggestEvent();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

                // Gives some space between the events.
                params.leftMargin = 50;
                params.topMargin = 50;

                // Initialize a new TextView to put in CardView
                TextView tv = new TextView(mContext);
                tv.setLayoutParams(params);
                tv.setText(event.getName() +"\n" +"Date: " +event.getmonth()+"/"+event.getday()+"/"+event.getyear()
                        + "Start Time " + event.gethour() + ":" + event.getminute() + "\n" + " End Time " +
                        event.getEHour() + ":" + event.getEMinute());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                tv.setTextColor(Color.YELLOW);

                // Put the TextView in CardView
                card.addView(tv);

                // Finally, add the CardView in root layout
                mRelativeLayout.addView(card);
                card.setOnClickListener(new CardView.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        event.setId(db.createEvent(event, new long[]{student.getID()}));
                        Intent intent = new Intent(SuggestActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

    }


