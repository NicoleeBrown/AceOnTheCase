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

public class Scene9Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private ImageView sheriffImageView;
    private ImageView mayorImageView;
    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    public  int[] acesdialog = {0,1,6,11,24,27,28,29,30,31,33,34,34};
    public  int[] mayordialogue = {2,3,4,5,7,8,13,14,20,21,22,23,32,36};
    public  int[] sheriffdialogue = {9,10,12,15,16,17,18,19,25,26};
    private int  dialogueCounter = 0;


    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene9);

        imageView = findViewById(R.id.piace);
        imageView.setVisibility(View.VISIBLE);

        sheriffImageView = findViewById(R.id.sheriff);
        sheriffImageView.setVisibility(View.INVISIBLE);

        mayorImageView = findViewById(R.id.mayorbean);
        mayorImageView.setVisibility(View.INVISIBLE);

        handleAnimation(imageView);
        beanAnimation(mayorImageView);
        nuggetAnimation(sheriffImageView);

        mdialogues = getResources().getStringArray(R.array.array_of_dialogues_scene9);
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
    // determine who is speaking
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.nextBtn: {
                if( dialogueCounter < mdialogues.length) {

                    if(dialogueCounter == 0)
                        dialogueCounter = 1;


                    //if dialogueCounter = isAceSpeaking then display Ace dialogue
                    if (isAceSpeaking(dialogueCounter))
                    {
                        characterName.setText("DETECTIVE ACE");
                        imageView.setVisibility(View.VISIBLE);
                        sheriffImageView.setVisibility(View.INVISIBLE);
                        mayorImageView.setVisibility(View.INVISIBLE);
                    }
                    //if dialogueCounter = isMayorSpeaking then display Mayor dialogue
                    else if (isMayorSpeaking(dialogueCounter))
                    {
                        characterName.setText("MAYOR BEAN");
                        mayorImageView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        sheriffImageView.setVisibility(View.INVISIBLE);

                    }
                    //if dialogueCounter = isSheriffSpeaking then display Sheriff dialogue
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
                    break;
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

        // determine if its the Mayors time to speak or not
        for (int element : mayordialogue) {
            if (element == index) {
                return true;
            }
        }
        return false;
    }
    private boolean isSheriffSpeaking( int index){

        // determine if its the Sheriff time to speak or not
        for (int element : sheriffdialogue) {
            if (element == index) {
                return true;
            }
        }
        return false;
    }
}
