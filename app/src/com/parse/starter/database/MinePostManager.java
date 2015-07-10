package com.parse.starter.database;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by wcordelo on 7/10/15.
 */
public class MinePostManager {
    //To register into MinePost class "MinePost"
    public static void registerParseObjects() {
        ParseObject.registerSubclass(MinePost.class);
    }

    public void addMinePost(String title, String description, final MinePostAddedCallback minePostAddedCallback) {
        //Makes new MinePost Object with title, description
        //also need one for pictures
        MinePost minePost = new MinePost();
        minePost.setTitle(title);
        minePost.setDescription(description);

        //also need one for pictures

        minePost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                minePostAddedCallback.onMinePostAdded();
            }
        });
    }

    public void loadQuestions(final MinePostLoadedCallback callback) {
        ParseQuery<MinePost> query = new ParseQuery<>(MinePost.MODEL_NAME);
        query.findInBackground(new FindCallback<MinePost>() {
            @Override
            public void done(List<MinePost> list, ParseException e) {
                callback.onMinePostsLoaded(list);
            }
        });
    }


}
