package com.rubenxi.emojirpg.Emoji.AtaquesGame;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubenxi.emojirpg.Emoji.AnimacionImpacto;
import com.rubenxi.emojirpg.Emoji.Ataque;
import com.rubenxi.emojirpg.Emoji.Enemy;
import com.rubenxi.emojirpg.Emoji.Me;
import com.rubenxi.emojirpg.Emoji.Mirror;
import com.rubenxi.emojirpg.Emoji.ReverseInterpolator;

public class Surf extends Ataque {

    public int potencia = 2;
    public String attackEmoji = "\uD83C\uDF0A";
    private int velocidad = 2000;
    private int velocidadImpacto = 50;
    private int velocidadMe = 1000;
    private float transparencia = 0.75F;

    public Surf(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me) {
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
            Mirror mirror = new Mirror(attack);
            mirror.mirrorHorizontal();
        }
        else{
            atacante=me.meTextView;
        }

        attack.setVisibility(View.INVISIBLE);
        atacante.setScaleX(1.1f);
        atacante.setScaleY(1.1f);
        attack.setAlpha(transparencia);

        attack.setText(attackEmoji);
        attack.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);


        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.005f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.05f);


        animationMove.setDuration(velocidadMe); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);
        atacante.startAnimation(animationMove);



        Animation animation;
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, -0.3f);


        animation.setDuration(velocidad); 
        if (enemyAttacks){
            animation.setInterpolator(new ReverseInterpolator());
        }
        else{
            animation.setInterpolator(new LinearInterpolator()); 
        }
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        Animation animationScale;
        animationScale = new ScaleAnimation(
                1.0F,
                10.0F,
                1.0F,
                10.0F,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);


        animationScale.setDuration(velocidad); 
        animationScale.setInterpolator(new LinearInterpolator()); 
        animationScale.setRepeatCount(0);
        animationScale.setFillAfter(true);
        AnimationSet animationSet= new AnimationSet(false);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(animationScale);
        animationSet.addAnimation(animation);
        attack.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
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
