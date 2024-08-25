package com.rubenxi.emojirpg.Adventure;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DefinirMovimiento {
    TextView arribaButton, abajoButton, izquierdaButton, derechaButton;
    Boolean moviendo;
    String direccion;
    private Handler handler;
    Runnable runnable;
    Button attack1;

    public DefinirMovimiento(TextView arribaButton, TextView abajoButton, TextView izquierdaButton, TextView derechaButton, Handler handler){
        this.arribaButton=arribaButton;
        this.abajoButton=abajoButton;
        this.izquierdaButton=izquierdaButton;
        this.derechaButton=derechaButton;
        this.moviendo=false;
        this.handler=handler;
    }

    public void definirMovimiento(){
        arribaButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        arribaButton.setScaleX(1.1f);
                        arribaButton.setScaleY(1.1f);
                        moviendo = true;
                        direccion="arriba";
                        handler.post(runnable); 
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        arribaButton.setScaleX(1.0f);
                        arribaButton.setScaleY(1.0f);
                        moviendo = false;
                        return true;
                }
                return false;
            }
        });

        abajoButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        abajoButton.setScaleX(1.1f);
                        abajoButton.setScaleY(1.1f);
                        moviendo = true;
                        direccion="abajo";
                        handler.post(runnable); 
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        abajoButton.setScaleX(1.0f);
                        abajoButton.setScaleY(1.0f);
                        moviendo = false;
                        return true;
                }
                return false;
            }
        });

        izquierdaButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        izquierdaButton.setScaleX(1.1f);
                        izquierdaButton.setScaleY(1.1f);
                        moviendo = true;
                        direccion="izquierda";
                        handler.post(runnable); 
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        izquierdaButton.setScaleX(1.0f);
                        izquierdaButton.setScaleY(1.0f);
                        moviendo = false;
                        return true;
                }
                return false;
            }
        });

        derechaButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        derechaButton.setScaleX(1.1f);
                        derechaButton.setScaleY(1.1f);
                        moviendo = true;
                        direccion="derecha";
                        handler.post(runnable); 
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        derechaButton.setScaleX(1.0f);
                        derechaButton.setScaleY(1.0f);
                        moviendo = false;
                        return true;
                }
                return false;
            }
        });


    }

    public Boolean getMoviendo(){
        return moviendo;
    }
    public void setRunnable(Runnable runnable){
        this.runnable=runnable;
    }
    public String getDireccion(){
        return this.direccion;
    }
}
