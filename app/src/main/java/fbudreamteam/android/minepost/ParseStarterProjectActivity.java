package fbudreamteam.android.minepost;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;

public class ParseStarterProjectActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
