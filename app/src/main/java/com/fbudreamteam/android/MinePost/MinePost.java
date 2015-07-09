package com.fbudreamteam.android.MinePost;

import java.util.Date;
import java.util.UUID;

/**
 * Created by FBU Dream Team / William on 7/7/15.
 */
public class MinePost {

    private UUID mId; // used for now until Parse ID is implemented
    private String mTitle; // Useful for having the title. Also Parse
    private Date mDate; // Parse At Created By will be used instead


    //    private boolean mSolved; // Not necessary
//    private String mSuspect; // Also not necessary
    private String mDescription; // Description of the post
//    private


    public MinePost() {
        this(UUID.randomUUID());
    }

    public MinePost(UUID id) {
        mId = id;
        mDate = new Date();
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    //May or may not be neccessary
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

}
