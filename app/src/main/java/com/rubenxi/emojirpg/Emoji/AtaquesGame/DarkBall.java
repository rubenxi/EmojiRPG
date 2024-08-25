package com.rubenxi.emojirpg.Emoji.AtaquesGame;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubenxi.emojirpg.Emoji.AnimacionImpacto;
import com.rubenxi.emojirpg.Emoji.Ataque;
import com.rubenxi.emojirpg.Emoji.Enemy;
import com.rubenxi.emojirpg.Emoji.Me;
import com.rubenxi.emojirpg.Emoji.ReverseInterpolator;

public class DarkBall extends Ataque {

    public int potencia = 3;
    public String attackEmoji = "\uD83C\uDF11";
    private int velocidad = 200;
    private int velocidadImpacto = 50;
    private int velocidadMe = 50;
    private float transparencia = 0.75F;

    public DarkBall(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me) {
        super(enemy, attack, context, linearLayout, me);
        this.setEmoji(attackEmoji);
        this.setPotencia(potencia);
    }
    @Override
    public void iniciarAnimacion(Boolean enemyAttacks){
        resetAttack();

        TextView atacante;
        if (enemyAttacks){
            atacante=enemy.enemyTextView;
        }
        else{
            atacante=me.meTextView;
        }
        attack.setVisibility(View.INVISIBLE);
        atacante.setScaleX(1.1f);
        atacante.setScaleY(1.1f);

        attack.setText(attackEmoji);
        attack.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);


        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.05f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.05f);


        animationMove.setDuration(velocidadMe); 

        if (enemyAttacks) {
            animationMove.setInterpolator(new ReverseInterpolator()); 
        }
        else{
            animationMove.setInterpolator(new LinearInterpolator()); 

        }
        animationMove.setRepeatCount(0);
        animationMove.setStartOffset(1000);
        animationMove.setFillAfter(true);
        atacante.startAnimation(animationMove);


        Animation animation;
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.3f,
                Animation.RELATIVE_TO_PARENT, 0.3f,
                Animation.RELATIVE_TO_PARENT, 0.3f,
                Animation.RELATIVE_TO_PARENT, -0.3f);


        animation.setDuration(velocidad); 
        if (enemyAttacks) {
            animation.setInterpolator(new ReverseInterpolator()); 
        }
        else{
            animation.setInterpolator(new LinearInterpolator()); 

        }
        animation.setStartOffset(1000);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);



        Animation animationAlpha;
        animationAlpha = new AlphaAnimation(0.0F,0.95F);

        animationAlpha.setDuration(1000); 

        animationAlpha.setInterpolator(new LinearInterpolator()); 


        animationAlpha.setRepeatCount(0);
        animationAlpha.setFillAfter(true);




        Animation animationRotation;
        animationRotation = new RotateAnimation(0F,360F,Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);

        animationRotation.setDuration(100); 
        animationRotation.setInterpolator(new LinearInterpolator()); 

        animationRotation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART); 
        animationRotation.setFillAfter(true);

        AnimationSet animationSet1 = new AnimationSet(false);
        animationSet1.setFillAfter(true);
        animationSet1.addAnimation(animationRotation);
        animationSet1.addAnimation(animation);
        animationSet1.addAnimation(animationAlpha);

        attack.startAnimation(animationSet1);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                AnimacionImpacto animacionImpacto = new AnimacionImpacto(velocidadImpacto, enemy, context, potencia, attack, linearLayout, me);
                animacionImpacto.animar(enemyAttacks);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
