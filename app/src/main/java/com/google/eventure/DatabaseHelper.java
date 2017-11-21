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


    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
  //  private static final String KEY_TODO_ID = "todo_id";
  //  private static final String KEY_TAG_ID = "tag_id";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Name
            + " TEXT," + KEY_Username + " Text," + KEY_password
            + " Text" + ")";

    // Tag table create statement
  /*  private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

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

    public long createStudent(Student todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, todo.getName());
        values.put(KEY_Username, todo.getUsername());
        values.put(KEY_password, todo.getPassword());

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
}