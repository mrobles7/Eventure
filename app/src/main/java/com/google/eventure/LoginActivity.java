package com.google.eventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{
    //Initialize database
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hold a reference to all the activities in the XML file
        //Its a final variable because editName is the only one its gonna be assigned to
        //FindViewById looks at the activity register filed and finds the id
        final EditText editUsername = (EditText)findViewById(R.id.editUsername);
        final EditText editPassword = (EditText)findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final TextView registerLink = (TextView) findViewById(R.id.registerLink);

        //When the user clicks the register link it'll jump to that page
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
