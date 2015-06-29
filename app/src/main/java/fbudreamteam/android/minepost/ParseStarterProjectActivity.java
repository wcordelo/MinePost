

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;

import fbudreamteam.android.minepost.R;

public class ParseStarterProjectActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_post);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
