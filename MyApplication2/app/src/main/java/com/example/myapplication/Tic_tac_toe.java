package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class Tic_tac_toe extends View {
    private int boardColor;
    private int xcolor;
    private int ocolor;
    private boolean[] dline = new boolean[8];
    private int winninglinecolor;
    private float wrows,wcols,wrowe,wcole;
    private Paint paint=new Paint();
    private int cellsize=getMeasuredWidth()/3;
    public Tic_tac_toe(Context context,AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs ,R.styleable.Tic_tac_toe,0,0);
        try{
            boardColor = a.getInteger(R.styleable.Tic_tac_toe_boardColor,0);
            xcolor = a.getInteger(R.styleable.Tic_tac_toe_Xcolor,0);
            ocolor = a.getInteger(R.styleable.Tic_tac_toe_ocolor,0);
            winninglinecolor = a.getInteger(R.styleable.Tic_tac_toe_winninglinecolor,0);
        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width,int height)
    {
        super.onMeasure(width,height);
        int mnlength=Math.min(getMeasuredHeight(),getMeasuredWidth());
        cellsize=mnlength/3;
        setMeasuredDimension(mnlength,mnlength);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameboard(canvas);
        for(int i=0;i<8;i++)
        {
            if(dline[i])
            {
                if(i==0)
                    drawwinili(canvas,0,0,1);
                else if (i==1)
                    drawwinili(canvas,0,1,1);
                else if (i==2)
                    drawwinili(canvas,0,2,1);
                else if (i==3)
                    drawwinili(canvas,0,0,2);
                else if (i==4)
                    drawwinili(canvas,1,0,2);
                else if (i==5)
                    drawwinili(canvas,2,0,2);
                else if (i==6)
                    drawwinili(canvas,0,0,3);
                else if (i==7)
                    drawwinili(canvas,0,2,4);
            }
        }
    }
    public void giveline(int pos)
    {
        dline[pos]=true;
        invalidate();
    }
    private void drawGameboard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(15);
        for(int columb=1;columb<3;columb++) {
            canvas.drawLine(cellsize * columb, 0, cellsize * columb, canvas.getWidth(), paint);
        }
        for(int row=1;row<3;row++) {
            canvas.drawLine(0, cellsize * row, canvas.getWidth(), cellsize * row, paint);
        }

    }
    public void drawwinili(Canvas canvas,int row,int col,int styl)
    {
            if(styl==1)
            {
                wcols= (cellsize/2 + col*cellsize);
                wrows=cellsize/2;
                wcole=wcols;
                wrowe=(cellsize/2+2*cellsize);
            }
            else if(styl==2)
            {
                wcols= cellsize/2;
                wrows=(cellsize/2 + row*cellsize);
                wcole=(cellsize/2+2*cellsize);
                wrowe=wrows;
            } else if (styl==3) {
                wcols= cellsize/2;
                wrows=cellsize/2;
                wcole=cellsize/2+2*cellsize;
                wrowe=cellsize/2+2*cellsize;
            }
            else {
                wcols= (cellsize/2 + col*cellsize);
                wrows=cellsize/2;
                wcole=wrows;
                wrowe=wcols;
            }

        paint.setColor(Color.parseColor("#C668F7"));
        paint.setStrokeWidth(55);
        canvas.drawLine(wcols,wrows,wcole,wrowe,paint);
    }
    public void clearCanvas() {
        for(int i=0;i<8;i++)
            dline[i]=false;
        invalidate();
    }

}
