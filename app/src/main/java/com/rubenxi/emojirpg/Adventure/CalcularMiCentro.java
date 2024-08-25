package com.rubenxi.emojirpg.Adventure;

import android.widget.TextView;

public class CalcularMiCentro {
    int[] location = new int[2];

    public CalcularMiCentro(){
    }
    public float[] calcularMiCentro(TextView pjTextView){
        location = new int[2];
        pjTextView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        
        float width = pjTextView.getWidth();
        float height = pjTextView.getHeight();

        
        float centroX = x + (width / 2.0f);
        float centroY = y + (height / 2.0f);
        return new float[]{centroX,centroY};
    }

}
