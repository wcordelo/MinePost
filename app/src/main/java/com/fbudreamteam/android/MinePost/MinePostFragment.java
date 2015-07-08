package com.fbudreamteam.android.MinePost;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

/**
 * Created by FBU Dream Team / William on 7/7/15.
 */
public class MinePostFragment extends Fragment {

    private static final String ARG_MinePost_ID = "MinePost_id";
    private static final String DIALOG_DATE = "DialogDate";

        private static final int REQUEST_DATE = 0;
//    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO = 2;

    //    private MinePost mMinePost;
    //Similar to Criminal Intent
    //    Create New Post
    //    See Criminal Intent (Data saved in Parse Cloud)
    //    Parse Object Save / Parse ImageView
    private MinePost mMinePost;


    private File mPhotoFile; // Will use Parse ImageView later on... once Database is implemented
    private EditText mTitleField; // EditText for the Title Field in the Create New Post
//        private Button mDateButton; // Might not use date if Created At is implemented
//    private CheckBox mSolvedCheckbox; // Not needed at all. Will implement up /down voting
//    private Button mReportButton; // Instead of a Report Button we need a Post Button in the location
//    private Button mSuspectButton; // This uses the contacts list which is something we don't need...
    private ImageButton mPhotoButton; // Yes, very useful.
    private ImageView mPhotoView; // Yes, also useful.
    private Callbacks mCallbacks; // Required to keep the fragments independent

    /**
     * Required interface for hosting activities.
     */
    public interface Callbacks {
        void onMinePostUpdated(MinePost minePost);
    }

    public static MinePostFragment newInstance(UUID MinePostId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MinePost_ID, MinePostId);

        MinePostFragment fragment = new MinePostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID MinePostId = (UUID) getArguments().getSerializable(ARG_MinePost_ID);
        mMinePost = MinePostLab.get(getActivity()).getMinePost(MinePostId);
        mPhotoFile = MinePostLab.get(getActivity()).getPhotoFile(mMinePost);
    }

    @Override
    public void onPause() {
        super.onPause();

        MinePostLab.get(getActivity())
                .updateMinePost(mMinePost);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    //onCreatView links the xml file () to MinePostLab
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_post, container, false);

        mTitleField = (EditText) v.findViewById(R.id.MinePost_title);
        mTitleField.setText(mMinePost.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getActivity() == null) {
                    return;
                }
                mMinePost.setTitle(s.toString());
                updateMinePost();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Date Button not needed nor the SolvedCheckBox
//        mDateButton = (Button) v.findViewById(R.id.MinePost_date);
//        updateDate();
//        mDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//                DatePickerFragment dialog = DatePickerFragment
//                        .newInstance(mMinePost.getDate());
//                dialog.setTargetFragment(MinePostLab.this, REQUEST_DATE);
//                dialog.show(manager, DIALOG_DATE);
//            }
//        });

//        mSolvedCheckbox = (CheckBox) v.findViewById(R.id.MinePost_solved);
//        mSolvedCheckbox.setChecked(mMinePost.isSolved());
//        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mMinePost.setSolved(isChecked);
//                updateMinePost();
//            }
//        });

        //Instead of the Report Button we will make this post in location using GeoParse
//        mReportButton = (Button) v.findViewById(R.id.MinePost_report);
//        mReportButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT, getMinePostReport());
//                i.putExtra(Intent.EXTRA_SUBJECT,
//                        getString(R.string.MinePost_report_subject));
//                i = Intent.createChooser(i, getString(R.string.send_report));
//
//                startActivity(i);
//            }
//        });
        //Suspect Button not needed
//        final Intent pickContact = new Intent(Intent.ACTION_PICK,
//                ContactsContract.Contacts.CONTENT_URI);
//        mSuspectButton = (Button) v.findViewById(R.id.MinePost_suspect);
//        mSuspectButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivityForResult(pickContact, REQUEST_CONTACT);
//            }
//        });
//
//        if (mMinePost.getSuspect() != null) {
//            mSuspectButton.setText(mMinePost.getSuspect());
//        }
//
//        if (packageManager.resolveActivity(pickContact,
//                PackageManager.MATCH_DEFAULT_ONLY) == null) {
//            mSuspectButton.setEnabled(false);
//        }
        //Needed to take photo
        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.minepost_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.post_minepost_photo);
        updatePhotoView();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        //Code not needed for MinePost
//        if (requestCode == REQUEST_DATE) {
//            Date date = (Date) data
//                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
//            mMinePost.setDate(date);
//        updateMinePost();
//            updateDate(); } else if (requestCode == REQUEST_CONTACT && data != null) {
//        if (requestCode == REQUEST_CONTACT && data != null) {
//            Uri contactUri = data.getData();
//            // Specify which fields you want your query to return
//            // values for.
//            String[] queryFields = new String[]{
//                    ContactsContract.Contacts.DISPLAY_NAME,
//            };
//            // Perform your query - the contactUri is like a "where"
//            // clause here
//            ContentResolver resolver = getActivity().getContentResolver();
//            Cursor c = resolver
//                    .query(contactUri, queryFields, null, null, null);

//            try {
//                // Double-check that you actually got results
//                if (c.getCount() == 0) {
//                    return;
//                }
//
//                // Pull out the first column of the first row of data -
//                // that is your suspect's name.
//                c.moveToFirst();
//
//                String suspect = c.getString(0);
//                mMinePost.setSuspect(suspect);
//                updateMinePost();
//                mSuspectButton.setText(suspect);
//            } finally {
//                c.close();
//            } } else if (requestCode == REQUEST_PHOTO) {
        if (requestCode == REQUEST_PHOTO) {
            updateMinePost();
            updatePhotoView();
        }
    }

    private void updateMinePost() {
        MinePostLab.get(getActivity()).updateMinePost(mMinePost);
        mCallbacks.onMinePostUpdated(mMinePost);
    }

//Not needed since we will be using Parse's Created at
//    private void updateDate() {
//        mDateButton.setText(mMinePost.getDate().toString());
//    }
        //useful but not necessary
//    private String getMinePostReport() {
//        String solvedString = null;
//        if (mMinePost.isSolved()) {
//            solvedString = getString(R.string.MinePost_report_solved);
//        } else {
//            solvedString = getString(R.string.MinePost_report_unsolved);
//        }
//        String dateFormat = "EEE, MMM dd";
//        String dateString = DateFormat.format(dateFormat, mMinePost.getDate()).toString();
//        String suspect = mMinePost.getSuspect();
//        if (suspect == null) {
//            suspect = getString(R.string.MinePost_report_no_suspect);
//        } else {
//            suspect = getString(R.string.MinePost_report_suspect, suspect);
//        }
//        String report = getString(R.string.MinePost_report,
//                mMinePost.getTitle(), dateString, solvedString, suspect);
//        return report;
//    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

}
