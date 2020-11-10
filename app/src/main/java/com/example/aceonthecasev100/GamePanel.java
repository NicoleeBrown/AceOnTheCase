package com.example.aceonthecasev100;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback  {
    private MainThread thread;
    private SceneManager manager;
    private Cutscene callerActivity;

    public GamePanel(Cutscene callerActivity, Context context)
    {
        super(context);
        this.callerActivity = callerActivity;

        getHolder().addCallback(this);

        GraphicsToolkit.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager(this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry) { //Sometimes it takes multiple tries to get it to work
            try {
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        manager.receiveTouch(event);
        return true;
    }

    public void update()
    {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);
    }

    public void endGame() {
        thread.setRunning(false);
        callerActivity.endScene();
    }

}
