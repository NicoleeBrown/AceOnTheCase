package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public interface GameObject {
    public Rect getRectangle();
    public void draw(Canvas canvas);
    public void update();
    public void receiveTouch(MotionEvent event);
}
