package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.aceonthecasev100.GraphicsToolkit;
import com.example.aceonthecasev100.R;

import java.util.Random;

public class HorizontalPlayerBlock extends Player
{

    public HorizontalPlayerBlock(int size) // This is the constructor with the size. // Suggestion, make a fixed number for y, and set x to 1-4. Depending on what number it is (size) color it differently
    {
        this.rectangle = new Rect(0,0,size * GraphicsToolkit.BLOCK_WIDTH, GraphicsToolkit.BLOCK_HEIGHT);
        Random rand = new Random();
        this.color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        this.size = size;
        isPlayer = false;
    }

    @Override
    public void draw(Canvas canvas) {

        Drawable d = GraphicsToolkit.CURRENT_CONTEXT.getDrawable(R.drawable.gems);

        switch (size) {
            case 2:
                d = GraphicsToolkit.CURRENT_CONTEXT.getDrawable(R.drawable.chest);
                break;
            case 3:
                break;
        }
        d.setBounds(this.playerPoint.x, this.playerPoint.y, this.playerPoint.x + rectangle.width(),
                this.playerPoint.y + rectangle.height());
        d.draw(canvas);

    }


}
