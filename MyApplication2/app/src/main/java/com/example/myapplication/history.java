package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class history extends AppCompatActivity {
    DatabaseHelper dbhelper;
    temp t;
    TableLayout tlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        dbhelper = new DatabaseHelper(this);

        t = new temp();
        List<Object[]> store= t.showdata(dbhelper);

        tlayout = findViewById(R.id.tblay);
        addTableHeader();

        for (Object[] match : store) {
            addTableRow((String) match[0],(int)match[1],(String) match[2],(int)match[3]);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void del(View view)
    {
        t.deletedatabase(dbhelper);
        Activity activity = history.this;
        activity.recreate();
    }
    private void addTableHeader() {
        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.GRAY);

        addCellstring(tableRow, "Player 1", Color.WHITE);
        addCellstring(tableRow, "Score 1", Color.WHITE);
        addCellstring(tableRow, "Player 2", Color.WHITE);
        addCellstring(tableRow, "Score 2", Color.WHITE);

        tlayout.addView(tableRow);
    }
    private void addTableRow(String player1, int score1, String player2, int score2) {
        TableRow tableRow = new TableRow(this);
//        tableRow.setBackgroundColor(Color.GRAY);

        addCellstring(tableRow, player1, Color.BLACK);
        addCellint(tableRow, score1, Color.BLACK);
        addCellstring(tableRow, player2, Color.BLACK);
        addCellint(tableRow, score2, Color.BLACK);

        tlayout.addView(tableRow);
    }
    private void addCellstring(TableRow row, String text, int textColor) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setPadding(16, 8, 16, 8);
        textView.setGravity(Gravity.CENTER);
        row.addView(textView);
    }
    private void addCellint(TableRow row, int text, int textColor) {
        TextView textView = new TextView(this);
        textView.setText(""+text);
        textView.setTextColor(textColor);
        textView.setPadding(16, 8, 16, 8);
        textView.setGravity(Gravity.CENTER);
        row.addView(textView);
    }
}