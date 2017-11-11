package com.google.eventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity
{
    //initialize database
    DBHandler db;

    public static  long Student1_id;
    public static Student Student1;

    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hold a reference to all the activities in the XML file
        //Its a final variable because editName is the only one its gonna be assigned to
        //FindViewById looks at the activity register filed and finds the id
        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editUsername = (EditText)findViewById(R.id.editUsername);
        final EditText editPassword = (EditText)findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Convert to Edittext string then add to database
        String FirstName =  editName.toString();
        String Username =  editUsername.toString();
        String Passwword =  editPassword.toString();
        db = new DBHandler(getApplicationContext());

        //creating an Example student object

        Student Student1 = new Student(FirstName,Username,Passwword,FirstName);




        /*
        when create event/add event are done i will link each student to events but
        for now just creating students with no events in database
         */
    }
}
