package com.fbudreamteam.android.MinePost;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by wcordelo on 7/7/15.
 */
public class MinePostHomeActivity extends SingleFragmentActivity
        implements MinePostFragment.Callbacks
        , MinePostHomeFragment.Callbacks
//        , MinePostViewFragment.Callbacks
{


    @Override
    protected Fragment createFragment() {
        return new MinePostHomeFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    // handles when user clicks on post in list
    @Override
    public void onMinePostSelected(MinePost minePost) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = MinePostActivity.newIntent(this, minePost.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = MinePostFragment.newInstance(minePost.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    // when user edits, updates user interface
    @Override
    public void onMinePostUpdated(MinePost minePost) {
        MinePostHomeFragment listFragment = (MinePostHomeFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    public void onMinePostViewSelected(MinePost minePost) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = MinePostActivity.newIntent(this, minePost.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = MinePostViewFragment.newInstance(minePost.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}

