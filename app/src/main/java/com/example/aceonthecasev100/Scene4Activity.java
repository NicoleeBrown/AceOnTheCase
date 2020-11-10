package com.example.aceonthecasev100;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Scene4Activity extends AppCompatActivity implements Cutscene {

    private ImageView imageView;
    private ImageView mayorBeanImageView;
    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    public  int[] acesdialog = {0,3,4,5,6,};
    private int  dialogueCounter = 0;
    GamePanel gamePanel;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene4);

        gamePanel = new GamePanel(this, this);

        imageView = findViewById(R.id.characterImage);
        imageView.setVisibility(View.VISIBLE);
        mayorBeanImageView = findViewById(R.id.mayorbean);
        mayorBeanImageView.setVisibility(View.INVISIBLE);

        handleAnimation(imageView);
        handleAnimationforbean(mayorBeanImageView);

        mdialogues = getResources().getStringArray(R.array.array_of_dialogues_scene4);
        initText();

        linearLayout = findViewById(R.id.linearLayout);
    }

    public void handleAnimation (View view){
        //int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        int screenWidth = GraphicsToolkit.SCREEN_WIDTH;
        float scrolltovalue = screenWidth * 68 / 100;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "x", scrolltovalue);
        animatorX.setDuration(animationDuration);
        animatorX.start();
    }

    public void handleAnimationforbean (View view){
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        float scrolltovalue = screenWidth * 3 / 100;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "x", -scrolltovalue);
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


                    //determine who is speaking
                    if (isAceSpeaking(dialogueCounter)) {
                        characterName.setText("DETECTIVE ACE");
                        imageView.setVisibility(View.VISIBLE);
                        mayorBeanImageView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        characterName.setText("MAYOR BEAN");
                        mayorBeanImageView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);

                    }

                    dialogueName.setText(mdialogues[dialogueCounter++]);

                    if(dialogueCounter == mdialogues.length){
                        nextBtn.setText("GO");
                    }
                }
                else{
                    setContentView(gamePanel);
                }
            }
        }
    }


    private boolean isAceSpeaking( int index){

        // determine if its aces time to speak or not
        for (int element : acesdialog) {
            if (element == index) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void endScene() {
        Intent intent = new Intent(this, Scene5Activity.class);
        startActivity(intent);
    }
}
