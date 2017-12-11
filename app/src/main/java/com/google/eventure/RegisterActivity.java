package com.google.eventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.List;


public class RegisterActivity extends LoginActivity {

    // SQLiteOpenHelper openHelper;
    //initialize database
    // DBHandler db;

    Button btnlogin;
    DatabaseHelper db;


    @Override


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(getApplicationContext());
        //Hold a reference to all the activities in the XML file
        //Its a final variable because editName is the only one its gonna be assigned to
        //FindViewById looks at the activity register filed and finds the id
        final EditText editName = (EditText) findViewById(R.id.editName);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);


        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String Name = editName.getText().toString();
                String userName = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                List<Student> students = db.getAllStudents();
                for (Student student : students) {


                }



                // Save the Data in Database

                Student student1 = new Student();
                student1.setID(1);
                student1.setName(Name);
                student1.setUsermame(userName);
                student1.setPassword(password);

               student1.setID(db.createStudent(student));


               student = student1;


                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                //}

                Intent registerIntent = new Intent(RegisterActivity.this, ScheduleActivity.class);
                startActivity(registerIntent);

            }
        });
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();

    }


}