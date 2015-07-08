package com.fbudreamteam.android.MinePost;

import android.support.v4.app.Fragment;

/**
 * Created by wcordelo on 7/7/15.
 */
public class MinePostListActivity extends SingleFragmentActivity
        implements MinePostFragment.Callbacks //, MinePostListFragment.Callbacks,
{


    @Override
    protected Fragment createFragment() {
        return new MinePostListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

//    @Override
//    public void onMinePostSelected(MinePost minePost) {
//        if (findViewById(R.id.detail_fragment_container) == null) {
//            Intent intent = MinePostActivity.newIntent(this, minePost.getId());
//            startActivity(intent);
//        } else {
//            Fragment newDetail = MinePostFragment.newInstance(minePost.getId());
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.detail_fragment_container, newDetail)
//                    .commit();
//        }
//    }

    @Override
    public void onMinePostUpdated(MinePost minePost) {
        MinePostListFragment listFragment = (MinePostListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}

