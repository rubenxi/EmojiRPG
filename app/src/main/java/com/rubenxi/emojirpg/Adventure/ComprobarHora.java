package com.rubenxi.emojirpg.Adventure;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class ComprobarHora {
    public TextView cielo1,cielo2,cielo3,cielo4,cielo5,nube,astro;

            public ComprobarHora(TextView cielo1,TextView cielo2,TextView cielo3,TextView cielo4,TextView cielo5,TextView nube,TextView astro){
                this.cielo1=cielo1;
                this.cielo2=cielo2;
                this.cielo3=cielo3;
                this.cielo4=cielo4;
                this.cielo5=cielo5;
                this.astro=astro;
                this.nube=nube;

            };

    public void comprobarHora(){
            Calendar now = Calendar.getInstance();
            int currentHour = now.get(Calendar.HOUR_OF_DAY);

            
            int nightStartHour = 20; 
            int nightEndHour = 6;    

            
            if (currentHour >= nightStartHour || currentHour < nightEndHour) {
                System.out.println("Es de noche");
                nube.setVisibility(View.INVISIBLE);
                nube.setText("");

                new AnimacionEstrella(nube).animacionEstrellas();
            } else {
                System.out.println("No es de noche");
                cielo1.setText("");
                cielo1.setBackgroundColor(Color.parseColor("#87CEEB"));
                cielo2.setText("");
                cielo2.setBackgroundColor(Color.parseColor("#87CEEB"));
                cielo3.setText("");
                cielo3.setBackgroundColor(Color.parseColor("#87CEEB"));
                cielo4.setText("");
                cielo4.setBackgroundColor(Color.parseColor("#87CEEB"));
                cielo5.setText("");
                cielo5.setBackgroundColor(Color.parseColor("#87CEEB"));
                astro.setText("⛅");
                new AnimacionNube(nube).animacionNube();
            }
        }


}
