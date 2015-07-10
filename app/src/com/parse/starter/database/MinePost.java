package com.parse.starter.database;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by FBU Dream Team / William on 7/10/15.
 */
@ParseClassName("MinePost")
public class MinePost extends ParseObject{
    //Where the data will be stored in the Parse Database
    public static final String MODEL_NAME = "MinePost";
    //Title and Description of MinePost
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    //also need one for pictures

    public MinePost() {}

    //getters and setters for title and description
    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    public String getDescription() {
        return getString(DESCRIPTION);
    }

    public void setDescription(String description) {
        put(DESCRIPTION, description);
    }

    //also need getters and setters for pictures later on

    //Overrides String so that it can be compatible with Parse Database
    @Override
    public String toString() {
        return "MinePost{" + getTitle() + "," + getDescription() + ",}";
    }

}
