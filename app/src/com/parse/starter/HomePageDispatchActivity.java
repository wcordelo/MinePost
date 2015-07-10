package com.parse.starter;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by karinnaloo on 7/7/15.
 */
public class HomePageDispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return HomePageActivity.class;
    }
}
