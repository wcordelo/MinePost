package com.fbudreamteam.android.MinePost.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.fbudreamteam.android.MinePost.database.CrimeDbSchema.MinePostTable;


public class CrimeBaseHelper extends SQLiteOpenHelper {
//    private static final String TAG = "CrimeBaseHelper";
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "minePostBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + MinePostTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MinePostTable.Cols.UUID + ", " +
                MinePostTable.Cols.TITLE + ", " +
                MinePostTable.Cols.DATE + ", " +
                        MinePostTable.Cols.DESCRIPTION +
//                MinePostTable.Cols.SUSPECT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
