package com.google.eventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends LoginActivity {
    DatabaseHelper db;
    Event event;
    public void setEvent(Event e){
        event=e;
    }

    public Event getEvent(){
        return this.event;
    }



    static int IDcounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);



            final EditText textEvent = (EditText) findViewById((R.id.txtEvent));
            final EditText textLoc = (EditText) findViewById((R.id.txtLoc));
            final EditText textEndMinute = (EditText) findViewById((R.id.txtEndMinute));
            final EditText textEndHour = (EditText) findViewById((R.id.txtEndHour));
            final EditText textNotifHour = (EditText) findViewById((R.id.txtNotifHour));
            final EditText textNotifMinute = (EditText) findViewById((R.id.txtNotifMinute));
            final EditText textYear = (EditText) findViewById((R.id.txtYear));
            final EditText textMonth = (EditText) findViewById((R.id.txtMonth));
            final EditText textDay = (EditText) findViewById((R.id.txtDay));
            final EditText textStartHour = (EditText) findViewById((R.id.txtStartHour));
            final EditText textStartMinute = (EditText) findViewById((R.id.txtStartMinute));
            final EditText textNote = (EditText) findViewById((R.id.txtNote));
            final EditText textRepeat = (EditText) findViewById((R.id.txtRepeat));
            final Button buttonSave = (Button) findViewById(R.id.btnSave);
            final Button buttonScrap = (Button) findViewById((R.id.btnScrap));

        if(event!=null){

            textMonth.setText(event.getmonth());

        }


        buttonScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Event Deleted.", Toast.LENGTH_LONG).show();

                Intent EventIntent = new Intent(EventActivity.this, ScheduleActivity.class);
                startActivity(EventIntent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            //This will happen when clicked
            public void onClick(View v) {

                try {
                    // Make sure everything can be parsed correctly
                    int year = Integer.parseInt(textYear.getText().toString());
                    int month = Integer.parseInt(textMonth.getText().toString());
                    int day = Integer.parseInt(textDay.getText().toString());
                    int SHour = Integer.parseInt(textStartHour.getText().toString());
                    int SMinute = Integer.parseInt(textStartMinute.getText().toString());
                    int EHour = Integer.parseInt(textEndHour.getText().toString());
                    int EMinute = Integer.parseInt(textEndMinute.getText().toString());
                    int NHour = Integer.parseInt(textNotifHour.getText().toString());
                    int NMinute = Integer.parseInt(textNotifMinute.getText().toString());
                    int Repeat = Integer.parseInt((textRepeat.getText().toString()));

                    //create a new list of events to be added
                    ArrayList<Event> toAdd = new ArrayList<>(Repeat);

                    for(int i = 0; i < Repeat; i ++) {
                        // then create the event
                        Event Event1 = new Event(IDcounter += 1, year, month, (day + 7*i ) , SHour, SMinute, EHour, EMinute, NHour, NMinute,
                                textNote.getText().toString(), textEvent.getText().toString(), textLoc.getText().toString());
                        toAdd.add(Event1);
                    }
                    db = new DatabaseHelper(getApplicationContext());

                    for(Event e: toAdd ) {
                        // add the event to database
                        long Event1_id = db.createEvent(e, new long[]{student.getID()});
                        }
                    if (toAdd.size() > 0) {
                        Toast.makeText(getApplicationContext(), "event succesfully added.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception EX) {
                    Toast.makeText(getApplicationContext(), "Please make sure that all values for time & date are integer values.", Toast.LENGTH_LONG).show();
                }

                //---------LIST OF ALL EVENTS ADDED -------------///
                DatabaseHelper db;
                db = new DatabaseHelper(getApplicationContext());
                List<Event> events = db.getAllEventsByStudent( student.getPassword() );
                for (Event event : events) {

                        event.getName();
                        event.getday();
                        event.getDescription();
                    }


                Intent EventIntent = new Intent(EventActivity.this, ScheduleActivity.class);
                startActivity(EventIntent);
            }
        });
    }
}
