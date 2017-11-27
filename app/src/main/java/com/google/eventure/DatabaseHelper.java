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

    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StudenAndEvent";

    // Table Names
    private static final String TABLE_TODO = "todos"; //Student
    private static final String TABLE_TAG = "tags"; //Event
    private static final String TABLE_TODO_TAG = "todo_tags";//Student_Event

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

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
    private static final String  KEY_Ehour= "Ehour";
    private static final String  KEY_Eminute= "Eminute";
    private static final String  KEY_EventDate = "EventDate";
    private static final String  KEY_FormattedDate = "FormattedDate";
    private static final String  KEY_location = "location";
    private static final String  KEY_description = "description";
    private static final String  KEY_name = "name";
    private static final String  KEY_notification= "notification";
    private static final String  KEY_start = "start";
    private static final String  KEY_end= "end";

    /*

      int id, year, month, day, hour, minute, Ehour, Eminute;
    String EventDate;
    Date FormattedDate;
    String location;
    String description;
    String name;
    Date NotifDate;
    String notifTime;
    String Start;
    String End;

     */


    // NOTE_TAGS Table - column names
  //  private static final String KEY_TODO_ID = "todo_id";
  //  private static final String KEY_TAG_ID = "tag_id";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Name
            + " TEXT," + KEY_Username + " Text," + KEY_password
            + " Text" + ")";

    // Event table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_year+ " INTEGER,"
            + KEY_month+ " INTEGER," + KEY_day+ " INTEGER," + KEY_hour+ " INTEGER,"
            +KEY_minute+ " INTEGER," +KEY_Ehour+ " INTEGER," + KEY_Eminute+ " INTEGER,"
            + KEY_EventDate+ " Text,"+ KEY_FormattedDate + " DATE,"+ KEY_location + " Text,"
            + KEY_name+ " Text,"+ KEY_notification + " DATE,"+ KEY_start + " Text,"
            + KEY_end+ " Text,"+ KEY_description + " Text,"
            +")";
/*
    // todo_tag table create statement
    private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";
*/
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TODO);
        //db.execSQL(CREATE_TABLE_TAG);
       // db.execSQL(CREATE_TABLE_TODO_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_TAG);

        // create new tables
        onCreate(db);

    }

    public long createStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, student.getName());
        values.put(KEY_Username, student.getUsername());
        values.put(KEY_password, student.getPassword());

        // insert row
        long todo_id = db.insert(TABLE_TODO, null, values);

        // assigning tags to todo
       /* for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }
*/
        return todo_id;
    }

    public List<Student> getAllStudents() {
        List<Student> todos = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Student td = new Student();
                td.setID(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_Name))));
                td.setUsermame(c.getString(c.getColumnIndex(KEY_Username)));
                td.setPassword(c.getString(c.getColumnIndex(KEY_password)));
                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public long createEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        // insert row
        long tag_id = db.insert(TABLE_TAG, null, values);

        return tag_id;
    }

    /**
     * getting all tags
     *
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();
        String selectQuery = "SELECT  * FROM " + TABLE_TAG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Tag t = new Tag();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setTagName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));

                // adding to tags list
                tags.add(t);
            } while (c.moveToNext());
        }
        return tags;
    }*/
}