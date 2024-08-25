package com.rubenxi.emojirpg.Adventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.rubenxi.emojirpg.GachaActivity;
import com.rubenxi.emojirpg.Emoji.AnimacionStandardMe;
import com.rubenxi.emojirpg.R;

public class AdventureMapActivity extends AppCompatActivity {
    public TextView cielo1,cielo2,cielo3,cielo4,cielo5,nube,astro, pjTextView;
    public Button arribaButton,abajoButton,izquierdaButton,derechaButton;
    CalcularMiCentro calcularMiCentro = new CalcularMiCentro();
    public String pjEmoji;

    public Context context = this;
    private Runnable runnable, runnableEnemy;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    public float centroX, centroY;
    ComprobarBordes comprobarBordes = new ComprobarBordes();
    private Handler handler = new Handler();
    private Handler handlerEnemy = new Handler();
    Spawn spawn = new Spawn();
    public int pjWidth,pjHeight;
    MoverEnemy moverEnemy;
    ConstraintLayout parentConstraintLayout;

    public int screenHeight, screenwidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_map);

        pjTextView = findViewById(R.id.pjTextView);

        arribaButton = findViewById(R.id.arribaButton);
        abajoButton = findViewById(R.id.abajoButton);
        izquierdaButton = findViewById(R.id.izquierdaButton);
        derechaButton = findViewById(R.id.derechaButton);
        cielo1 = findViewById(R.id.cielo1);
        cielo2 = findViewById(R.id.cielo2);
        cielo3 = findViewById(R.id.cielo3);
        cielo4 = findViewById(R.id.cielo4);
        cielo5 = findViewById(R.id.cielo5);
        astro = findViewById(R.id.astro);
        nube = findViewById(R.id.nube);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        pjEmoji = sharedPref.getString(getString(R.string.emojiActual),"\uD83D\uDC69\uD83C\uDFFB\u200D\uD83E\uDDB3");
        pjTextView.setText(pjEmoji);
        AnimacionStandardMe animacionStandardMe = new AnimacionStandardMe(pjTextView);
        animacionStandardMe.animar();
        pjTextView.post(new Runnable() {
            @Override
            public void run() {
                
                pjWidth = pjTextView.getWidth();
                pjHeight = pjTextView.getHeight();
                
                spawn.spawn(context,pjTextView,screenHeight,screenwidth,pjWidth,pjHeight);
            }
        });
        SpawnEnemy spawnEnemy = new SpawnEnemy();
        parentConstraintLayout = findViewById(R.id.parentConstraintLayout);
        spawnEnemy.spawnEnemy("\uD83D\uDC80",0,-screenHeight/2+cielo1.getHeight(),context, pjTextView, parentConstraintLayout, 60,pjWidth,pjHeight);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenwidth = displayMetrics.widthPixels;
        DefinirMovimiento definirMovimiento = new DefinirMovimiento(arribaButton,abajoButton,izquierdaButton,derechaButton, handler);
        definirMovimiento.definirMovimiento();
        new ComprobarHora(cielo1,cielo2,cielo3,cielo4,cielo5,nube,astro).comprobarHora();
        MoverPj moverPj = new MoverPj(pjTextView, definirMovimiento, cielo1,screenwidth,screenHeight, handler);



        runnable = new Runnable() {
            @Override
            public void run() {
                if (definirMovimiento.getMoviendo()) {
                    centroX=calcularMiCentro.calcularMiCentro(pjTextView)[0];
                    centroY=calcularMiCentro.calcularMiCentro(pjTextView)[1];

                    switch (comprobarBordes.comprobarBordes(pjTextView, screenwidth, screenHeight, context)){
                        case "arriba":

                            break;
                        case "abajo":

                            break;
                        case "izquierda":

                            break;
                        case "derecha":
                        irMapa();
                            break;
                        default:
                            break;

                    }
                    moverPj.moverPj();
                }
                else{
                    destroyHandlers();
                }

            }
        };

        moverEnemy = new MoverEnemy(spawnEnemy.getEnemyTextView(), pjTextView, cielo1,screenwidth,screenHeight, handlerEnemy);

        runnableEnemy = new Runnable() {
            @Override
            public void run() {
                if (centroX!=0.0 && centroY!=0.0) {
                    moverEnemy.moverEnemy(centroX, centroY, spawnEnemy.getX(), spawnEnemy.getY());
                }
                handlerEnemy.postDelayed(this, 1);
            }
        };
        handlerEnemy.post(runnableEnemy);

        moverPj.setRunnable(runnable);
        
        definirMovimiento.setRunnable(runnable);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        destroyHandlers();
    }
    public void destroyHandlers(){
        if (handler!=null) {
            handler.removeCallbacksAndMessages(runnable);
        }
    handler=null;
    }



    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, GachaActivity.class));
    }

    public void irMapa(){
        Intent intent = new Intent(this, MapaActivity.class);
        startActivity(intent);

    }
}