package com.rubenxi.emojirpg.Emoji;

import android.content.Context;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ataque implements IAtaque {
    public int potencia;
    public AnimationSet animationSet;
    public TextView attack;
    public Enemy enemy;
    public Me me;
    public Context context;
    public LinearLayout linearLayout;
    public String attackEmoji;
    public Ataque(Enemy enemy, TextView attack, Context context, LinearLayout linearLayout, Me me){
    this.enemy = enemy;
    this.attack = attack;
    this.context = context;
    this.linearLayout = linearLayout;
    this.me = me;
    }


    public void setPotencia(int potencia){
        this.potencia = potencia;
    }
    public void setEmoji(String attackEmoji){
        this.attackEmoji=attackEmoji;
    }

    public void iniciarAnimacion(Boolean enemyAttacks) {

    }

    public void resetAttack(){
        attack.setAlpha(1.0F);
            attack.setScaleX(1.0f);
            attack.setScaleY(1.0f);
    }
}
