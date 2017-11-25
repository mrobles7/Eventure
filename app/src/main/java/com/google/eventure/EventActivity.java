package com.google.eventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventActivity extends RegisterActivity {
    DBHandler db;
    int IDcounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final EditText textEvent = (EditText) findViewById((R.id.txtEvent));
        final EditText textLoc = (EditText) findViewById((R.id.txtLoc));
        final EditText textEndMinute = (EditText) findViewById((R.id.txtEndMinute));
        final EditText textEndHour = (EditText) findViewById((R.id.txtEndHour));
        final EditText textNotif = (EditText) findViewById((R.id.txtNotif));
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
                Event Event1 =new Event(IDcounter += 1 , textYear.getText().toString() , textMonth.getText().toString(), textDay.getText().toString()
                        , textStartHour.getText().toString() ,textStartMinute.getText().toString(),textEndHour.getText().toString()
                        ,textEndMinute.getText().toString(), textNote.getText().toString(), textEvent.getText().toString(), textLoc.getText().toString() );


                db = new DBHandler(getApplicationContext());

                //add the event to database
                long Event1_id = db.createEvent(Event1);

                // if another event is added then add that under the same student
                /*db.createStudentEvent(Student1_id, Event1_id);*/
            }
        });


    }




}
