package com.rubenxi.emojirpg.Emoji;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AnimacionImpacto {
    public int velocidadImpacto;
    public Enemy enemy;
    public Me meMe;
    public TextView hpEnemy, hpMe, me, attack;
    public LinearLayout linearLayout;
    public Context context;
    public int potencia;
    public AnimacionImpacto(int velocidadImpacto, Enemy enemy, Context context, int potencia, TextView attack, LinearLayout linearLayout, Me meMe){
        this.velocidadImpacto=velocidadImpacto;
        this.enemy=enemy;
        this.hpEnemy=enemy.hpEnemy;
        this.context=context;
        this.potencia=potencia;
        this.me=meMe.meTextView;
        this.attack=attack;
        this.linearLayout=linearLayout;
        this.meMe = meMe;
        this.hpMe = meMe.hpMe;
    }


    public void animar(Boolean meRecive){
        Random random = new Random();
        int probabilidad = 90;
        int randomNumber2 = random.nextInt(100)+1;
        if (randomNumber2 <= probabilidad) {


            TextView reciver;
            if (meRecive){
                reciver=me;
            }
            else{
                reciver=enemy.enemyTextView;
            }
            Animation animation1;
            animation1 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.05f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);


            animation1.setDuration(velocidadImpacto); 
            animation1.setInterpolator(new LinearInterpolator()); 
            animation1.setRepeatCount(0);
            animation1.setFillAfter(true);
            reciver.startAnimation(animation1);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    Animation animation2;
                    animation2 = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 0.05f,
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 0.0f,
                            Animation.RELATIVE_TO_PARENT, 0.05f);


                    animation2.setDuration(velocidadImpacto); 
                    animation2.setInterpolator(new LinearInterpolator()); 
                    animation2.setRepeatCount(0);
                    animation2.setFillAfter(true);
                    reciver.startAnimation(animation2);
                    animation2.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            Animation animation3;
                            animation3 = new TranslateAnimation(
                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                    Animation.RELATIVE_TO_PARENT, -0.05f,
                                    Animation.RELATIVE_TO_PARENT, 0.05f,
                                    Animation.RELATIVE_TO_PARENT, 0.0f);


                            animation3.setDuration(velocidadImpacto); 
                            animation3.setInterpolator(new LinearInterpolator()); 
                            animation3.setRepeatCount(0);
                            animation3.setFillAfter(true);
                            reciver.startAnimation(animation3);
                            animation3.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                    Animation animation4;
                                    animation4 = new TranslateAnimation(
                                            Animation.RELATIVE_TO_PARENT, -0.05f,
                                            Animation.RELATIVE_TO_PARENT, 0.0f,
                                            Animation.RELATIVE_TO_PARENT, 0.0f,
                                            Animation.RELATIVE_TO_PARENT, -0.05f);


                                    animation4.setDuration(velocidadImpacto); 
                                    animation4.setInterpolator(new LinearInterpolator()); 
                                    animation4.setRepeatCount(0);
                                    animation4.setFillAfter(true);
                                    reciver.startAnimation(animation4);
                                    animation4.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {

                                            Animation animation5;
                                            animation5 = new TranslateAnimation(
                                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                                    Animation.RELATIVE_TO_PARENT, 0.0f,
                                                    Animation.RELATIVE_TO_PARENT, -0.05f,
                                                    Animation.RELATIVE_TO_PARENT, 0.0f);


                                            animation5.setDuration(velocidadImpacto); 
                                            animation5.setInterpolator(new LinearInterpolator()); 
                                            animation5.setRepeatCount(0);
                                            animation5.setFillAfter(true);
                                            reciver.startAnimation(animation5);
                                            animation5.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation) {

                                                }

                                                @Override
                                                public void onAnimationEnd(Animation animation) {
                                                    finalizarImpacto(meRecive);
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

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }else{
            if (meRecive){
                Toast.makeText(context, enemy.enemyEmoji+" missed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, meMe.meEmoji+" missed", Toast.LENGTH_SHORT).show();
            }
            AnimacionStandardEnemy animacionStandardEnemy = new AnimacionStandardEnemy(enemy.enemyTextView);
            animacionStandardEnemy.animar();
            AnimacionStandardMe animacionStandardme = new AnimacionStandardMe(me);
            animacionStandardme.animar();
            attack.setVisibility(View.INVISIBLE);
            attack.setText("");
            if (!meRecive){
                
                if (enemy.vida>0) {
                    AtaqueEnemigo ataqueEnemigo = new AtaqueEnemigo(enemy, attack);
                    ataqueEnemigo.atacar();
                }
            }
            else if (enemy.vida!=0 && meMe.vida!=0) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        }


    }
    public void finalizarImpacto(Boolean meRecive){

                AnimacionStandardEnemy animacionStandardEnemy = new AnimacionStandardEnemy(enemy.enemyTextView);
                animacionStandardEnemy.animar();
                AnimacionStandardMe animacionStandardme = new AnimacionStandardMe(me);
                animacionStandardme.animar();

                if (meRecive){
                    BajarVidaMe bajarVidaMe = new BajarVidaMe(meMe,context, potencia);
                    bajarVidaMe.bajarVida();
                }
                else {
                    BajarVidaEnemy bajarVidaEnemy = new BajarVidaEnemy(enemy, context, potencia, meMe);
                    bajarVidaEnemy.bajarVida();
                }
                attack.setVisibility(View.INVISIBLE);
                attack.setText("");
                if (!meRecive){
                    
                    if (enemy.vida>0) {
                        AtaqueEnemigo ataqueEnemigo = new AtaqueEnemigo(enemy, attack);
                        ataqueEnemigo.atacar();
                    }
                }
                else if (enemy.vida!=0 && meMe.vida!=0) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

}
