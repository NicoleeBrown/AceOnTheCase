package com.example.aceonthecasev100;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.aceonthecasev100.UnblockMe.Scene;
import com.example.aceonthecasev100.UnblockMe.UnblockMeScene;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    private GamePanel gamePanel;

    public static int ACTIVE_SCENE;

    public SceneManager(GamePanel gamePanel) {
        ACTIVE_SCENE = 0; // this is for a game with multiple scenes
        this.gamePanel = gamePanel;
        scenes.add(new UnblockMeScene(this));
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update(){
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void endGame(){
        gamePanel.endGame();
    }
}
