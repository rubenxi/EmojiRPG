package com.rubenxi.emojirpg.Adventure;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Random;

public class AnimacionNube {
    TextView nube;
    Animation animationMove;
    int size, speed;
    float randomY1;
    Random random = new Random();

    public AnimacionNube(TextView nube){
        this.nube=nube;
    }

    public void animacionNube(){
         size = 20 + random.nextInt(35); 
         speed = 20000 + random.nextInt(10000); 
         randomY1 = -0.01F + (0.01F - (-0.01F)) * random.nextFloat();

        nube.setTextSize(size); 


        nube.setText("☁️");
        nube.setVisibility(View.VISIBLE);
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, randomY1,
                Animation.RELATIVE_TO_PARENT, randomY1);


        animationMove.setDuration(speed); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);
        nube.startAnimation(animationMove);
        animationMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animacionNube();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
