package com.example.aceonthecasev100;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Scene2Activity extends AppCompatActivity implements Cutscene {

    private ImageView imageView;
    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    private int  dialogueCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene2);

        imageView = findViewById(R.id.characterImage);
        handleAnimation(imageView);
        mdialogues = getResources().getStringArray(R.array.array_of_dialogues_scene2);
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
                if(dialogueCounter < mdialogues.length) {

                    if(dialogueCounter == 0)
                        dialogueCounter = 1;

                    if(dialogueCounter == 6)
                    {
                        imageView.setImageResource(R.drawable.pi_ace);
                    }

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
        Intent intent = new Intent(this, Scene3Activity.class);
        startActivity(intent);

    }
}
