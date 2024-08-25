package com.rubenxi.emojirpg.Adventure;

import android.view.View;
import android.widget.TextView;

import com.rubenxi.emojirpg.Emoji.AnimacionSorpresa;

public class ComprobarColision {

    TextView surpriseTextView;
    String icon;
    AnimacionSorpresa animacionSorpresa = new AnimacionSorpresa();

    public ComprobarColision(){

    }

    public void comprobarColision(TextView prop, float centroX, float centroY, float propX, float propY, TextView surpriseTextView, String icon){
        if (centroX>=propX-prop.getWidth()/2 && centroX<=propX+prop.getWidth()/2 && centroY>=propY-prop.getHeight()/2 && centroY<=propY+prop.getHeight()/2){
            surpriseTextView.setText(icon);
            if (surpriseTextView.getVisibility()== View.INVISIBLE){
                animacionSorpresa.animar(surpriseTextView);
                surpriseTextView.setVisibility(View.VISIBLE);
            }
        }
        else{
            surpriseTextView.setText("");
            surpriseTextView.setVisibility(View.INVISIBLE);
        }
    }
    public boolean comprobarColisionEnemy(TextView prop, float centroX, float centroY, float propX, float propY) {
        if (centroX >= propX - prop.getWidth() / 2 && centroX <= propX + prop.getWidth() / 2 && centroY >= propY - prop.getHeight() / 2 && centroY <= propY + prop.getHeight() / 2) {
            return true;
        } else {
            return false;
        }
    }
}
