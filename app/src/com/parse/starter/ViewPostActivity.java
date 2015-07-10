package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewPostActivity extends Activity{
    TextView title;
    ImageView image;
    ImageButton upButton;
    TextView votesView;
    ImageButton downButton;
    TextView description;
    int votes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_post);

        title = (TextView) findViewById(R.id.MinePost_view_title);
        image = (ImageView) findViewById(R.id.MinePost_view_photo);
        upButton = (ImageButton) findViewById(R.id.up_vote_button);
        votesView = (TextView) findViewById(R.id.votes_counter);
        downButton = (ImageButton) findViewById(R.id.down_vote_button);
        description = (TextView) findViewById(R.id.description_text_view);



    }



}
