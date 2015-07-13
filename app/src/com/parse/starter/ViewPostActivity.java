package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewPostActivity extends Activity {
    ImageButton backButton;
    TextView title;
    ImageView image;
    ImageButton likeButton;
    TextView votesView;
    TextView description;
    int votes = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_post);

        backButton = (ImageButton) findViewById(R.id.view_post_back_button);
        title = (TextView) findViewById(R.id.MinePost_view_title);
        image = (ImageView) findViewById(R.id.MinePost_view_photo);
        likeButton = (ImageButton) findViewById(R.id.like_button);
        votesView = (TextView) findViewById(R.id.votes_counter);
        description = (TextView) findViewById(R.id.description_text_view);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votes++;
                likeButton.setEnabled(false);
            }
        });




    }


}
