package com.rubenxi.emojirpg.Emoji.AtaquesGame;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubenxi.emojirpg.AllEmojis;
import com.rubenxi.emojirpg.Emoji.AnimacionImpacto;
import com.rubenxi.emojirpg.Emoji.Ataque;
import com.rubenxi.emojirpg.Emoji.Enemy;
import com.rubenxi.emojirpg.Emoji.Me;
import com.rubenxi.emojirpg.Emoji.ReverseInterpolator;

import java.util.Random;

public class Transform extends Ataque {

    public int potencia = 1;
    public String attackEmoji = "\uD83C\uDFAD";
    private int velocidadImpacto = 50;
    private int velocidadMe = 50;
    private float transparencia = 0.75F;

    public Transform(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me) {
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



        Animation alphaAnimation;
        alphaAnimation = new AlphaAnimation(1.0F,0.0F);
        alphaAnimation.setDuration(1000); 
        alphaAnimation.setInterpolator(new LinearInterpolator()); 
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setStartOffset(1000);



        Animation animationScale;
        animationScale = new ScaleAnimation(
                0.0F,
                20.0F,
                0.0F,
                20.0F,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);


        animationScale.setDuration(2000); 
        animationScale.setInterpolator(new LinearInterpolator()); 
        animationScale.setRepeatCount(0);
        animationScale.setFillAfter(true);



        Animation animationPosition;
        if (enemyAttacks){
            animationPosition = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.3f,
                    Animation.RELATIVE_TO_PARENT, 0.3f,
                    Animation.RELATIVE_TO_PARENT, -0.3f,
                    Animation.RELATIVE_TO_PARENT, -0.3f);
        }
        else {
            animationPosition = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, -0.1f,
                    Animation.RELATIVE_TO_PARENT, -0.1f,
                    Animation.RELATIVE_TO_PARENT, 0.2f,
                    Animation.RELATIVE_TO_PARENT, 0.2f);
        }

        animationPosition.setDuration(0); 
        if (enemyAttacks) {
            animationPosition.setInterpolator(new ReverseInterpolator()); 
        }
        else{
            animationPosition.setInterpolator(new LinearInterpolator()); 

        }
        animationPosition.setRepeatCount(0);
        animationPosition.setFillAfter(true);


        AnimationSet animationSet1 = new AnimationSet(false);
        animationSet1.setFillAfter(true);
        animationSet1.addAnimation(animationScale);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.addAnimation(animationPosition);

        attack.startAnimation(animationSet1);
        animationSet1.setAnimationListener(new Animation.AnimationListener() {
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

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        int randomNumber = random.nextInt(new AllEmojis().emojiListArray.size());

                        String emoji = (String) new AllEmojis().emojiListArray.get(randomNumber);
                        atacante.setText(emoji);
                        if (enemyAttacks){
                            enemy.estadoEnemyTextView.setText("\uD83C\uDFAD");
                            enemy.estadoEnemyTextView.setVisibility(View.VISIBLE);
                        }else{
                            me.estadoMeTextView.setText("\uD83C\uDFAD");
                            me.estadoMeTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }, 500);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
