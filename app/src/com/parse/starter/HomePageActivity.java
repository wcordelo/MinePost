package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class HomePageActivity extends Activity {
    private Button logOut;
    private Button create;
    //Required for RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_page);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.minepost_recycler_view);
        mRecyclerView.setHasFixedSize(true); //keeps the RecyclerView Fixed in Size
        mLayoutManager = new LinearLayoutManager(this); //RecyclerView needs a Layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager); //Layout Manager
//        mAdapter = new MyAdapter(myDataset); //RecyclerView Adapter
//        mRecyclerView.setAdapter(mAdapter); //RecyclerView Adapter
<<<<<<< HEAD
=======

        logOut = (Button) findViewById(R.id.logout);
>>>>>>> afaf281935f4ef29dec3b63e9fd6ce791c8b70d6

        logOut = (Button) findViewById(R.id.logout);
        //log out button
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                // intent...flag clear_top|new stack...start activity... finish
                Intent myIntent = new Intent(HomePageActivity.this, HomePageDispatchActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);
                finish();
            }
        });

        create = (Button) findViewById(R.id.create_button); // create button
        // Initialize create button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("INSIDE CLICK THING");
                Intent intent = new Intent(HomePageActivity.this, NewPostActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

//    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//        private String[] mDataset;
//
//        // Provide a reference to the views for each data item
//        // Complex data items may need more than one view per item, and
//        // you provide access to all the views for a data item in a view holder
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            // each data item is just a string in this case
//            public TextView mTextView;
//            public ViewHolder(TextView v) {
//                super(v);
//                mTextView = v;
//            }
//        }

        // Provide a suitable constructor (depends on the kind of dataset)
//        public MyAdapter(String[] myDataset) {
//            mDataset = myDataset;
//        }
//
//        // Create new views (invoked by the layout manager)
//        @Override
//        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
//                                                       int viewType) {
//            // create a new view
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.recycler_view_item, parent, false);
//            // set the view's size, margins, paddings and layout parameters
//
//            ViewHolder vh = new ViewHolder((TextView) v);
//            return vh;
//        }
//
//        // Replace the contents of a view (invoked by the layout manager)
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            // - get element from your dataset at this position
//            // - replace the contents of the view with that element
//            holder.mTextView.setText(mDataset[position]);
//
//        }
//
//        // Return the size of your dataset (invoked by the layout manager)
//        @Override
//        public int getItemCount() {
//            return mDataset.length;
//        }
//    }
}