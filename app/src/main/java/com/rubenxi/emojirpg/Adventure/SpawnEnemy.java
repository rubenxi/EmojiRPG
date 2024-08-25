package com.rubenxi.emojirpg.Adventure;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SpawnEnemy {
    TextView enemy, pj;
    Context context;

    public SpawnEnemy(){

    }
    public void spawnEnemy(String icon, float positionX, float positionY, Context context, TextView pj, ConstraintLayout layout, int size, int sizeX, int sizeY){

        this.context = context;
        this.pj = pj;
        enemy = new TextView(context);

        
        enemy.setTextSize(size);
        enemy.setTextColor(context.getResources().getColor(android.R.color.white));

        enemy.setText(icon);

                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                Point size2 = new Point();
                display.getSize(size2);
                int screenWidth = size2.x;
                int screenHeight = size2.y;

                int centerX = (screenWidth - sizeX) / 2;
                int centerY = (screenHeight - sizeY) / 2;

                
                enemy.setX(centerX+positionX);
                enemy.setY(centerY+positionY);

        layout.addView(enemy);
    }

    public TextView getEnemyTextView(){
        return enemy;
    }
    public float getX(){
        return enemy.getX();
    }
    public float getY(){
        return enemy.getY();
    }
}
