package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class ParseStarterProjectActivity extends Activity {
    Button logOut;
    Button create;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_page);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        logOut = (Button) findViewById(R.id.logout);

        //log out button
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                // intent...flag clear_top|new stack...start activity... finish
                Intent myIntent = new Intent(ParseStarterProjectActivity.this, ParseStarterProjectDispatchActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);
                finish();
            }
        });


        create = (Button) findViewById(R.id.create_button); // create button

        // Initialize create button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("INSIDE CLICK THING");
                Intent intent = new Intent(ParseStarterProjectActivity.this, NewPostActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}