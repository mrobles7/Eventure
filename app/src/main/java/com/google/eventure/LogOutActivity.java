package com.google.eventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogOutActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hold a reference to all the activities in the XML file
        //FindViewById looks at the activity register filed and finds the id
        final TextView loginLink = (TextView) findViewById(R.id.loginLink);

        //When the user clicks the login link it'll jump to that page
        //The OnClickListener waits for there to be clicked then it takes action
        loginLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //This will happen when clicked
            public void onClick(View v)
            {
                //An intent opens the register activity
                //We say we are in LoginActivity and to open the intent
                Intent loginIntent = new Intent(LogOutActivity.this, LoginActivity.class);
                LogOutActivity.this.startActivity(loginIntent);
            }
        });
    }
}
