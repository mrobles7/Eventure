package com.google.eventure;
import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity
{

    public static  long Student1_id;
    public static Student Student1;
    //Initialize database
   // DBHandler dab;
   // @SuppressLint("Recycle")
    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
      //  SQLiteDatabase db;
       // SQLiteOpenHelper openHelper;
        //Cursor cursor;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hold a reference to all the activities in the XML file
        //Its a final variable because editName is the only one its gonna be assigned to
        //FindViewById looks at the activity register filed and finds the id
        final EditText editUsername = (EditText)findViewById(R.id.editUsername);
        final EditText editPassword = (EditText)findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final TextView registerLink = (TextView) findViewById(R.id.registerLink);
       // openHelper=new DBHandler(this);
        //db = openHelper.getReadableDatabase();

        String Username =  editUsername.toString();
        String Passwword =  editPassword.toString();

       /* cursor = db.rawQuery("SELECT *FROM " + DBHandler.TABLE_Student + " WHERE " +
                DBHandler.STUDENT_Username+ " =? AND " + DBHandler.STUDENT_password + " =? " ,
                new String[]{Username, Passwword});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
            }

            //get studentId
            cursor.getColumnIndexOrThrow(DBHandler.KEY_Student_ID);
            Student1=dab.getStudent(Student1_id);

        }//*/

        //When the user clicks the register link itll jump to that page
        //The OnClickListener waits for there to be clicked then it takes action
        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //An intent opens the register activity
                //We say we are in LoginActivity and to open the intent
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });



         }
}
