package com.rubenxi.emojirpg.Emoji.AtaquesGame;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubenxi.emojirpg.Emoji.AnimacionImpacto;
import com.rubenxi.emojirpg.Emoji.Ataque;
import com.rubenxi.emojirpg.Emoji.Enemy;
import com.rubenxi.emojirpg.Emoji.Me;
import com.rubenxi.emojirpg.Emoji.ReverseInterpolator;

public class Fire extends Ataque {

    public int potencia = 1;
    private int velocidad = 800;
    private int velocidadImpacto = 50;
    private int velocidadMe = 50;
    private float transparencia = 0.95F;
    public String attackEmoji = "\uD83D\uDD25";

    public Fire(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me) {
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

        attack.setAlpha(transparencia);

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
        attack.startAnimation(animation);
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
