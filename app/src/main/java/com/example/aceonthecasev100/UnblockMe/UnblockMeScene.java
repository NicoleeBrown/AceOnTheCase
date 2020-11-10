package com.example.aceonthecasev100.UnblockMe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.example.aceonthecasev100.GraphicsToolkit;
import com.example.aceonthecasev100.R;
import com.example.aceonthecasev100.SceneManager;

import java.util.ArrayList;

public class UnblockMeScene implements Scene {

    public static ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Point> playerPointers = new ArrayList<>();

    public static boolean gameOver = false;

    private SceneManager sceneManager;

    public UnblockMeScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        players.add(new Player());

        // Horizontal Blocks
        players.add(new HorizontalPlayerBlock(2));
        players.add(new HorizontalPlayerBlock(2));
        players.add(new HorizontalPlayerBlock(3));
        players.add(new HorizontalPlayerBlock(3));
        players.add(new HorizontalPlayerBlock(2));
        // Vertical blocks
        players.add(new VerticalPlayerBlock());
        players.add(new VerticalPlayerBlock());
        players.add(new VerticalPlayerBlock());
        players.add(new VerticalPlayerBlock());
        // Boundaries
        int offset = GraphicsToolkit.OFFSET/2;
        players.add(new Boundary(GraphicsToolkit.SCREEN_WIDTH,offset));
        players.add(new Boundary(GraphicsToolkit.SCREEN_WIDTH, offset));
        players.add(new Boundary(offset, GraphicsToolkit.SCREEN_HEIGHT)); // Left side
        players.add(new Boundary(GraphicsToolkit.BLOCK_WIDTH, (GraphicsToolkit.BLOCK_HEIGHT + offset) * 2)); // Right side
        players.add(new Boundary(GraphicsToolkit.BLOCK_WIDTH, (GraphicsToolkit.BLOCK_HEIGHT + offset) * 2));
        players.add(new Boundary(offset, GraphicsToolkit.BLOCK_HEIGHT));

        reset();
    }
    public void reset() {

        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(0), GraphicsToolkit.getGridPosV(2)));
        // Horizontal Blocks
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(3), GraphicsToolkit.getGridPosV(1)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(5), GraphicsToolkit.getGridPosV(3)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(0), GraphicsToolkit.getGridPosV(4)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(4), GraphicsToolkit.getGridPosV(0)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(0), GraphicsToolkit.getGridPosV(0)));
        // Vertical Blocks
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(6), GraphicsToolkit.getGridPosV(1)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(5), GraphicsToolkit.getGridPosV(1)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(3), GraphicsToolkit.getGridPosV(2)));
        playerPointers.add(new Point(GraphicsToolkit.getGridPosH(4), GraphicsToolkit.getGridPosV(2)));
        // Boundaries
        int offset = GraphicsToolkit.OFFSET/2;
        playerPointers.add(new Point(0, 0));
        playerPointers.add(new Point(0, GraphicsToolkit.SCREEN_HEIGHT - offset));
        playerPointers.add(new Point(0, 0));
        playerPointers.add(new Point(GraphicsToolkit.SCREEN_WIDTH - GraphicsToolkit.BLOCK_WIDTH, 0));
        playerPointers.add(new Point(GraphicsToolkit.SCREEN_WIDTH - GraphicsToolkit.BLOCK_WIDTH, GraphicsToolkit.getGridPosV(3)));
        playerPointers.add(new Point(GraphicsToolkit.SCREEN_WIDTH - offset, GraphicsToolkit.getGridPosV(2)));


        for(int i = 0; i < players.size() && i < playerPointers.size(); i++) // consistency check
        {
            players.get(i).update(playerPointers.get(i));
        }
    }
    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if(!gameOver) {
            for(Player p : players)
            {
                p.receiveTouch(event);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable d = GraphicsToolkit.CURRENT_CONTEXT.getDrawable(R.drawable.bg_unblockme);
        d.setBounds(0,0, GraphicsToolkit.SCREEN_WIDTH, GraphicsToolkit.SCREEN_HEIGHT);
        d.draw(canvas);


        for(Player p : players)
        {
            p.draw(canvas);
        }

        if(gameOver) {
            Paint paint = new Paint();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(Color.GREEN);
            paint.setTextSize(150);
            canvas.drawText("Good job!", GraphicsToolkit.SCREEN_WIDTH/2, GraphicsToolkit.SCREEN_HEIGHT/2, paint);
        }
    }

    @Override
    public void update() {
        if(!gameOver) {
            for(Player p : players)
            {
                p.update();
            }
        }
        else
        {
            endGame();
        }
    }

    public void endGame()
    {
        sceneManager.endGame();
    }
}
