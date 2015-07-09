package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    EditText input;
    Button sendButton;
    Button refreshButton;
    TextView textDisplay;
    String parseObjName = "testParse";
    Button logOut;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        input = (EditText) findViewById(R.id.EnterText); //input text
        textDisplay = (TextView) findViewById(R.id.displayText); // text display
        sendButton = (Button) findViewById(R.id.send_button); // send button
        refreshButton = (Button) findViewById(R.id.refresh_button); // refresh view button
        logOut = (Button) findViewById(R.id.log_out); //log out button

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // left blank on purpose
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sendButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // this too
            }
        });

        Log.d("grantland", "onCreate()");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("grantland", "onClick()");
                sendButton.setEnabled(false);
                String output = input.getText().toString();
                ParseObject newTextInput = new ParseObject(parseObjName);
                newTextInput.put("text", output);
                newTextInput.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("grantland", "done()");
                        //mSendButton.setEnabled(true);
                        updateView();
                        input.setText("");
                    }
                });
                Log.d("grantland", "onClick() 2");
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent myIntent = new Intent(ParseStarterProjectActivity.this, ParseStarterProjectDispatchActivity.class);
                ParseStarterProjectActivity.this.startActivity(myIntent);
                finish();
            }
        });

        updateView();
    }

    // Helper function for updating the text view
    public void updateView() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(parseObjName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                String text = "";
                if (e == null) {
                    for (ParseObject obj : list) {
                        text = obj.getString("text") + "\n" + text;
                    }
                    textDisplay.setText(text);
                } else {
                    //
                }
            }
        });
    }
}