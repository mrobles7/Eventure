package com.google.eventure;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
 * Created by ReignNelson on 10/2/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String LOGTAG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Eventure";
    // table name
    public static final String TABLE_Student = "Student";
    public static final String TABLE_Event = "Event";
    public static final String TABLE_Student_Event = "Student_Event";
    // Student Table Columns names
    private static final String KEY_ID = "ID" ;

    public static final String KEY_Student_ID = "Student_ID";
    public static final String STUDENT_NAME = "FirstName";
    public static final String STUDENT_Username = "Username";
    public static final String STUDENT_password = "Password";
    public static final String STUDENT_Email = "Email";

    //Event Table Columns names
    private static final String KEY_Event_ID = "Event_ID";
    private static final String EVENT_Date = "Date";
    private static final String EVENT_Description = "Description";
    private static final String EVENT_Location = "Location";
    private static final String EVENT_Name = "Name";
    private static final String EVENT_Start = "Start";
    private static final String EVENT_End= "End";

    //Create Event and Student Tables
    private static final String CREATE_TABLE_Student = "CREATE TABLE "
            + TABLE_Student + "(" + KEY_ID + " INTEGER PRIMARY KEY," + STUDENT_NAME
            + " TEXT," + STUDENT_Username + " TEXT," + STUDENT_password + "TEXT" + STUDENT_Email + "TEXT" + ")";

    private static final String CREATE_TABLE_Event = "CREATE TABLE " + TABLE_Event
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + EVENT_Description + " TEXT,"
            + EVENT_Location + " TEXT" + EVENT_Start+"TEXT"+EVENT_End +"Text" +EVENT_Name + "TEXT" + EVENT_Date + " TEXT" + ")";

    private static final String CREATE_TABLE_Student_Event = "CREATE TABLE "
            + TABLE_Student_Event + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_Student_ID + " INTEGER," + KEY_Event_ID + " INTEGER," + ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_Student);
        db.execSQL(CREATE_TABLE_Event);
       // db.execSQL(CREATE_TABLE_Student_Event);

        Log.i(LOGTAG, "Tables were created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Event);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student_Event);

        // create new tables
        onCreate(db);
    }
   // create a student event
    public long createStudentEvent(long Student_id, long Event_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Event_ID, Event_id);
        values.put(KEY_Student_ID, Student_id);



        long id = db.insert(TABLE_Student_Event, null, values);

        return id;
    }
    //insrt just a student
    public void insertEntry(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getName());
        values.put(STUDENT_Username, student.getUsername());
        values.put(STUDENT_password, student.getPassword());
        values.put(STUDENT_Email, student.getEmail());


        // Insert the row into your table
        db.insert(TABLE_Student, null, values);
       

        //Toast.makeText(getApplicationContext(), "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();

    }
    //get sing entry
    public String getSinlgeEntry(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_Student, null, " Username=?",
                new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }
    //Create Student with event
    public long createStudent(Student student, long[] Event_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getName());
        values.put(STUDENT_Username, student.getUsername());
        values.put(STUDENT_password, student.getPassword());
        values.put(STUDENT_Email, student.getEmail());

        // insert row
        long student_id = db.insert(TABLE_Student, null, values);

        // assigning students to events
        for (long Event_id : Event_ids) {
            createStudentEvent(student_id, Event_id);
        }

        return student_id;
    }
    //getting single student
    public Student getStudent(long Student_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_Student + " WHERE "
                + KEY_ID + " = " + Student_id;

        Log.e(LOGTAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Student st = new Student();

        st.setID(c.getInt(c.getColumnIndex(KEY_ID)));
        st.setPassword((c.getString(c.getColumnIndex(STUDENT_password))));
        st.setEmail(c.getString(c.getColumnIndex(STUDENT_Email)));
        st.setUsermame(c.getString(c.getColumnIndex(STUDENT_Username)));
        st.setName(c.getString(c.getColumnIndex(STUDENT_NAME)));
        return st;
    }
    // getting all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_Student;

        Log.e(LOGTAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student st = new Student();

                st.setID(c.getInt(c.getColumnIndex(KEY_ID)));
                st.setPassword((c.getString(c.getColumnIndex(STUDENT_password))));
                st.setEmail(c.getString(c.getColumnIndex(STUDENT_Email)));
                st.setUsermame(c.getString(c.getColumnIndex(STUDENT_Username)));
                st.setName(c.getString(c.getColumnIndex(STUDENT_NAME)));


                // adding to
               students.add(st);
            } while (c.moveToNext());
        }

        return students;
    }



    /*
 * Creating Event
 */
    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_Description, event.getDescription());
        values.put(EVENT_Location, event.getlocation());
        values.put(EVENT_Date, String.valueOf(event.getDate()));
        values.put(EVENT_Start,String.valueOf(event.getStart()));
        values.put(EVENT_End,String.valueOf(event.getEnd()));


        // insert row
        long event_id = db.insert(TABLE_Event, null, values);

        return event_id;
    }
    //getting all events
    public List<Event> getAllEvents() {
        List<Event> event = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_Event;

        Log.e(LOGTAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Event E = new Event();
                E.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                E.setdescription(c.getString(c.getColumnIndex(EVENT_Description)));
                E.setName(c.getString(c.getColumnIndex(EVENT_Name)));
                E.setlocation(c.getString(c.getColumnIndex(EVENT_Location)));
                E.setDate(c.getString(c.getColumnIndex(EVENT_Date)));
                // adding to Events list
                event.add(E);
            } while (c.moveToNext());
        }
        return event;
    }

    /*
 * Updating an Event
*/
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_Name, event.getName());
        values.put(EVENT_Location, event.getlocation());
        values.put(EVENT_Description, event.getDescription());
        values.put(EVENT_Date, event.getDate());
        values.put(EVENT_Start,event.getStart());
        values.put(EVENT_End,event.getEnd());


        // updating row
        return db.update(TABLE_Event, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }

    //updating a student
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_Email, student.getEmail());
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_password, student.getPassword());
        values.put(STUDENT_Username, student.getUsername());

        // updating row
        return db.update(TABLE_Student, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getID()) });
    }

    //getting all students under single event
    public List<Student> getAllStudentsByEvents(String Event_name) {
        List<Student> students = new ArrayList<Student>();

        String selectQuery = "SELECT  * FROM " + TABLE_Student + " ts, "
                + TABLE_Event + " te, " + TABLE_Student_Event + " tse WHERE tg."
                + EVENT_Name + " = '" + Event_name + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_Event_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_Student_ID;

        Log.e(LOGTAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student st = new Student();
                st.setID(c.getInt((c.getColumnIndex(KEY_ID))));
                st.setName((c.getString(c.getColumnIndex(STUDENT_NAME))));
                st.setUsermame(c.getString(c.getColumnIndex(STUDENT_Username)));
                st.setPassword(c.getString(c.getColumnIndex(STUDENT_password)));
                st.setEmail(c.getString(c.getColumnIndex(STUDENT_Email)));

                // adding to Student list
                students.add(st);
            } while (c.moveToNext());
        }

        return students;
    }

    //Updating a Student Event
    public int updateStudentEvent(long id, long tag_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Event_ID, tag_id);

        // updating row
        return db.update(TABLE_Student, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}