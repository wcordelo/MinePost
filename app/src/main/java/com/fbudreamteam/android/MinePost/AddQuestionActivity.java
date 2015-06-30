package com.fbudreamteam.android.MinePost;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java
import com.fbudreamteam.android.geoquiz.R;
import com.fbudreamteam.android.MinePost.model.QuizAddedCallback;
import com.fbudreamteam.android.MinePost.model.QuizQuestionManager;

public class AddQuestionActivity extends AppCompatActivity implements QuizAddedCallback {
=======
public class AddQuestionActivity extends AppCompatActivity implements com.fbudreamteam.android.MinePost.model.QuizAddedCallback {
>>>>>>> fe739e69266b214e257c3d7f2ca58e36a0b48899:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java

    private CheckBox mQuestionTrueCheckbox;
    private EditText mQuestionText;
    private Button mSaveQuestionButton;
<<<<<<< HEAD:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java
    private QuizQuestionManager mQuizQuestionManager;
=======
    private com.fbudreamteam.android.MinePost.model.QuizQuestionManager mQuizQuestionManager;
>>>>>>> fe739e69266b214e257c3d7f2ca58e36a0b48899:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        mQuestionTrueCheckbox = (CheckBox) findViewById(R.id.question_true);
        mQuestionText = (EditText) findViewById(R.id.question_text);
        mSaveQuestionButton = (Button) findViewById(R.id.save_question);
<<<<<<< HEAD:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java
        mQuizQuestionManager = new QuizQuestionManager();
=======
        mQuizQuestionManager = new com.fbudreamteam.android.MinePost.model.QuizQuestionManager();
>>>>>>> fe739e69266b214e257c3d7f2ca58e36a0b48899:app/src/main/java/com/fbudreamteam/android/MinePost/AddQuestionActivity.java
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

}
