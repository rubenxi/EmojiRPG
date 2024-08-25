package com.rubenxi.emojirpg.Emoji;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class AnimacionSorpresa {
    Animation animationMove;


    public AnimacionSorpresa(){
    }

    public void animar(TextView textView){

        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.025f);


        animationMove.setDuration(50); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(1);
        animationMove.setRepeatMode(Animation.REVERSE); 
        animationMove.setFillAfter(true);
        textView.startAnimation(animationMove);
    }
}
