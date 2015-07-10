package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
=======
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.ImageView;
=======
import android.widget.ImageButton;
import android.widget.ImageView;

>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.starter.database.MinePostAddedCallback;
import com.parse.starter.database.MinePostManager;
import java.nio.ByteBuffer;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by karinnaloo on 7/9/15.
 */
public class NewPostActivity extends Activity implements MinePostAddedCallback {

    String parseObjName = "MinePostTest";
    private EditText mTitle;
    private EditText mDescription;
    private Button mPostButton;
    private MinePostManager mMinePostManager;
    private ProgressDialog mProgressDialog;

    ImageButton photoButton;
    ImageView photoView;

    final ParseObject parseData = new ParseObject(parseObjName);

    private static final int REQUEST_PHOTO = 2;

    ImageButton photoButton;
    ImageView photoView;

    final ParseObject parseData = new ParseObject(parseObjName);

    private static final int REQUEST_PHOTO = 2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
<<<<<<< HEAD

        // enter the mTitle
        mTitle = (EditText) findViewById(R.id.MinePost_title);

        // enter the mDescription
        mDescription = (EditText) findViewById(R.id.post_description);
=======
>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
        // enter the title
        title = (EditText) findViewById(R.id.MinePost_title);

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

=======
>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
                String postTitle = title.getText().toString();
                String postDescription = description.getText().toString();
                parseData.put("title", postTitle); // save the title
                parseData.put("description", postDescription); // save the description
<<<<<<< HEAD
=======
                parseData.put("votes", 0);
>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
                parseData.saveInBackground(new SaveCallback() {
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

        photoButton = (ImageButton) findViewById(R.id.minepost_camera);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        photoView = (ImageView) findViewById(R.id.post_minepost_photo);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        ParseFile pf = new ParseFile(convertBP(bp)); // convert byte[] to parse file
        parseData.put("photo", pf); //put file into object
        photoView.setImageBitmap(bp);
    }

    // helper method for converting bitmap into byte array
    public byte[] convertBP(Bitmap bp) {
        int bytes = bp.getByteCount();//calculate how many bytes our image consists of.
        ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
        bp.copyPixelsToBuffer(buffer); //Move the byte data to the buffer
        return buffer.array(); //Get the underlying array containing the data.
    }
<<<<<<< HEAD

    ///////////////////////////////////////////////////////////////////////////////////////////////
    private CheckBox mQuestionTrueCheckbox;
    private EditText mQuestionText;
    private Button mSaveQuestionButton;
    private QuizQuestionManager mQuizQuestionManager;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_question);
//        mQuestionTrueCheckbox = (CheckBox) findViewById(R.id.question_true);
//        mQuestionText = (EditText) findViewById(R.id.question_text);
        mSaveQuestionButton = (Button) findViewById(R.id.save_question);
//        mQuizQuestionManager = new QuizQuestionManager();
        mSaveQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuizQuestionManager.addQuestion(mQuestionText.getText().toString(),
                        mQuestionTrueCheckbox.isChecked(),
                        AddQuestionActivity.this);
                mProgressDialog = ProgressDialog.show(AddQuestionActivity.this, "", getString(R.string.questions_saving_text), true);
            }
        });
    }

    @Override
    public void onQuizAdded() {
        Toast.makeText(this, R.string.quiz_added, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

=======
>>>>>>> e82a3ed367edfb68c3abe42c7f30ff26b4032a9e
}
