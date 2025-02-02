
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ai_play extends AppCompatActivity {

    public String player1name;
    private EditText player1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        player1=findViewById(R.id.playername3);
    }
    public void nextButton(View view)
    {
        player1name = player1.getText().toString();
        if (player1name.length() == 0) {
            Toast toast = Toast.makeText(this, "players name can't be empty", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Intent intent = new Intent(this, play_with_ai.class);
        intent.putExtra("Player_name",new String[]{player1name});
        startActivity(intent);
    }
    public void backbtnclick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}