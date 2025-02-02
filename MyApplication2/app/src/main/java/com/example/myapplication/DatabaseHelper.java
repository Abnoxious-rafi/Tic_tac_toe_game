package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GameScores.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "scores";
    private static final String COL_ID = "id";
    private static final String COL_PLAYER1 = "player1";
    private static final String COL_SCORE1 = "score1";
    private static final String COL_PLAYER2 = "player2";
    private static final String COL_SCORE2 = "score2";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_PLAYER1 + " TEXT, " +
            COL_SCORE1 + " INTEGER, " +
            COL_PLAYER2 + " TEXT, " +
            COL_SCORE2 + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String player1, int score1, String player2, int score2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PLAYER1, player1);
        values.put(COL_SCORE1, score1);
        values.put(COL_PLAYER2, player2);
        values.put(COL_SCORE2, score2);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public boolean updateScore(long id, int newScore1, int newScore2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SCORE1, newScore1);
        values.put(COL_SCORE2, newScore2);

        int rowsAffected = db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public void resetdatabase()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        onUpgrade(db,1,1);
        db.close();
    }

}
