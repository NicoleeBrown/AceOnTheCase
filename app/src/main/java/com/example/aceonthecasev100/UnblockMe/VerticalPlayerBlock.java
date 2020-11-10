package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.example.aceonthecasev100.GraphicsToolkit;
import com.example.aceonthecasev100.R;

import java.util.Random;

public class VerticalPlayerBlock extends Player {

    public VerticalPlayerBlock() // This is the constructor with the size. // Suggestion, make a fixed number for y, and set x to 1-4. Depending on what number it is (size) color it differently
    {
        this.rectangle = new Rect(0,0, GraphicsToolkit.BLOCK_WIDTH,2 * GraphicsToolkit.BLOCK_HEIGHT);
        Random rand = new Random();
        this.color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        isPlayer = false;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(this.getRectangle().contains((int)event.getX(),(int)event.getY()))
                {
                    movingPlayer = true;
                    touchInitialX = event.getRawX();
                    touchInitialY = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE: // When finger moves, get x,y
                if(movingPlayer)
                {
                    double movementDir = event.getRawY() - touchInitialY;
                    if((movementDir > 0) && !collisionCheck(this.playerPoint.x + 2, this.playerPoint.y + this.rectangle.height() + 2)) // If going down
                        this.playerPoint.set(this.playerPoint.x, (int)event.getY() - this.rectangle.height() / 2);
                    else if ((movementDir < 0) && !collisionCheck(this.playerPoint.x + 2, this.playerPoint.y - 2 )) // if going up
                        this.playerPoint.set(this.playerPoint.x, (int)event.getY() - this.rectangle.height() / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (collisionCheck(this.playerPoint.x + 2, this.playerPoint.y + this.rectangle.height() + 2))
                    positionY = GraphicsToolkit.findGridPosV(this.playerPoint.y - 20);
                if (collisionCheck(this.playerPoint.x + 2, this.playerPoint.y - 2 ))
                    positionY = GraphicsToolkit.findGridPosV(this.playerPoint.y + 20);
                else
                    positionY = GraphicsToolkit.findGridPosV(this.playerPoint.y);

                this.playerPoint.set(this.playerPoint.x, positionY);

                movingPlayer = false;
                break;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        Drawable d = GraphicsToolkit.CURRENT_CONTEXT.getDrawable(R.drawable.goldbars);
        d.setBounds(this.playerPoint.x, this.playerPoint.y, this.playerPoint.x + rectangle.width(),
                this.playerPoint.y + rectangle.height());
        d.draw(canvas);
    }



}
