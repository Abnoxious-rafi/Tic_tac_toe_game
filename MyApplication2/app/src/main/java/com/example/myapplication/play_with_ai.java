package com.example.myapplication;

import android.content.Intent;
import android.health.connect.datatypes.SleepSessionRecord;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class play_with_ai extends AppCompatActivity {
    Tic_tac_toe ttt;
    public String[] playernam;
    public char[] board = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    public char player = 'X';
    public char ai = 'O';
    public int playr=0;
    public int pai=0,pnt=0;
    public int[][] WINNING_COMBOS = {
            { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
            { 0, 4, 8 }, { 2, 4, 6 }
    };
    Button[] btn= new Button[9];
    TextView[] txtvi = new TextView[5];
    private void idbuild()
    {
        ttt=findViewById(R.id.tic_tac_toe2);
        btn[0]=findViewById(R.id.button20);
        btn[1]=findViewById(R.id.button21);
        btn[2]=findViewById(R.id.button22);
        btn[3]=findViewById(R.id.button23);
        btn[4]=findViewById(R.id.button24);
        btn[5]=findViewById(R.id.button25);
        btn[6]=findViewById(R.id.button26);
        btn[7]=findViewById(R.id.button27);
        btn[8]=findViewById(R.id.button28);
        txtvi[0]=findViewById(R.id.textView10);
        txtvi[1]=findViewById(R.id.textView11);
        txtvi[2]=findViewById(R.id.textView12);
    }
    public void wini(char p)
    {
        if(checkWinner()==p){
        if(p=='X')
        {
            playr+=1;
            txtvi[0].setText(playernam[0]+" WIN");
        }
        else {
            pai+=1;
            txtvi[0].setText("AI WIN");
        }
        txtvi[1].setText(playernam[0]+" : "+playr);
        txtvi[2].setText("AI : "+pai);
        for(int i=0;i<9;i++)
            btn[i].setClickable(false);
        if(pnt==0)
            ttt.giveline(3);
        else if(pnt==1)
            ttt.giveline(4);
        else if(pnt==2)
            ttt.giveline(5);
        else if(pnt==3)
            ttt.giveline(0);
        else if(pnt==4)
            ttt.giveline(1);
        else if(pnt==5)
            ttt.giveline(2);
        else if(pnt==6)
            ttt.giveline(6);
        else if(pnt==7)
            ttt.giveline(7);
        }
    }
    public char checkWinner() {
        pnt=0;
        for (int[] combo : WINNING_COMBOS) {
            if ((board[combo[0]] != ' ') && (board[combo[0]] == board[combo[1]]) && (board[combo[1]] == board[combo[2]])) {
                return board[combo[0]];
            }
            pnt++;
        }
        return ' ';
    }
    public void reusee()
    {
        txtvi[0].setText("TIC TOC TOE");
        for(int i=0;i<9;i++) {
            btn[i].setBackgroundResource(R.drawable.cell_button);
            btn[i].setClickable(true);
            board[i]=' ';
        }
        ttt.clearCanvas();
    }

    public int minimax(boolean isMaximizing) {
        char winner = checkWinner();
        if (winner == ai)
            return 10;
        if (winner == player)
            return -10;
        if (isBoardFull())
            return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = ai;
                    int score = minimax(false);
                    board[i] = ' ';
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    board[i] = player;
                    int score = minimax(true);
                    board[i] = ' ';
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }
    public void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = ai;
                int score = minimax(false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        board[move] = ai;
        btn[move].setBackgroundResource(R.drawable.niceo);
    }
    public boolean isBoardFull() {
        for (char cell : board) {
            if (cell == ' ')
                return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.play_with_ai);
        idbuild();
        Intent intent=getIntent();
        playernam = intent.getStringArrayExtra("Player_name");
        txtvi[1].setText(playernam[0] + " : "+playr);
        txtvi[2].setText("AI : "+pai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //
    public void onbutton11click(View view)
    {
        if(board[0]==' ')
        {
            btn[0].setBackgroundResource(R.drawable.nicecrs);
            board[0]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    public void onbutton12click(View view)
    {
        if(board[1]==' ')
        {
            btn[1].setBackgroundResource(R.drawable.nicecrs);
            board[1]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    public void onbutton13click(View view)
    {
         if(board[2]==' ')
        {
            btn[2].setBackgroundResource(R.drawable.nicecrs);
            board[2]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
               wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton21click(View view)
    {
        if(board[3]==' ')
        {
            btn[3].setBackgroundResource(R.drawable.nicecrs);
            board[3]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton22click(View view)
    {
        if(board[4]==' ')
        {
            btn[4].setBackgroundResource(R.drawable.nicecrs);
            board[4]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton23click(View view)
    {
        if(board[5]==' ')
        {
            btn[5].setBackgroundResource(R.drawable.nicecrs);
            board[5]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton31click(View view)
    {
        if(board[6]==' ')
        {
            btn[6].setBackgroundResource(R.drawable.nicecrs);
            board[6]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton32click(View view)
    {
        if(board[7]==' ')
        {
            btn[7].setBackgroundResource(R.drawable.nicecrs);
            board[7]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void onbutton33click(View view)
    {
        if(board[8]==' ')
        {
            btn[8].setBackgroundResource(R.drawable.nicecrs);
            board[8]='X';
            wini('X');
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                aiMove();
                wini('O');
            }
            if(isBoardFull())
            {
                txtvi[0].setText("Draw..!");
                Toast toast = Toast.makeText(this, "Draw", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }
    public void playagain(View view)
    {
        reusee();
    }
    public void homebuttonclick(View view)
    {
        reusee();
        playr=0;
        pai=0;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void backbtnonclick(View view)
    {
        reusee();
        playr=0;
        pai=0;
        Intent intent = new Intent(this, Ai_play.class);
        startActivity(intent); // Go to ActivityB
        finish();
    }
}