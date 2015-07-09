package com.fbudreamteam.android.MinePost;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by karinnaloo on 7/9/15.
 */
public class MinePostDispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return MinePostHomeActivity.class;
    }
}
