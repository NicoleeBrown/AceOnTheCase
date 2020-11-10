package com.example.aceonthecasev100;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Scene8Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private ImageView sheriffImageView;

    private ImageView mayorImageView;

    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    public  int[] acesdialog = {0,3,4,5,9,11,17};

    public  int[] mayordialogue = {1,2,6,16,18,23,25,26};
    public  int[] sheriffdialogue = {7,8,10,12,13,14,15,19,20,21,22,24,27,28,29,30,31,32};


    private int  dialogueCounter = 0;


    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene8);

        imageView = findViewById(R.id.piace);
        imageView.setVisibility(View.VISIBLE);

        sheriffImageView = findViewById(R.id.sheriff);
        sheriffImageView.setVisibility(View.INVISIBLE);

        mayorImageView = findViewById(R.id.mayorbean);
        mayorImageView.setVisibility(View.INVISIBLE);

        handleAnimation(imageView);
        beanAnimation(mayorImageView);
        nuggetAnimation(sheriffImageView);

        mdialogues = getResources().getStringArray(R.array.array_of_dialogues_scene8);
        initText();

        linearLayout = findViewById(R.id.linearLayout);
    }

    public void handleAnimation (View view){
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        float scrolltovalue = screenWidth * 68 / 100;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "x", scrolltovalue);
        animatorX.setDuration(animationDuration);
        animatorX.start();
    }

    public void beanAnimation (View view){
        int screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        float scrolltovalue = screenWidth * 3 / 100;
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "x", -scrolltovalue);
        animatorX.setDuration(animationDuration);
        animatorX.start();
    }

    public void nuggetAnimation (View view){
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
                    if (isAceSpeaking(dialogueCounter))
                    {
                        characterName.setText("DETECTIVE ACE");
                        imageView.setVisibility(View.VISIBLE);
                        sheriffImageView.setVisibility(View.INVISIBLE);
                        mayorImageView.setVisibility(View.INVISIBLE);
                    }
                    else if (isMayorSpeaking(dialogueCounter))
                    {
                        characterName.setText("MAYOR BEAN");
                        mayorImageView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        sheriffImageView.setVisibility(View.INVISIBLE);

                    }
                    else if (isSheriffSpeaking(dialogueCounter))
                    {
                        characterName.setText("SHERIFF NUGGET");
                        sheriffImageView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        mayorImageView.setVisibility(View.INVISIBLE);

                    }

                    dialogueName.setText(mdialogues[dialogueCounter++]);

                    if(dialogueCounter == mdialogues.length)
                    {
                        nextBtn.setText("GO");
                    }
                }
                else{
                    Intent intent = new Intent(this, Scene9Activity.class);
                    startActivity(intent);
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

    private boolean isMayorSpeaking( int index){

        // determine if its aces time to speak or not
        for (int element : mayordialogue) {
            if (element == index) {
                return true;
            }
        }
        return false;
    }
    private boolean isSheriffSpeaking( int index){

        // determine if its aces time to speak or not
        for (int element : sheriffdialogue) {
            if (element == index) {
                return true;
            }
        }
        return false;
    }
}
