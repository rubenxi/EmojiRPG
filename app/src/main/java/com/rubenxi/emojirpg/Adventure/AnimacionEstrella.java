package com.rubenxi.emojirpg.Adventure;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.Random;

public class AnimacionEstrella {
    Random random = new Random();
    Animation animationRotation;
    AnimationSet animationSet1;


    int size, speed, speedRota;
    float randomY1,randomY2;
    TextView nube;
    Animation animationMove;

    public AnimacionEstrella(TextView nube){
        this.nube=nube;
    }
    public void animacionEstrellas(){
         size = 10 + random.nextInt(30); 
         speed = 800 + random.nextInt(1000); 
         speedRota = 100 + random.nextInt(300); 

        nube.setTextSize(size); 


         randomY1 = 0.0F + (0.2F - (0.0F)) * random.nextFloat();
         randomY2 = 0.2F + (0.5F - (0.2F)) * random.nextFloat();
        nube.setText("⭐");
        nube.setVisibility(View.VISIBLE);
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, randomY1,
                Animation.RELATIVE_TO_PARENT, randomY2);


        animationMove.setDuration(speed); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);



        animationRotation = new RotateAnimation(0F,360F,Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);

        animationRotation.setDuration(speedRota); 
        animationRotation.setInterpolator(new LinearInterpolator()); 

        animationRotation.setRepeatCount(Animation.INFINITE);
        animationRotation.setRepeatMode(Animation.RESTART); 
        animationRotation.setFillAfter(true);

        animationSet1 = new AnimationSet(false);
        animationSet1.setFillAfter(true);
        animationSet1.addAnimation(animationRotation);
        animationSet1.addAnimation(animationMove);

        nube.startAnimation(animationSet1);
        animationMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animacionEstrellas();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
