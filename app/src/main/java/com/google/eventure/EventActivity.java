package com.google.eventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventActivity extends ScheduleActivity {
    DatabaseHelper db;

    int id, year, month, day, SHour, SMinute, EHour, EMinute, NHour, NMinute;
    Event Event1;
    String location, description, Ename, SDate, EDate, NDate;

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
             Button buttonSave = (Button) findViewById(R.id.btnSave);
             Button buttonScrap = (Button) findViewById((R.id.btnScrap));


            if(ev!=null) {

                textLoc.setText(ev.getlocation(), TextView.BufferType.EDITABLE);
                textYear.setText(Integer.toString(ev.getyear()), TextView.BufferType.EDITABLE);
                textEndHour.setText(Integer.toString(ev.getEHour()), TextView.BufferType.EDITABLE);
                textEndMinute.setText(Integer.toString(ev.getEMinute()), TextView.BufferType.EDITABLE);
                textMonth.setText(Integer.toString(ev.getmonth()), TextView.BufferType.EDITABLE);
                textStartHour.setText(Integer.toString(ev.gethour()), TextView.BufferType.EDITABLE);
                textStartMinute.setText(Integer.toString(ev.getminute()), TextView.BufferType.EDITABLE);
                textDay.setText(Integer.toString(ev.getday()), TextView.BufferType.EDITABLE);
                textEvent.setText(ev.getName(), TextView.BufferType.EDITABLE);
                textNotifHour.setText(Integer.toString(ev.getNhour()), TextView.BufferType.EDITABLE);
                textNotifMinute.setText(Integer.toString(ev.getNminute()), TextView.BufferType.EDITABLE);
            }
            else{
                textLoc.setText(null);textYear.setText(null);
                textEndHour.setText(null);textEndMinute.setText(null);
                textMonth.setText(null);textStartHour.setText(null);
                textStartMinute.setText(null);textDay.setText(null);
                textEvent.setText(null);textNotifHour.setText(null);
                textNotifMinute.setText(null);
            }


        buttonScrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Event Deleted.", Toast.LENGTH_LONG).show();
            if(ev!=null) {
                db = new DatabaseHelper(getApplicationContext());
                db.deleteEvent(ev.getId());
                Intent EventIntent = new Intent(EventActivity.this, ScheduleActivity.class);
                startActivity(EventIntent);
            }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            //This will happen when clicked
            public void onClick(View v) {

                try {
                    // Make sure everything can be parsed correctly
                    year = Integer.parseInt(textYear.getText().toString());
                    month = Integer.parseInt(textMonth.getText().toString());
                    day = Integer.parseInt(textDay.getText().toString());
                    SHour = Integer.parseInt(textStartHour.getText().toString());
                    SMinute = Integer.parseInt(textStartMinute.getText().toString());
                    EHour = Integer.parseInt(textEndHour.getText().toString());
                    EMinute = Integer.parseInt(textEndMinute.getText().toString());
                    NHour = Integer.parseInt(textNotifHour.getText().toString());
                    NMinute = Integer.parseInt(textNotifMinute.getText().toString());
                    int Repeat = Integer.parseInt((textRepeat.getText().toString()))+1;

                    //create a new list of events to be added

                            // then create the event
                            Event1 = new Event(year, month, (day ), SHour, SMinute, EHour, EMinute, NHour, NMinute,
                                    textNote.getText().toString(), textEvent.getText().toString(), textLoc.getText().toString());

                            db = new DatabaseHelper(getApplicationContext());
                            Event1.setId(db.createEvent(Event1, new long[]{student.getID()}));



                            ev.setEminute(EMinute);
                            ev.setName(textEvent.getText().toString());ev.setYear(year);
                            ev.setlocation(textLoc.getText().toString());ev.setmonth(month);
                            ev.setday(day);ev.sethour(SHour);ev.setMinute(SMinute);
                            ev.setEhour(EHour);
                            ev.setEminute(EMinute);
                            db.updateEvent(ev);


                  //set values back to null
                    Toast.makeText(getApplicationContext(), "event succesfully added.", Toast.LENGTH_LONG).show();

                    } catch(Exception EX){

                    }

                Intent EventIntent = new Intent(EventActivity.this, ScheduleActivity.class);
                startActivity(EventIntent);
            }
        });
    }
}
