package com.rubenxi.emojirpg.Adventure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.rubenxi.emojirpg.R;

public class Spawn {
    SharedPreferences sharedPref;

    public Spawn(){
    }

    public void spawn(Context context, TextView pj, int screenHeight, int screenWidth, int pjWidth, int pjHeight){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        switch (sharedPref.getString(context.getString(R.string.spawn),"")){
            case "arriba":
            pj.setY(screenHeight-pjHeight-10);
                break;
            case "abajo":
                pj.setY(10);
                break;
            case "izquierda":
                pj.setX(screenWidth-pjWidth-10);
                break;
            case "derecha":
                pj.setX(10);
                break;
            default:
                break;

        }
    }

}
