package com.fbudreamteam.android.MinePost.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.fbudreamteam.android.MinePost.MinePost;

import java.util.Date;
import java.util.UUID;

import static com.fbudreamteam.android.MinePost.database.CrimeDbSchema.MinePostTable;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public MinePost getMinePost() {
        String uuidString = getString(getColumnIndex(MinePostTable.Cols.UUID));
        String title = getString(getColumnIndex(MinePostTable.Cols.TITLE));
        long date = getLong(getColumnIndex(MinePostTable.Cols.DATE));
//        int isSolved = getInt(getColumnIndex(MinePostTable.Cols.SOLVED));
//        String suspect = getString(getColumnIndex(MinePostTable.Cols.SUSPECT));

        MinePost minePost = new MinePost(UUID.fromString(uuidString));
        minePost.setTitle(title);
        minePost.setDate(new Date(date));
//        minePost.setSolved(isSolved != 0);
//        minePost.setSuspect(suspect);

        return minePost;
    }
}
