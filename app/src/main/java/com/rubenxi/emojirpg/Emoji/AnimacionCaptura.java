package com.rubenxi.emojirpg.Emoji;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.GachaActivity;

import java.util.Random;

public class AnimacionCaptura {

    public TextView attack;
    Animation animationMove;

    public final float pointAX = 1.0F;
    public final float pointAY = 1.0F;
    public final float pointBX = 2.0F;
    public final float pointBY = 2.0F;
    public final float centerX = (pointAX + pointBX)/2;
    public final float centerY = pointAY - 2.0F;
    public final float radius = (float) Math.hypot(centerX - pointAX, centerY-pointAY);
    public ValueAnimator animator = ValueAnimator.ofFloat(0,100);
    public LinearLayout linearLayout;
    public int velocidad = 500;
    public Enemy enemy;
    public Context context;

    public AnimacionCaptura(TextView attack, LinearLayout linearLayout, Enemy enemy, Context context){
        this.attack = attack;
        this.linearLayout=linearLayout;
        this.enemy = enemy;
        this.context = context;
    }

    public void animar() {
        Random random = new Random();
        int probabilidad = 100/enemy.vida;
        int probabilidadEscape = 100/2;

        int randomNumber2 = random.nextInt(100)+1;
        if (randomNumber2 <= probabilidad/2) {
            animarCaptura();
        }
        else{
            randomNumber2 = random.nextInt(100)+1;
            if (randomNumber2 <= probabilidadEscape) {
                animarEscapa();
            }
            else{
                animarNoEscapa();
            }
        }
    }
    public void animarCaptura(){
        attack.setScaleY(0.5F);
        attack.setScaleX(0.5F);
        attack.setVisibility(View.INVISIBLE);
        attack.setText("⭐");
        attack.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        enemy.estadoEnemyTextView.setVisibility(View.INVISIBLE);


        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 0.25f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, -0.25f);


        animationMove.setDuration(velocidad); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);
        attack.startAnimation(animationMove);


        animationMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                attack.setText("\uD83C\uDF1F");
                Animation animationScale;
                animationScale = new ScaleAnimation(
                        1.0F,
                        0.0F,
                        1.0F,
                        0.0F,
                        Animation.RELATIVE_TO_SELF, 1.0F,
                        Animation.RELATIVE_TO_SELF,0.0F);


                animationScale.setDuration(1000); 
                animationScale.setInterpolator(new LinearInterpolator()); 
                animationScale.setRepeatCount(0);
                animationScale.setFillAfter(true);



                Animation animation5;
                animation5 = new AlphaAnimation(1.0F,0.0F);
                animation5.setDuration(1000); 
                animation5.setInterpolator(new LinearInterpolator()); 
                animation5.setRepeatCount(0);
                animation5.setFillAfter(true);

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setFillAfter(true);
                animationSet.addAnimation(animation5);
                enemy.enemyTextView.startAnimation(animationSet);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Captura captura = new Captura(enemy,context);
                        captura.capturar();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void animarEscapa(){

        attack.setScaleY(0.5F);
        attack.setScaleX(0.5F);
        attack.setVisibility(View.INVISIBLE);
        attack.setText("⭐");
        attack.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);

        enemy.estadoEnemyTextView.setVisibility(View.INVISIBLE);

        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 0.25f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, -0.25f);


        animationMove.setDuration(velocidad); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);
        attack.startAnimation(animationMove);


        animationMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation animationMove;
                animationMove = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.5f);


                animationMove.setDuration(velocidad/2); 
                animationMove.setInterpolator(new LinearInterpolator()); 
                animationMove.setRepeatCount(0);
                        animationMove.setFillAfter(true);
                attack.startAnimation(animationMove);


                animationMove.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation animationScale;
                        animationScale = new ScaleAnimation(
                                1.0F,
                                0.0F,
                                1.0F,
                                0.0F,
                                Animation.RELATIVE_TO_SELF, 0.5F,
                                Animation.RELATIVE_TO_SELF,0.5F);


                        animationScale.setDuration(1000); 
                        animationScale.setInterpolator(new LinearInterpolator()); 
                        animationScale.setRepeatCount(0);
                        animationScale.setFillAfter(true);

                        animation = new TranslateAnimation(
                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                Animation.RELATIVE_TO_PARENT, 1.0f,
                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                Animation.RELATIVE_TO_PARENT, -1.0f);


                        animation.setDuration(1000); 
                        animation.setInterpolator(new LinearInterpolator()); 
                        animation.setRepeatCount(0);
                        animation.setRepeatMode(Animation.REVERSE); 
                        animation.setFillAfter(true);

                        AnimationSet animationSet = new AnimationSet(true);
                        animationSet.setFillAfter(true);

                        animationSet.addAnimation(animation);
                        animationSet.addAnimation(animationScale);
                        enemy.enemyTextView.startAnimation(animationSet);

                        animationSet.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Toast.makeText(context, enemy.enemyEmoji+" fled...", Toast.LENGTH_SHORT).show();

                                irGacha();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void animarNoEscapa(){

        attack.setScaleY(0.5F);
        attack.setScaleX(0.5F);
        attack.setVisibility(View.INVISIBLE);
        attack.setText("⭐");
        attack.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);


        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 0.25f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, -0.25f);


        animationMove.setDuration(velocidad); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);
        attack.startAnimation(animationMove);


        animationMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation animationMove;
                animationMove = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.25f,
                        Animation.RELATIVE_TO_PARENT, -0.5f);


                animationMove.setDuration(velocidad/2); 
                animationMove.setInterpolator(new LinearInterpolator()); 
                animationMove.setRepeatCount(0);
                        animationMove.setFillAfter(true);
                attack.startAnimation(animationMove);

                animationMove.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        attack.setText("");
                        Animation animationMove;
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
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                enemy.estadoEnemyTextView.setVisibility(View.VISIBLE);
                                enemy.estadoEnemyTextView.setText("‼️");
                                enemy.estadoEnemyTextView.startAnimation(animationMove);
                            }
                        }, 500);
                        animationMove.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        AtaqueEnemigo ataqueEnemigo = new AtaqueEnemigo(enemy, attack);
                                        ataqueEnemigo.atacar();
                                    }
                                }, 500);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
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
