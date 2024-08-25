package com.rubenxi.emojirpg.Emoji;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

public class BajarVidaMe {
    public int vidaMe, vidaBaseMe;
    public Me me;
    public TextView hpMe;
    public Context context;
    public int potencia;
    public BajarVidaMe(Me me, Context context, int potencia){
        this.me=me;
        this.vidaMe=me.vida;
        this.vidaBaseMe=me.vidaBase;
        this.hpMe = me.hpMe;
        this.context = context;
        this.potencia = potencia;
    }

    public int bajarVida(){
        vidaMe=vidaMe-potencia;
        if (vidaMe<=0){
            vidaMe=0;
        }
        if (vidaMe>=5){
            vidaMe=5;
        }
        me.vida=vidaMe;
        StringBuilder vida = new StringBuilder();
        for (int i = 0; i<vidaMe; i++){
            vida.append("\uD83D\uDFE2");
        }
        for (int i = 0; i<vidaBaseMe-vidaMe; i++){
            vida.append("\uD83D\uDD34");
        }
        hpMe.setText(vida.toString());
        if (vidaMe<=0){
            meMuere();
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.setVida(me.meEmoji, String.valueOf(vidaMe));
        return vidaMe;
    }

    public void meMuere(){
        me.estadoMeTextView.setVisibility(View.INVISIBLE);

        int velocidad = 1000;


        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.05f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.05f);


        animationMove.setDuration(velocidad); 
        animationMove.setInterpolator(new ReverseInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);

        Animation animation5;
        animation5 = new AlphaAnimation(1.0F,0.0F);
        animation5.setDuration(velocidad);
        animation5.setInterpolator(new LinearInterpolator());
        animation5.setRepeatCount(0);
        animation5.setFillAfter(true);


        Animation animationScale;
        animationScale = new ScaleAnimation(
                1.0F,
                0.0F,
                1.0F,
                0.0F,
                Animation.RELATIVE_TO_SELF, -0.4F,
                Animation.RELATIVE_TO_SELF,1.5F);


        animationScale.setDuration(velocidad);
        animationScale.setInterpolator(new LinearInterpolator());
        animationScale.setRepeatCount(0);
        animationScale.setFillAfter(true);

        AnimationSet animations = new AnimationSet(false);
        animations.setFillAfter(true);
        animations.addAnimation(animationScale);
        animations.addAnimation(animation5);
        animations.addAnimation(animationMove);
        me.meTextView.startAnimation(animations);
        animations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                me.meTextView.setAlpha(0.0F);
                irGacha();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void irGacha() {
        Intent intent = new Intent(context, MapaActivity.class);
        context.startActivity(intent);
    }
}
