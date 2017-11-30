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


public class RegisterActivity extends AppCompatActivity {

    // SQLiteOpenHelper openHelper;
    //initialize database
    // DBHandler db;


    Button btnlogin;
    DatabaseHelper db;

    public static Student Student1;
    public static long Student1_id;

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
                // String confirmPassword=editTextConfirmPassword.getText().toString();

                // check if any of the fields are vaccant
             /*  if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }*/
                // check if both password matches
               /* if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }*/
                // else
                //{
                // Save the Data in Database

                Student student = new Student();
                student.setName(Name);
                student.setUsermame(userName);
                student.setPassword(password);

               student.setID(db.createStudent(student));


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