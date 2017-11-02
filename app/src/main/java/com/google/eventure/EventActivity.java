package com.google.eventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EventActivity extends AppCompatActivity {

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
                new Event(001 , textEvent.getText().toString() , textLoc.getText().toString(), textNote.getText().toString()
                        , textDate.getText().toString() ,textNotif.getText().toString() );

            }
        });
    }




}
