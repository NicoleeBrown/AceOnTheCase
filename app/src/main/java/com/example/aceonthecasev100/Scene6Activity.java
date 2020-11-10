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

public class Scene6Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private ImageView sheriffImageView;
    private TextView characterName;
    private TextView dialogueName;
    private Button nextBtn;
    long animationDuration = 1000; //milliseconds
    public  String[] mdialogues;
    public  int[] acesdialog = {0,2,3,4,5,9,12,15,20,21};
    private int  dialogueCounter = 0;


    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene6);

        imageView = findViewById(R.id.piace);
        imageView.setVisibility(View.VISIBLE);
        sheriffImageView = findViewById(R.id.sheriff);
        sheriffImageView.setVisibility(View.INVISIBLE);

        handleAnimation(imageView);
        handleAnimationforbean(sheriffImageView);

        mdialogues = getResources().getStringArray(R.array.array_of_dialogues_scene6);
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
                        sheriffImageView.setVisibility(View.INVISIBLE);
                    }
                    else{
                        characterName.setText("SHERIFF NUGGET");
                        sheriffImageView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);

                    }

                    dialogueName.setText(mdialogues[dialogueCounter++]);

                    if(dialogueCounter == mdialogues.length){
                        nextBtn.setText("GO");
                    }
                }
                else{
                    Intent intent = new Intent(this, Scene7Activity.class);
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
}
