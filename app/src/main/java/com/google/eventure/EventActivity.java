package com.google.eventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventActivity extends RegisterActivity {
    DatabaseHelper db;
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
        final Button buttonSave = (Button) findViewById(R.id.btnSave);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            //This will happen when clicked
            public void onClick(View v) {

                try {
                    // Make sure everything can be parsed correctly
                    int year = Integer.parseInt( textYear.getText().toString() );
                    int month = Integer.parseInt( textMonth.getText().toString() );
                    int day = Integer.parseInt( textDay.getText().toString() );
                    int SHour = Integer.parseInt( textStartHour.getText().toString() );
                    int SMinute = Integer.parseInt( textStartMinute.getText().toString() );
                    int EHour = Integer.parseInt( textEndHour.getText().toString() );
                    int EMinute = Integer.parseInt( textEndMinute.getText().toString() );
                    int NHour = Integer.parseInt( textNotifHour.getText().toString() );
                    int NMinute = Integer.parseInt( textNotifMinute.getText().toString() );
                    // then create the event
                    Event Event1 = new Event(IDcounter += 1, year, month, day, SHour, SMinute, EHour , EMinute, NHour,NMinute,
                            textNote.getText().toString(), textEvent.getText().toString(), textLoc.getText().toString());
                    db = new DatabaseHelper(getApplicationContext());
                    // and add the event to database
                    long Event1_id = db.createEvent(Event1);

                } catch (Exception EX)
                {
                    Toast.makeText(getApplicationContext(), "Please make sure that all values for time & date are integer values.", Toast.LENGTH_LONG).show();
                }



                // if another event is added then add that under the same student
                /*db.createStudentEvent(Student1_id, Event1_id);*/
            }
        });


    }




}
