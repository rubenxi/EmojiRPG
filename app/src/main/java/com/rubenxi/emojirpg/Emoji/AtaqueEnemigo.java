package com.rubenxi.emojirpg.Emoji;

import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AtaqueEnemigo {
    public Enemy enemy;
    public TextView attack;
    public AtaqueEnemigo(Enemy enemy, TextView attack){
        this.enemy=enemy;
        this.attack = attack;
    }
    public void atacar(){
        attack.setScaleX(1.0f);
        attack.setScaleY(1.0f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Ataque> ataques = enemy.ataques;
                Random random = new Random();
                int randomNumber = random.nextInt(ataques.size());
                Ataque ataque = ataques.get(randomNumber);
                ataque.iniciarAnimacion(true);
            }
        }, 500);

    }
}
