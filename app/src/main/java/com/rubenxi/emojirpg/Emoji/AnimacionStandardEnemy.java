package com.rubenxi.emojirpg.Emoji;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class AnimacionStandardEnemy {
    TextView enemy;
    Animation animation;

    public AnimacionStandardEnemy(TextView enemy){
        this.enemy = enemy;

    }

    public void animar(){
        enemy.setScaleX(1.0F);
        enemy.setScaleY(1.0F);

        int velocidad = 500;
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.03f);


        animation.setDuration(velocidad); 
        animation.setInterpolator(new LinearInterpolator()); 
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE); 
        animation.setFillAfter(true);
        enemy.startAnimation(animation);
    }
}
