package fbudreamteam.android.minepost;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    //app id and key from Parse
    public static final String YOUR_APPLICATION_ID = "aBsY5STXm1XVwrQBMLyE5qh8iSSQuOhagHffyE1b";
    public static final String YOUR_CLIENT_KEY = "uNVlKv0ZuC7fyEjMuKg7eUtQvEAEY2b9uQZ9yGbt";

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

        // Add your initialization code here
        Parse.initialize(this);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        ParseACL.setDefaultACL(defaultACL, true);
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
