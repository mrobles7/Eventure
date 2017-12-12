package com.google.eventure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ReignNelson on 11/20/17.
 */

public class DatabaseHelper  extends SQLiteOpenHelper {


    private Student student;
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database";

    // Table Names
    private static final String TABLE_EVENT = "EVENT"; //Event
    private static final String TABLE_STUDENT = "STUDENT"; //Student
    private static final String TABLE_STUDENT_EVENT = "student_event";//Student_Event

    // Common column names
    private static final String KEY_ID = "id";


    // NOTES Table - column nmaes
    private static final String KEY_Name = "name";
    private static final String KEY_Username = "Username";
    private static final String KEY_password= "password";


    // Event Table - column names
    private static final String  KEY_year = "year";
    private static final String  KEY_month = "month";
    private static final String  KEY_day = "day";
    private static final String  KEY_hour = "hour";
    private static final String  KEY_minute = "minute";
    private static final String  KEY_location = "location";
    private static final String  KEY_description = "description";
    private static final String  KEY_Ename = "Ename";
    private static final String  KEY_notification= "notification";
    private static final String  KEY_Ehour= "Ehour";
    private static final String  KEY_Eminute= "Eminute";


    // NOTE_TAGS Table - column names
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_EVENT_ID = "event_id";

    public static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Name
            + " TEXT," + KEY_Username + " Text," + KEY_password
            + " Text" + ")";


    public static final String CREATE_TABLE_EVENT = "CREATE TABLE " + TABLE_EVENT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_year+ " INTEGER,"
            + KEY_month+ " INTEGER," + KEY_day+ " INTEGER," + KEY_hour+ " INTEGER,"+ KEY_Ename+" Text,"
            + KEY_minute+ " INTEGER," + KEY_location + " Text," + KEY_Ehour+ " INTEGER," +KEY_Eminute+" INTEGER,"
            + KEY_notification + " TEXT," + KEY_description + " Text"
            + ")";

    public static final String CREATE_TABLE_STUDENT_EVENT = "CREATE TABLE "
            + TABLE_STUDENT_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STUDENT_ID + " INTEGER," + KEY_EVENT_ID + " INTEGER"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_STUDENT_EVENT);

        Log.d("Created", "Created Both the Tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_EVENT);

        // create new tables
        onCreate(db);

    }

    public void SetCurrentStudent(Student student){

       this.student=student;
    }
    public Student GetCurrentStudent(){
        return this.student;
    }

    //---------------CREATE STUDENT------------//
    public long createStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, student.getName());
        values.put(KEY_Username, student.getUsername());
        values.put(KEY_password, student.getPassword());

        // insert row
        long todo_id = db.insert(TABLE_STUDENT, null, values);

        return todo_id;
    }
    //--------------GET LIST OF STUDENTS--------------//
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student st = new Student();
                st.setID(c.getInt((c.getColumnIndex(KEY_ID))));
                st.setName((c.getString(c.getColumnIndex(KEY_Name))));
                st.setUsermame(c.getString(c.getColumnIndex(KEY_Username)));
                st.setPassword(c.getString(c.getColumnIndex(KEY_password)));
                // adding to todo list
                students.add(st);
            } while (c.moveToNext());
        }

        return students;
    }
    //--------KEEP TRACK OF STUDENTS AND EVENTS-----------//
    public long createStudentEvent(long event_id, long student_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_ID, event_id);
        values.put(KEY_STUDENT_ID, student_id);


        long id = db.insert(TABLE_STUDENT_EVENT, null, values);

        return id;
    }
    //----------CREATE EVENTS LINKED TO STUDENTS-------------//
    public long createEvent(Event event,long[] student_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_Ename, event.getName());
        values.put(KEY_year, event.getyear());
        values.put(KEY_month, event.getmonth());
        values.put(KEY_day, event.getday());
        values.put(KEY_minute, event.getminute());
        values.put(KEY_hour, event.gethour());
        values.put(KEY_location, event.getlocation());
        values.put(KEY_description, event.getDescription());
        values.put(KEY_Ehour, event.getEHour());
        values.put(KEY_Eminute, event.getEMinute());

        // insert row
        long event_id = db.insert(TABLE_EVENT, null, values);


        // assigning tags to todo
       for (long s_id : student_ids) {
            createStudentEvent(event_id, s_id);
        }


        return event_id;
    }

                /*get all events under a student*/
    public List<Event> getAllEventsByStudent(String Student_password) {
        List<Event> events = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT + " EV, "
                + TABLE_STUDENT+ " ST, " + TABLE_STUDENT_EVENT+ " SE WHERE ST."
                + KEY_password+ " = '" + Student_password + "'" + " AND ST." + KEY_ID
                + " = " + "SE." + KEY_STUDENT_ID + " AND EV." + KEY_ID + " = "
                + "SE." + KEY_EVENT_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event t = new Event();

                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_Ename)));
                t.setYear(c.getInt(c.getColumnIndex(KEY_year)));
                t.setlocation(c.getString(c.getColumnIndex(KEY_location)));
                t.setdescription(c.getString(c.getColumnIndex(KEY_description)));
                t.setmonth(c.getInt(c.getColumnIndex(KEY_month)));
                t.setday(c.getInt(c.getColumnIndex(KEY_day)));
                t.sethour(c.getInt(c.getColumnIndex(KEY_hour)));
                t.setMinute(c.getInt(c.getColumnIndex(KEY_minute)));
                t.setEhour(c.getInt(c.getColumnIndex(KEY_Ehour)));
                t.setEminute(c.getInt(c.getColumnIndex(KEY_Eminute)));

                // adding to todo list
                events.add(t);
            } while (c.moveToNext());
        }

        return events;
    }


}