package com.fbudreamteam.android.MinePost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by FBU Dream Team / William on 7/7/15.
 */
public class MinePostActivity extends AppCompatActivity
        implements MinePostFragment.Callbacks{
    private static final String EXTRA_CRIME_ID =
            "com.fbudreamteam.android.minepost.minepost_id";

    private ViewPager mViewPager;
    private List<MinePost> mMinePosts;

    public static Intent newIntent(Context packageContext, UUID minePostId) {
        Intent intent = new Intent(packageContext, MinePostActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, minePostId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minepost_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_minepost_pager_view_pager);

        mMinePosts = MinePostLab.get(this).getMinePosts();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                MinePost minePost = mMinePosts.get(position);
                return MinePostFragment.newInstance(minePost.getId());
            }

            @Override
            public int getCount() {
                return mMinePosts.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                MinePost minePost = mMinePosts.get(position);
                if (minePost.getTitle() != null) {
                    setTitle(minePost.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        for (int i = 0; i < mMinePosts.size(); i++) {
            if (mMinePosts.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onMinePostUpdated(MinePost minePost) {

    }
}
