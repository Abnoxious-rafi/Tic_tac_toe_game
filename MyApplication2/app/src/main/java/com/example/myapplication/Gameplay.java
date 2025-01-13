package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Gameplay extends AppCompatActivity {
    private static int[][] a = new int[4][4];
    TextView turnflip1,turnflip2;
    String[] playernam;
    Tic_tac_toe ticTacToe;
    private static int turn=1,pl1score=0,pl2score=0,counter=0;
    private TextView txview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);
        Intent intent=getIntent();

        turnflip1=findViewById(R.id.textView7);
        turnflip2=findViewById(R.id.textView8);

        playernam = intent.getStringArrayExtra("Player_name");
        TextView tx1=findViewById(R.id.textView5),tx2=findViewById(R.id.textView6);
        tx1.setText(playernam[0]+" : "+pl1score);
        tx2.setText(playernam[1]+" : "+pl2score);

        txview=findViewById(R.id.textView4);

        ticTacToe=findViewById(R.id.tic_tac_toe);
//        ticTacToe.giveline(0);

        if(turn==1) {
            turnflip1.setVisibility(View.VISIBLE);
            turnflip1.setBackgroundResource(R.drawable.online_button);
            turnflip2.setVisibility(View.INVISIBLE);
        }
        else {
            turnflip2.setVisibility(View.VISIBLE);
            turnflip2.setBackgroundResource(R.drawable.online_button);
            turnflip1.setVisibility(View.INVISIBLE);
        }
    }
    public void reuse()
    {
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                a[i][j]=0;
        counter=0;
        ConstraintLayout cont = findViewById(R.id.main_layout);
        for (int i = 0; i < cont.getChildCount(); i++) {
            View child = cont.getChildAt(i);
            if (child instanceof Button) {
                Button btn = (Button) child;
                if(btn.getId() != R.id.button16)
                {
                    btn.setClickable(true);
                    btn.setBackgroundResource(R.drawable.cell_button);
                }
            }
        }
        txview.setText("Tic Tac Toe");
        ticTacToe.clearCanvas();
    }
    public void win_celebration()
    {
        if(turn == 1)
            pl1score++;
        else
            pl2score++;
        txview.setText(playernam[turn-1]+" Win..");
        TextView tx1=findViewById(R.id.textView5),tx2=findViewById(R.id.textView6);
        tx1.setText(playernam[0]+" "+pl1score);
        tx2.setText(playernam[1]+" "+pl2score);

        ConstraintLayout cont = findViewById(R.id.main_layout);
        for (int i = 0; i < cont.getChildCount(); i++) {
            View child = cont.getChildAt(i);
            if (child instanceof Button) {
                Button btn = (Button) child;
                if( btn.getId() !=  R.id.button16 )
                  btn.setClickable(false);
            }
        }

    }
    public boolean checkwhin()
    {
        if(counter==9)
            txview.setText("Draw");
        if(a[1][1]!=0)
        {
            if(a[1][2]==a[1][1] &&  a[1][3]==a[1][1]) {
                ticTacToe.giveline(3);//right
                return true;
            }
            if(a[2][1]==a[1][1] &&  a[3][1]==a[1][1]) {
                ticTacToe.giveline(0);//right
                return true;
            }
        }
        if(a[3][3]!=0)
        {
            if(a[1][3]==a[3][3] &&  a[2][3]==a[3][3]) {
                ticTacToe.giveline(2);
                return true;
            }
            if(a[3][1]==a[3][3] &&  a[3][2]==a[3][3]) {
                ticTacToe.giveline(5);
                return true;
            }
        }
        if(a[2][2]!=0)
        {
            if(a[1][1]==a[2][2] && a[3][3]==a[2][2]){
                ticTacToe.giveline(6);//right
                return true;
            }
            if(a[1][3]==a[2][2] && a[3][1]==a[2][2])
            {
                ticTacToe.giveline(7);//right
                return true;
            }
            if(a[1][2]==a[2][2] && a[3][2]==a[2][2]){
                ticTacToe.giveline(1);//right
                return true;
            }
            if(a[2][1]==a[2][2] && a[2][3]==a[2][2]){
                ticTacToe.giveline(4);
                return true;
            }
        }

        if(turn==1) {
            turnflip2.setVisibility(View.VISIBLE);
            turnflip2.setBackgroundResource(R.drawable.online_button);
            turnflip1.setVisibility(View.INVISIBLE);
            turn=2;
        }
        else {
            turnflip1.setVisibility(View.VISIBLE);
            turnflip1.setBackgroundResource(R.drawable.online_button);
            turnflip2.setVisibility(View.INVISIBLE);
            turn=1;
        }
        return false;
    }
    public void homebuttonclick(View view)
    {
        reuse();
        pl1score=0;
        pl2score=0;
        turn=1;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void playagainbuttonclick(View view)
    {
        reuse();
    }
    public void backbtnonclick(View view)
    {
        reuse();
        pl1score=0;
        pl2score=0;
        turn=1;
        Intent intent = new Intent(this, Playersetup.class);
        startActivity(intent); // Go to ActivityB
        finish();
    }
    public void onbutton11click(View view)
    {
        if(a[1][1]==0)
        {
            counter++;
            Button b = findViewById(R.id.button6);
            if(turn==1)
            b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[1][1] = ( ( turn == 1 ) ? 1 : 2 );

            if(checkwhin())
               win_celebration();

        }
    }
    public void onbutton12click(View view)
    {
        if(a[1][2]==0)
        {
            counter++;
            Button b = findViewById(R.id.button7);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[1][2] = ( (turn == 1)? 1 : 2 );

            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton13click(View view)
    {
        if(a[1][3]==0)
        {
            counter++;
            Button b = findViewById(R.id.button8);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[1][3] = ( ( turn == 1 ) ? 1 : 2 );

            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton21click(View view)
    {
        if(a[2][1]==0)
        {
            counter++;
            Button b = findViewById(R.id.button9);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[2][1] = ( ( turn == 1 ) ? 1 : 2 );
            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton22click(View view)
    {
        if(a[2][2]==0)
        {
            counter++;
            Button b = findViewById(R.id.button10);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[2][2] = ( ( turn == 1 ) ? 1 : 2 );
            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton23click(View view)
    {
        if(a[2][3]==0)
        {
            counter++;
            Button b = findViewById(R.id.button11);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[2][3] = ( ( turn == 1 ) ? 1 : 2 );
            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton31click(View view)
    {
        if(a[3][1]==0)
        {
            counter++;
            Button b = findViewById(R.id.button12);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[3][1] = ( ( turn == 1 ) ? 1 : 2 );

            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton32click(View view)
    {
        if(a[3][2]==0)
        {
            counter++;
            Button b = findViewById(R.id.button13);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[3][2] = ( ( turn == 1 ) ? 1 : 2 );

            if(checkwhin())
                win_celebration();
        }
    }
    public void onbutton33click(View view)
    {
        if(a[3][3]==0)
        {
            counter++;
            Button b = findViewById(R.id.button14);
            if(turn==1)
                b.setBackgroundResource(R.drawable.nicecrs);
            else
                b.setBackgroundResource(R.drawable.niceo);
            a[3][3] = ( ( turn == 1 ) ? 1 : 2 );

            if(checkwhin())
                win_celebration();
        }
    }
}