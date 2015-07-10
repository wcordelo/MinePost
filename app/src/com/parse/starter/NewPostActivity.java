package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by karinnaloo on 7/9/15.
 */
public class NewPostActivity extends Activity {
    String parseObjName = "MinePostTest";
    EditText title;
    EditText description;
    Button postButton;

    ImageButton photoButton;
    ImageView photoView;

    final ParseObject parseData = new ParseObject(parseObjName);

    private static final int REQUEST_PHOTO = 2;

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
                parseData.put("title", postTitle); // save the title
                parseData.put("description", postDescription); // save the description
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
}
