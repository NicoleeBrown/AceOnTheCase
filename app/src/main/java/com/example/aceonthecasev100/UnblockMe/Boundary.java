package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.aceonthecasev100.GraphicsToolkit;

public class Boundary extends Player {

    public Boundary(int width, int height) {
        isPlayer = false;
        this.rectangle = new Rect(0,0,width, height);
    }


    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(33,15,2));
        //paint.setColor(Color.rgb(74,76,75));
        canvas.drawRect(rectangle,paint);
    }
    @Override
    public void update() {
    }
    @Override
    public void update(Point point){ // this function forces it to a certain position
        this.playerPoint = point;
        rectangle.set(this.playerPoint.x, this.playerPoint.y, this.playerPoint.x + rectangle.width(),
                this.playerPoint.y + rectangle.height()); // left,top,right,bottom
    }
    @Override
    public void receiveTouch(MotionEvent event) {
    }
}
