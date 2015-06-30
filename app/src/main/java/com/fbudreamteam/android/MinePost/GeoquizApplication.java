package com.fbudreamteam.android.MinePost;

import android.app.Application;

import com.fbudreamteam.android.MinePost.model.QuizQuestionManager;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;


public class GeoquizApplication extends Application {

    //app id and key from Parse
    public static final String YOUR_APPLICATION_ID = "ho2mVEXTRQVSKhLtScuobm82ijdpJriHTmdBOjGP";
    public static final String YOUR_CLIENT_KEY = "E9bd2BD9QkMn0p3Vwn4hKsaIASOLBj38G0doN8WF";

    @Override
    public void onCreate() {
        super.onCreate();
        ParseCrashReporting.enable(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        ParseUser.enableAutomaticUser();
        QuizQuestionManager.registerParseObjects();
    }
}
