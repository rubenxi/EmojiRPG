package com.rubenxi.emojirpg.Adventure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.rubenxi.emojirpg.R;

public class ComprobarBordes {
    String direccion="";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public String comprobarBordes(TextView pjTextView, float screenwidth, float screenHeight, Context context){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        if (pjTextView.getY()<=0) {
            direccion="arriba";
            saveSpawn(context, direccion);
        }else if (pjTextView.getY()>=screenHeight-pjTextView.getHeight()) {
            direccion="abajo";
            saveSpawn(context, direccion);
        }
        else if (pjTextView.getX()<=0) {
            direccion = "izquierda";
            saveSpawn(context, direccion);
        }
       else if (pjTextView.getX()>=screenwidth-pjTextView.getWidth()) {
            direccion = "derecha";
            saveSpawn(context, direccion);
        }

        return direccion;
    }
    public void saveSpawn(Context context, String direccion){
        editor = sharedPref.edit();
        editor.putString(context.getString(R.string.spawn),direccion);
        editor.apply();
    }
}
