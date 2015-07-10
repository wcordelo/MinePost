package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
<<<<<<< HEAD
>>>>>>> Stashed changes
import android.graphics.Bitmap;
>>>>>>> Stashed changes
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
=======
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
>>>>>>> 83d640edebefdb36d255a4b29addf0751a8cdec4
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Toast;
<<<<<<< Updated upstream

=======
import android.widget.ImageButton;
import android.widget.ImageView;
=======
import android.widget.ImageButton;
import android.widget.ImageView;

>>>>>>> 83d640edebefdb36d255a4b29addf0751a8cdec4
>>>>>>> Stashed changes
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
<<<<<<< Updated upstream
import com.parse.starter.database.MinePostAddedCallback;
import com.parse.starter.database.MinePostManager;
=======

import java.nio.ByteBuffer;
>>>>>>> Stashed changes

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by karinnaloo on 7/9/15.
 */
public class NewPostActivity extends Activity implements MinePostAddedCallback {

//    String parseObjName = "MinePostTest";
    private EditText mTitle;
    private EditText mDescription;
    private Button mPostButton;
    private MinePostManager mMinePostManager;
    private ProgressDialog mProgressDialog;

<<<<<<< Updated upstream
=======
    ImageButton photoButton;
    ImageView photoView;

    final ParseObject parseData = new ParseObject(parseObjName);

    private static final int REQUEST_PHOTO = 2;

    ImageButton photoButton;
    ImageView photoView;

    final ParseObject parseData = new ParseObject(parseObjName);

    private static final int REQUEST_PHOTO = 2;

>>>>>>> Stashed changes
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
<<<<<<< HEAD

        // enter the mTitle
        mTitle = (EditText) findViewById(R.id.MinePost_title);

        // enter the mDescription
        mDescription = (EditText) findViewById(R.id.post_description);
<<<<<<< Updated upstream
=======
=======
>>>>>>> 83d640edebefdb36d255a4b29addf0751a8cdec4
        // enter the title
        title = (EditText) findViewById(R.id.MinePost_title);
>>>>>>> Stashed changes

        //MinePostManager
        mMinePostManager = new MinePostManager();

        // post button
        mPostButton = (Button) findViewById(R.id.create_post_button);
        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                mMinePostManager.addMinePost(mTitle.getText().toString(),
                        mDescription.getText().toString(),
                        NewPostActivity.this);
                mProgressDialog = ProgressDialog.show(NewPostActivity.this, "", getString(R.string.mineposts_saving_text), true);
            }
            @Override
            public void onClick(View v) {
                String postTitle = mTitle.getText().toString();
                String postDescription = mDescription.getText().toString();
                ParseObject newTextInput = new ParseObject(parseObjName);
                newTextInput.put("mTitle", postTitle); // save the mTitle
                newTextInput.put("mDescription", postDescription); // save the mDescription
                newTextInput.saveInBackground(new SaveCallback() {
<<<<<<< Updated upstream
=======

=======
>>>>>>> 83d640edebefdb36d255a4b29addf0751a8cdec4
                String postTitle = title.getText().toString();
                String postDescription = description.getText().toString();
                parseData.put("title", postTitle); // save the title
                parseData.put("description", postDescription); // save the description
                parseData.saveInBackground(new SaveCallback() {
>>>>>>> Stashed changes
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
