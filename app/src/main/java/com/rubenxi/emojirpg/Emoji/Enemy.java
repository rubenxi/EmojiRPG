package com.rubenxi.emojirpg.Emoji;

import android.widget.TextView;

import java.util.ArrayList;

public class Enemy {
    public int vidaBase, vida;
    public TextView enemyTextView, hpEnemy, estadoEnemyTextView;
    public String enemyEmoji;
    public ArrayList<Ataque> ataques;
    public Enemy(int vida, int vidaBase, TextView enemyTextView, String enemyEmoji, TextView hpEnemy, TextView estadoEnemyTextView){
        this.vida = vida;
        this.vidaBase = vidaBase;
        this.enemyTextView = enemyTextView;
        this.enemyEmoji=enemyEmoji;
        this.hpEnemy = hpEnemy;
        this.estadoEnemyTextView = estadoEnemyTextView;
    }
    public void setAtaques(ArrayList<Ataque> ataques){
        this.ataques = ataques;
    }

}
