package com.example.aceonthecasev100;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Cutscene {

    private ImageView imageView;
    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    private int  dialogueCounter = 0;
    public static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get screen dimensions & store in a constant class
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        GraphicsToolkit.initializeConstants(dm, this, this);


        //System.out.println(getApplicationContext().getAssets());
        //typeface.createFromAsset(this.getAssets(),"fonts/bubblegumsans.ttf");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.characterImage);
        handleAnimation(imageView);
        mdialogues = getResources().getStringArray(R.array.array_of_dialogues);
        initText();
    }

    public void handleAnimation (View view){
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        float scrolltovalue = screenWidth * 65 / 100;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "x", scrolltovalue);
        animatorX.setDuration(animationDuration);
        animatorX.start();
    }


    private void initText(){

        characterName = findViewById(R.id.characterName);
        dialogueName = findViewById(R.id.dialogueContent);


        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);



        dialogueName.setText(mdialogues[dialogueCounter]);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.nextBtn: {


                if( dialogueCounter < mdialogues.length) {

                    if(dialogueCounter == 0)
                        dialogueCounter = 1;

                    dialogueName.setText(mdialogues[dialogueCounter++]);

                    if(dialogueCounter == mdialogues.length){
                        nextBtn.setText("GO");
                    }
                }
                else{
                    endScene();
                }
            }
        }
    }

    @Override
    public void endScene(){
        Intent intent = new Intent(this, Scene2Activity.class);
        startActivity(intent);

    }



}
