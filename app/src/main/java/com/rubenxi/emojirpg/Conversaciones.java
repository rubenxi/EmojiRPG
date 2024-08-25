package com.rubenxi.emojirpg;


import android.widget.TextView;

public class Conversaciones {
    public TextView caraTextView;
    public Conversaciones(TextView caraTextView){
    this.caraTextView = caraTextView;
    }
    public String getConversacion1(){
        caraTextView.setText("\uD83D\uDE46\uD83C\uDFFB\u200D♂️");
        String texto = "Hello, what are you doing here?";
        return texto;
    }
    public String getConversacion2(){
        caraTextView.setText("\uD83D\uDE4D\uD83C\uDFFB\u200D♂️");
        String texto = "Why you don't talk?";
        return texto;
    }
    public String getConversacion3(){
        caraTextView.setText("\uD83D\uDE4E\uD83C\uDFFB\u200D♂️");
        String texto = "Ok then, this was only a demo of the visual novel anyways, hum";

        return texto;
    }

    public String getConversacion4(){
        caraTextView.setText("\uD83D\uDE4E\uD83C\uDFFB\u200D♂️");
        String texto = "Ok whatever, I'll heal your emojis... bye";

        return texto;
    }


}
