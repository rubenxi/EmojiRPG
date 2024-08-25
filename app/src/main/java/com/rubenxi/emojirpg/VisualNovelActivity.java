package com.rubenxi.emojirpg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

import java.util.List;

public class VisualNovelActivity extends AppCompatActivity {
    public TextView caraTextView, conversacionTextView;
    public int conversacionActual = 0;
    private Handler handler = new Handler(Looper.getMainLooper());
    public Conversaciones conversaciones;
    public Context context = this;
    private int index = 0;
    private static final long velocidadTexto = 20; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_novel);
        caraTextView = findViewById(R.id.caraTextView);
        conversaciones = new Conversaciones(caraTextView);
        conversacionTextView = findViewById(R.id.conversacionTextView);
        conversacionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conversacionActual++;
                if (handler!=null){
                    handler.removeCallbacksAndMessages(null);
                }
                handler = new Handler(Looper.getMainLooper());
                conversacionTextView.setText("");
                index = 0;
                siguienteConversation();
            }
        });
        animacionEntrar();


    }

    private void animateText(String texto) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                
                if (index < texto.length()) {
                    
                    String partialText = texto.substring(0, index + 1);
                    conversacionTextView.setText(partialText);
                    index++;
                    
                    animateText(texto);
                }
            }
        }, velocidadTexto);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MapaActivity.class));
    }
    public void animacionEntrar(){
        Animation alphaAnimation;
        alphaAnimation = new AlphaAnimation(0.0F,1.0F);
        alphaAnimation.setDuration(1000); 
        alphaAnimation.setInterpolator(new LinearInterpolator()); 
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setFillAfter(true);
        caraTextView.startAnimation(alphaAnimation);
        caraTextView.setVisibility(View.VISIBLE);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                conversacionTextView.setAlpha(1.0F);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animateText(conversaciones.getConversacion1());

                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void siguienteConversation(){
        switch (conversacionActual){
            case 1:
                animateText(conversaciones.getConversacion2());
                break;
            case 2:
                animateText(conversaciones.getConversacion3());
                break;
            case 3 :
                animateText(conversaciones.getConversacion4());
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                List<String> emojis = dbHelper.getTodos();
                for (String emoji : emojis){
                    dbHelper.setVida(emoji,dbHelper.getVidaTotal(emoji));
                }
                break;
            case 4:
                handler.removeCallbacksAndMessages(null);
                animacionSalir();
                break;
            default:
                break;

        }


    }
    public void animacionSalir(){
        Animation alphaAnimation;
        alphaAnimation = new AlphaAnimation(1.0F,0.0F);
        alphaAnimation.setDuration(500); 
        alphaAnimation.setInterpolator(new LinearInterpolator()); 
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setFillAfter(true);
        caraTextView.startAnimation(alphaAnimation);
        caraTextView.setVisibility(View.VISIBLE);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(context, MapaActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}