package com.google.eventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventActivity extends RegisterActivity {
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final EditText textEvent = (EditText) findViewById((R.id.txtEvent));
        final EditText textLoc = (EditText) findViewById((R.id.txtLoc));
        final EditText textStart = (EditText) findViewById((R.id.txtStart));
        final EditText textEnd = (EditText) findViewById((R.id.txtEnd));
        final EditText textNotif = (EditText) findViewById((R.id.txtNotif));
        final EditText textDate = (EditText) findViewById((R.id.txtDate));
        final EditText textNote = (EditText) findViewById((R.id.txtNote));
        final Button buttonSave = (Button) findViewById(R.id.btnSave);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            //This will happen when clicked
            public void onClick(View v) {
                //--------MAKE SURE TO MAKE A REAL EVENT ID ASSIGNER BEFORE SHIPPING ---------
                Event Event1 =new Event(001 , textEvent.getText().toString() , textLoc.getText().toString(), textNote.getText().toString()
                        , textDate.getText().toString() ,textNotif.getText().toString(),textStart.getText().toString()
                        ,textEnd.getText().toString() );

                db = new DBHandler(getApplicationContext());

                //add the event to database
                long Event1_id = db.createEvent(Event1);

                //insert student in database with Event
                Student1_id = db.createStudent(Student1,new long[]{Event1_id});


                // if another event is added then add that under the same student
                /*db.createStudentEvent(Student1_id, Event1_id);*/
            }
        });


    }




}
