package com.fbudreamteam.android.MinePost;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by FBU Dream Team / William on 7/7/15.
 */
public class MinePostHomeFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mMinePostRecyclerView;
    private MinePostAdapter mAdapter;
//    private boolean mSubtitleVisible;
    private Callbacks mCallbacks; // Code not needed as we do not have selection of post but it might be useful to have

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onMinePostSelected(MinePost minePost);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //uses fragment_home_page instead of fragment_minepost_list
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        mMinePostRecyclerView = (RecyclerView) view.findViewById(R.id.minepost_recycler_view);
        mMinePostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // INSERT GOOGLE MAP THING INTO ONCREATE ---- WILLL ---- HERE ----- (y)
//        if (savedInstanceState != null) {
//            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
//        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
////        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    // inflate the action create post button
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_minepost_list, menu);

//        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
//        if (mSubtitleVisible) {
//            subtitleItem.setTitle(R.string.hide_subtitle);
//        } else {
//            subtitleItem.setTitle(R.string.show_subtitle);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_minepost:
                MinePost minePost = new MinePost();
                MinePostLab.get(getActivity()).addMinePost(minePost);
                updateUI();
                mCallbacks.onMinePostSelected(minePost);
                return true;
//            case R.id.menu_item_show_subtitle:
//                mSubtitleVisible = !mSubtitleVisible;
//                getActivity().invalidateOptionsMenu();
//                updateSubtitle();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//not needed anymore but might be useful to have
//    private void updateSubtitle() {
//        MinePostLab minePostLab = MinePostLab.get(getActivity());
//        String subtitle = getString(R.string.subtitle_format, minePostLab.getMinePosts().size());
//
//        if (!mSubtitleVisible) {
//            subtitle = null;
//        }
//
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setSubtitle(subtitle);
//    }

    public void updateUI() {
        MinePostLab minePostLab = MinePostLab.get(getActivity());
        List<MinePost> minePosts = minePostLab.getMinePosts();

        if (mAdapter == null) {
            mAdapter = new MinePostAdapter(minePosts);
            mMinePostRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMinePosts(minePosts);
            mAdapter.notifyDataSetChanged();
        }

//        updateSubtitle();
    }

    private class MinePostHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {

        private TextView mTitleTextView;
        private TextView mDateTextView;
//        private CheckBox mSolvedCheckBox;

        private MinePost mMinePost;

        public MinePostHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_minepost_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_minepost_date_text_view);
//            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindMinePost(MinePost minePost) {
            mMinePost = minePost;
            mTitleTextView.setText(mMinePost.getTitle());
            mDateTextView.setText(mMinePost.getDate().toString());
//            mSolvedCheckBox.setChecked(mMinePost.isSolved());
        }

        @Override
        public void onClick(View v) {
            mCallbacks.onMinePostSelected(mMinePost);
        }
    }

    private class MinePostAdapter extends RecyclerView.Adapter<MinePostHolder> {

        private List<MinePost> mMinePosts;

        public MinePostAdapter(List<MinePost> minePosts) {
            mMinePosts = minePosts;
        }

        @Override
        public MinePostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_minepost, parent, false);
            return new MinePostHolder(view);
        }

        @Override
        public void onBindViewHolder(MinePostHolder holder, int position) {
            MinePost minePost = mMinePosts.get(position);
            holder.bindMinePost(minePost);
        }

        @Override
        public int getItemCount() {
            return mMinePosts.size();
        }

        public void setMinePosts(List<MinePost> minePosts) {
            mMinePosts = minePosts;
        }
    }
}

