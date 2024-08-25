package com.rubenxi.emojirpg.Adventure;


import android.os.Handler;
import android.widget.TextView;

public class MoverEnemy {
    TextView enemyTextView, pj;
    ComprobarColision comprobarColision = new ComprobarColision();
    private int movEnemy = 0;
    float centroEnemyX, centroEnemyY;
    DefinirMovimiento definirMovimiento;
    TextView cielo1;
    int screenwidth,screenHeight;
    private float velocidad = 2;
    CalcularMiCentro calcularMiCentro = new CalcularMiCentro();
    private Handler handler;
    Runnable runnable;


    public MoverEnemy(TextView enemy, TextView pj, TextView cielo1, int screenwidth, int screenHeight, Handler handler){
        this.cielo1 = cielo1;
        this.screenHeight=screenHeight;
        this.screenwidth=screenwidth;
        this.handler=handler;
        this.pj=pj;
        this.enemyTextView=enemy;
    }
    public void setEnemy(TextView enemy){
        this.enemyTextView=enemyTextView;
    }
    public void moverEnemy(float centroX, float centroY, float enemyX, float enemyY){

        centroEnemyX = calcularMiCentro.calcularMiCentro(enemyTextView)[0];
        centroEnemyY = calcularMiCentro.calcularMiCentro(enemyTextView)[1];

        if (centroEnemyY>=centroY+pj.getHeight()/2) {
            enemyTextView.setY(enemyTextView.getY() - velocidad); 
        }
        else if (centroEnemyY<=centroY-pj.getHeight()/2) {
            enemyTextView.setY(enemyTextView.getY() + velocidad); 
        }

        if (centroEnemyX>=centroX+pj.getWidth()/2) {
            enemyTextView.setX(enemyTextView.getX() - velocidad); 
        }
        else if (centroEnemyX<=centroX-pj.getWidth()/2) {
            enemyTextView.setX(enemyTextView.getX() + velocidad); 
        }

        if (comprobarColision.comprobarColisionEnemy(enemyTextView,centroX,centroY,centroEnemyX,centroEnemyY)){
            enemyTextView.setText("☠️");
        }
        else{
            movEnemy++;
            enemyTextView.setText("\uD83D\uDC80");
            if (enemyTextView.getRotation()==1 && movEnemy==5){
                enemyTextView.setRotation(-1);
                movEnemy=0;
            }
            else if (movEnemy==5) {
                enemyTextView.setRotation(1);
                movEnemy=0;
            }
        }

      
    }
    public void setPj(TextView pj){
        this.pj=pj;

    }
    public void setRunnable(Runnable runnable){
        this.runnable = runnable;
    }
}
