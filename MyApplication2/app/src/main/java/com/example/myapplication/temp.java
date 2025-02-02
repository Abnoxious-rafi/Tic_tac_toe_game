package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class temp extends AppCompatActivity {

    long id=0;
    void insertdata(DatabaseHelper dbHelper,String p1,String p2,int scr1,int scr2)
    {
        id=dbHelper.insertData(p1, scr1, p2, scr2);
    }
    void updatedata(DatabaseHelper dbHelper,int score1,int score2)
    {
        boolean isUpdated = dbHelper.updateScore(id, score1, score2);
    }
    List<Object[]> showdata(DatabaseHelper dbHelper)
    {
        List<Object[]> matches = new ArrayList<>();

        Cursor cursor = dbHelper.getAllData();
        if (cursor.getCount() == 0) {
            return matches;
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String player1 = cursor.getString(1);
                int score1 = cursor.getInt(2);
                String player2 = cursor.getString(3);
                int score2 = cursor.getInt(4);
                matches.add(new Object[]{player1, score1, player2, score2});
            }
        }
        cursor.close();
        return matches;
    }
    void deletedatabase(DatabaseHelper dbHelper)
    {
        dbHelper.resetdatabase();
    }
}
