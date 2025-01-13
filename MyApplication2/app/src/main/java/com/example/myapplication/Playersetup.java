package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Playersetup extends AppCompatActivity {
    private EditText player1,player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playersetup);
        player1 = findViewById(R.id.playername1);
        player2 = findViewById(R.id.playername2);
    }
    public void nextButtonclick(View view)
    {
        String player1name = player1.getText().toString(),player2name = player2.getText().toString();
        if ((player2name == null) || (player1name == null) || player2name.equals(player1name)) {

        Toast toast = Toast.makeText(this, "players name can't be empty or same", Toast.LENGTH_SHORT);
        toast.show();
        return;
        }
        Intent intent = new Intent(this,Gameplay.class);
        intent.putExtra("Player_name",new String[]{player1name,player2name});
        startActivity(intent);
    }
    public void backbtnonclick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); // Go to ActivityB
        finish();
    }
}