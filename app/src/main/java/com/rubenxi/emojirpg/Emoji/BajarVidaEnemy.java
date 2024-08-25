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

public class BajarVidaEnemy {
    public int vidaenemy, vidaBaseEnemy;
    public Enemy enemy;
    public TextView hpEnemy;
    public Context context;
    public Me me;
    public int potencia;
    public BajarVidaEnemy(Enemy enemy, Context context, int potencia, Me me){
        this.enemy=enemy;
        this.vidaenemy=enemy.vida;
        this.vidaBaseEnemy=enemy.vidaBase;
        this.hpEnemy = enemy.hpEnemy;
        this.context = context;
        this.potencia = potencia;
        this.me = me;
    }

    public int bajarVida(){
        vidaenemy=vidaenemy-potencia;
        if (vidaenemy<=0){
            vidaenemy=0;
        }
        if (vidaenemy>=5){
            vidaenemy=5;
        }
        enemy.vida=vidaenemy;
        StringBuilder vida = new StringBuilder();
        for (int i = 0; i<vidaenemy; i++){
            vida.append("\uD83D\uDFE2");
        }
        for (int i = 0; i<vidaBaseEnemy-vidaenemy; i++){
            vida.append("\uD83D\uDD34");
        }
        hpEnemy.setText(vida.toString());
        if (vidaenemy<=0){
            enemyMuere();
        }
        return vidaenemy;
    }

    public void enemyMuere(){
        int velocidad = 1000;

        enemy.estadoEnemyTextView.setVisibility(View.INVISIBLE);

        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.05f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -0.05f);


        animationMove.setDuration(velocidad); 
        animationMove.setInterpolator(new LinearInterpolator()); 
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
                Animation.RELATIVE_TO_SELF, 0.8F,
                Animation.RELATIVE_TO_SELF,0.4F);


        animationScale.setDuration(velocidad); 
        animationScale.setInterpolator(new LinearInterpolator()); 
        animationScale.setRepeatCount(0);
        animationScale.setFillAfter(true);




        AnimationSet animations = new AnimationSet(true);
        animations.setFillAfter(true);
        animations.addAnimation(animationScale);
        animations.addAnimation(animation5);
        animations.addAnimation(animationMove);
        
        enemy.enemyTextView.startAnimation(animations);
        animations.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                enemy.enemyTextView.setAlpha(0.0F);
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                int nivel = Integer.parseInt(databaseHelper.getNivel(me.meEmoji));
                databaseHelper.setNivel(me.meEmoji, String.valueOf(nivel+1));
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
