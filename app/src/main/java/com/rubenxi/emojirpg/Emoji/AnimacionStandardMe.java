package com.rubenxi.emojirpg.Emoji;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class AnimacionStandardMe {
    TextView me;
    Animation animation;

    public AnimacionStandardMe(TextView me){
        this.me = me;

    }

    public void animar(){
        me.setScaleX(1.0F);
        me.setScaleY(1.0F);


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
        me.startAnimation(animation);

    }
}
