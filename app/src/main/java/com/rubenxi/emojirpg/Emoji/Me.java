package com.rubenxi.emojirpg.Emoji;

import android.widget.TextView;

public class Me {

    public int vidaBase, vida;
    public TextView meTextView, hpMe, estadoMeTextView;
    public String meEmoji;
    public Me(int vida, int vidaBase, TextView meTextView, String meEmoji, TextView hpMe, TextView estadoMeTextView){
        this.vida = vida;
        this.vidaBase = vidaBase;
        this.meTextView = meTextView;
        this.meEmoji=meEmoji;
        this.hpMe = hpMe;
        this.estadoMeTextView=estadoMeTextView;
    }

}
