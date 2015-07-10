package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
<<<<<<< Updated upstream
=======
import android.graphics.Bitmap;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        // enter the mTitle
        mTitle = (EditText) findViewById(R.id.MinePost_title);

        // enter the mDescription
        mDescription = (EditText) findViewById(R.id.post_description);

        //MinePostManager
        mMinePostManager = new MinePostManager();

        // post button
        mPostButton = (Button) findViewById(R.id.create_post_button);
        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

}
