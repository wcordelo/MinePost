package com.fbudreamteam.android.MinePost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.fbudreamteam.android.MinePost.database.CrimeBaseHelper;
import com.fbudreamteam.android.MinePost.database.CrimeCursorWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fbudreamteam.android.MinePost.database.CrimeDbSchema.MinePostTable;

/**
 * Created by FBU Dream Team / William on 7/7/15.
 */
public class MinePostLab {

    private static MinePostLab sMinePostLab;
    //Uses Context to send information
    //Will use temporary database for now. Will go to Parse Database once it's done
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MinePostLab get(Context context) {
        if (sMinePostLab == null) {
            sMinePostLab = new MinePostLab(context);
        }
        return sMinePostLab;
    }

    private MinePostLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
    }


    public void addMinePost(MinePost minePost) {
        ContentValues values = getContentValues(minePost);

        mDatabase.insert(MinePostTable.NAME, null, values);
    }

    public List<MinePost> getMinePosts() {
        List<MinePost> minePosts = new ArrayList<>();

        CrimeCursorWrapper cursor = queryMinePosts(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            minePosts.add(cursor.getMinePost());
            cursor.moveToNext();
        }
        cursor.close();

        return minePosts;
    }

    public MinePost getMinePost(UUID id) {
        CrimeCursorWrapper cursor = queryMinePosts(
                MinePostTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getMinePost();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(MinePost minePost) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFilesDir == null) {
            return null;
        }

        return new File(externalFilesDir, minePost.getPhotoFilename());
    }

    public void updateMinePost(MinePost minePost) {
        String uuidString = minePost.getId().toString();
        ContentValues values = getContentValues(minePost);

        mDatabase.update(MinePostTable.NAME, values,
                MinePostTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(MinePost minePost) {
        ContentValues values = new ContentValues();
        values.put(MinePostTable.Cols.UUID, minePost.getId().toString());
        values.put(MinePostTable.Cols.TITLE, minePost.getTitle());
        values.put(MinePostTable.Cols.DATE, minePost.getDate().getTime());
//        values.put(CrimeTable.Cols.SOLVED, minePost.isSolved() ? 1 : 0);
//        values.put(CrimeTable.Cols.SUSPECT, minePost.getSuspect());

        return values;
    }

    private CrimeCursorWrapper queryMinePosts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MinePostTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new CrimeCursorWrapper(cursor);
    }

}

