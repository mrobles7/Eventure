package com.google.eventure;

import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by LukeMooney on 12/3/2017.
 */

public class EventSuggest extends DatabaseHelper {

    Student student;
    ArrayList<Event> possibleEvents = new ArrayList<Event>(4);
    Event Event1 = new Event(2017,12,5,3,30,5,30,0,
            0,"Final class","BIO","COB2");
    Event Event2 = new Event(2017,12,6,3,30,6,0,0,
            0,"Final class","BIO","COB2");
    Event Event3 = new Event(2017,12,7,3,30,7,30,0,
            0,"Final class","BIO","COB2");
    Event Event4 = new Event(2017,12,8,3,30,8,0,0,
            0,"Final class","BIO","COB2");


    public EventSuggest(Context context) {
        //doesnt do anything...???
        //lets us add events and stuff
        //(Stops complaining)
        super(context);
        possibleEvents.add(Event1);
        possibleEvents.add(Event2);
        possibleEvents.add(Event3);
        possibleEvents.add(Event4);
    }

    List <Event> GetallEvents(Student student) {
        //returns a list of all of the events a student is currently going to
        this.student = student;
        return getAllEventsByStudent( student.getUsername() );

    }
    List<Event> SuggestEvent(List<Event> CEvents) {
        //returns a list of possible events to go to
        //given what a student is currently going to

        //list of all events a student currently has going on
        List <Event> plannedEvents = getAllEventsByStudent(student.getPassword() );
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
                for(int x = 0; x < plannedEvents.size(); x++){
                    Date planES = plannedEvents.get(x).getStart();
                    Date planEE = plannedEvents.get(x).getEnd();
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
                        PE.remove(i);
                        i--;
                    }
                }
            }
            return PE;
        }
    }

}



