package com.rubenxi.emojirpg.Adventure;

import android.os.Handler;
import android.widget.TextView;

public class MoverPj {
    TextView pjTextView,enemyTextView;
    private int movPersonaje = 0;
    DefinirMovimiento definirMovimiento;
    TextView cielo1;
    int screenwidth,screenHeight;
    private float velocidad = 10;
    private Handler handler;
    Runnable runnable;


    public MoverPj(TextView pjTextView, DefinirMovimiento definirMovimiento, TextView cielo1, int screenwidth, int screenHeight, Handler handler){
        this.pjTextView=pjTextView;
        this.definirMovimiento=definirMovimiento;
        this.cielo1 = cielo1;
        this.screenHeight=screenHeight;
        this.screenwidth=screenwidth;
        this.handler=handler;
    }

    public void moverPj(){
        if (pjTextView.getRotation()==10 && movPersonaje==5){
            pjTextView.setRotation(-10);
            movPersonaje=0;
        }
        else if (movPersonaje==5) {
            pjTextView.setRotation(10);
            movPersonaje=0;
        }
        movPersonaje++;
        switch (definirMovimiento.getDireccion()){
            case "arriba":
                if (pjTextView.getY()>=cielo1.getHeight()) {
                    pjTextView.setY(pjTextView.getY() - velocidad);
                }
                break;
            case "abajo":
                if (pjTextView.getY()<=screenHeight-pjTextView.getHeight()) {
                    pjTextView.setY(pjTextView.getY() + velocidad);
                }
                break;
            case "izquierda":
                if (pjTextView.getX()>=0) {
                    pjTextView.setX(pjTextView.getX() - velocidad);
                }
                break;
            case "derecha":
                if (pjTextView.getX()<=screenwidth-pjTextView.getWidth()) {
                    
                    pjTextView.setX(pjTextView.getX() + velocidad); 
                }
                break;
            default:
                break;

        }

        handler.postDelayed(runnable, 1); 
    }

    public void setRunnable(Runnable runnable){
        this.runnable = runnable;
    }
}
