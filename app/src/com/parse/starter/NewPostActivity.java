package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by karinnaloo on 7/9/15.
 */
public class NewPostActivity extends Activity {
    String parseObjName = "MinePostTest";
    EditText title;
    EditText description;
    Button postButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        // enter the title
        title = (EditText) findViewById(R.id.MinePost_title);

        // enter the description
        description = (EditText) findViewById(R.id.post_description);

        // post button
        postButton = (Button) findViewById(R.id.create_post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postTitle = title.getText().toString();
                String postDescription = description.getText().toString();
                ParseObject newTextInput = new ParseObject(parseObjName);
                newTextInput.put("title", postTitle); // save the title
                newTextInput.put("description", postDescription); // save the description
                newTextInput.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        // after user clicks "post," want to bring back to home screen
                        Intent intent = new Intent(NewPostActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}