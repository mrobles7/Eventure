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


    Button btnlogin;
    DBHandler db;
    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHandler(getApplicationContext());
        //Hold a reference to all the activities in the XML file
        //Its a final variable because editName is the only one its gonna be assigned to
        //FindViewById looks at the activity register filed and finds the id
        final EditText editUsername = (EditText)findViewById(R.id.editUsername);
        final EditText editPassword = (EditText)findViewById(R.id.editPassword);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final TextView registerLink = (TextView) findViewById(R.id.registerLink);
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

        btnlogin=(Button)findViewById(R.id.buttonLogin);
            // Set On ClickListener
            btnlogin.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // get The User name and Password
                    String userName=editUsername.getText().toString();
                    String password=editPassword.getText().toString();

                    // fetch the Password form database for respective user name
                //    String storedPassword=db.getSinlgeEntry(userName);

                    // check if the Stored password matches with  Password entered by user
               /*     if(password.equals(storedPassword))
                    {
                        Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
*/
                    Intent login = new Intent(LoginActivity.this, ScheduleActivity.class);
                    LoginActivity.this.startActivity(login);
                }
            });

        }

        @Override
        protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        db.close();


         }
}
