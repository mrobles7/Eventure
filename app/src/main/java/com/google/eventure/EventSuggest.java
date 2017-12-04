package com.google.eventure;

import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
/**
 * Created by LukeMooney on 12/3/2017.
 */

public class EventSuggest extends DatabaseHelper {


    public EventSuggest(Context context) {
        super(context);
    }

    List <Event> GetallEvents(Student student) {

        return getAllEventsByStudent(student.getPassword());

    }

}



