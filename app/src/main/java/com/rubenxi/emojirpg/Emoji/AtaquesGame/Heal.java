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

public class Heal extends Ataque {

    public int potencia = -1;
    public String attackEmoji = "\uD83D\uDC93";
    private int velocidad = 1500;
    private int velocidadImpacto = 50;
    private int velocidadMe = 50;
    private float transparencia = 0.75F;

    public Heal(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me) {
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
        animation.setRepeatCount(0);
        animation.setFillAfter(true);



        Animation animationAlpha;
        animationAlpha = new AlphaAnimation(1.0F,0.0F);

        animationAlpha.setDuration(1000); 
        animationAlpha.setStartOffset(500);
        animationAlpha.setInterpolator(new LinearInterpolator()); 


        animationAlpha.setRepeatCount(0);
        animationAlpha.setFillAfter(true);


        Animation animationRotation;
        animationRotation = new RotateAnimation(0F,360F,Animation.RELATIVE_TO_SELF, 0.55F,
                Animation.RELATIVE_TO_SELF,0.55F);

        animationRotation.setDuration(1000); 
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
