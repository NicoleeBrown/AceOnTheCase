/*  Created by:     Isabela Rangel
    Created on:     2019-10-20
    Modified by:    Isabela Rangel
    Last modified:  2019-10-25
*/
package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.example.aceonthecasev100.GraphicsToolkit;
import com.example.aceonthecasev100.R;

public class Player implements GameObject {

    protected Rect rectangle;
    protected int color;
    protected Point playerPoint;
    protected boolean movingPlayer = false;
    protected int positionX;
    protected int positionY;
    protected int size;
    protected double touchInitialX = 0, touchInitialY = 0;
    protected boolean isPlayer;

    @Override
    public Rect getRectangle() { // This is going to be used for collisions. The hitbox is the rectangle
        return rectangle;
    }

    // Constructors
    public Player ()
    {
        this.rectangle = new Rect(0, 0, GraphicsToolkit.BLOCK_WIDTH, GraphicsToolkit.BLOCK_HEIGHT );
        this.color = Color.RED;
        this.size = 1;
        isPlayer = true;
    }

    @Override
    public void draw(Canvas canvas) {/*
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);*/
        Drawable d = GraphicsToolkit.CURRENT_CONTEXT.getDrawable(R.drawable.ace_topview);
        d.setBounds(this.playerPoint.x, this.playerPoint.y, this.playerPoint.x + rectangle.width(),
                this.playerPoint.y + rectangle.height());
        d.draw(canvas);
    }

    @Override
    public void update() {
        rectangle.set(this.playerPoint.x, this.playerPoint.y, this.playerPoint.x + rectangle.width(),
                this.playerPoint.y + rectangle.height()); // left,top,right,bottom
    }

    public void update(Point point){ // this function forces it to a certain position
        this.playerPoint = point;
        update();
    }

    @Override
    public void receiveTouch(MotionEvent event)
    {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: // When finger is placed in the screen // can get initial x,y to determine direction it's moving to
                if(this.getRectangle().contains((int)event.getX(),(int)event.getY()))
                {
                    movingPlayer = true;
                    touchInitialX = event.getRawX();
                    touchInitialY = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE: // When finger moves, get x,y and set it. Collision mechanics
                if(movingPlayer)
                {
                    double movementDir = event.getRawX() - touchInitialX;
                    if((movementDir > 0) && !collisionCheck(this.playerPoint.x + this.rectangle.width() + 2, this.playerPoint.y + 5)) // If going right
                        this.playerPoint.set((int) event.getX() - rectangle.width() / 2, this.playerPoint.y);
                    else if ((movementDir < 0) && !collisionCheck(this.playerPoint.x - 2, this.playerPoint.y + 5 )) // if going left
                        this.playerPoint.set((int) event.getX() - rectangle.width() / 2, this.playerPoint.y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(collisionCheck(this.playerPoint.x + this.rectangle.width() + 2, this.playerPoint.y + 5)) // if colliding right
                    positionX = GraphicsToolkit.findGridPosH(this.playerPoint.x - 20, isPlayer, size);
                else if(collisionCheck(this.playerPoint.x - 2, this.playerPoint.y + 5 ))
                    positionX = GraphicsToolkit.findGridPosH(this.playerPoint.x + 20, isPlayer, size);
                else
                    positionX = GraphicsToolkit.findGridPosH(this.playerPoint.x, isPlayer, size);

                this.playerPoint.set(positionX, this.playerPoint.y);


                if(rectangle.contains(GraphicsToolkit.getGridPosH(7)+15, GraphicsToolkit.getGridPosV(2)+15)) {
                    try {Thread.sleep(1);} catch (Exception e) {}
                    UnblockMeScene.gameOver = true;
                }

                movingPlayer = false;

                break;
        }
    }
    // checks in the position passed. Meaning it can check up, down, left or right if arguments are passed correctly
    public boolean collisionCheck(int x, int y)
    {
        for(Player p : UnblockMeScene.players)
        {
            if(p.getRectangle().contains(x, y))
                return true;
        }
        return false;
    }


}
