package com.rubenxi.emojirpg.Adventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.ChangeEmojiActivity;
import com.rubenxi.emojirpg.CollectionActivity;
import com.rubenxi.emojirpg.GachaActivity;

import com.rubenxi.emojirpg.Emoji.AnimacionStandardMe;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;
import com.rubenxi.emojirpg.EmojiCombatActivity;
import com.rubenxi.emojirpg.R;
import com.rubenxi.emojirpg.VisualNovelActivity;

public class MapaActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    CalcularMiCentro calcularMiCentro = new CalcularMiCentro();

    public Context context = this;
    private Runnable runnable;
    public TextView pjTextView, treeTextView, treeSurpriseTextView,casaSurpriseTextView, casaTextView, heartButton, heartSurpriseTextView, changeEmoji, surpriseChangeEmoji;
    public TextView cielo1,cielo2,cielo3,cielo4,cielo5,nube,astro;
    public int screenHeight, screenwidth;
    public Button arribaButton,abajoButton,izquierdaButton,derechaButton, ataque1;
    ComprobarBordes comprobarBordes = new ComprobarBordes();
    public String pjEmoji;
    ComprobarColision comprobarColision = new ComprobarColision();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    SharedPreferences sharedPref;
    public int pjHeight, pjWidth;
    Spawn spawn = new Spawn();

    public float treeX, treeY, centroX, centroY, casaX, casaY, heartX, heartY, changeEmojiX, changeEmojiY;
    private float currentTranslationY = 0f; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        pjTextView = findViewById(R.id.pjTextView);
        ataque1 = findViewById(R.id.attack);
        arribaButton = findViewById(R.id.arribaButton);
        abajoButton = findViewById(R.id.abajoButton);
        izquierdaButton = findViewById(R.id.izquierdaButton);
        derechaButton = findViewById(R.id.derechaButton);
        casaTextView = findViewById(R.id.casaTextView);
        heartButton = findViewById(R.id.heartButton);
        changeEmoji = findViewById(R.id.changeEmoji);
        surpriseChangeEmoji = findViewById(R.id.changeEmojiSurprise);
        cielo1 = findViewById(R.id.cielo1);
        cielo2 = findViewById(R.id.cielo2);
        cielo3 = findViewById(R.id.cielo3);
        cielo4 = findViewById(R.id.cielo4);
        cielo5 = findViewById(R.id.cielo5);
        astro = findViewById(R.id.astro);
        nube = findViewById(R.id.nube);
        heartSurpriseTextView = findViewById(R.id.heartSurpriseTextView);
        casaSurpriseTextView = findViewById(R.id.casaSurpriseTextView);
        treeTextView = findViewById(R.id.treeTextView);
        treeSurpriseTextView = findViewById(R.id.treeSurpriseTextView);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        pjEmoji = sharedPref.getString(getString(R.string.emojiActual),"\uD83D\uDC69\uD83C\uDFFB\u200D\uD83E\uDDB3");
        pjTextView.setText(pjEmoji);
        pjTextView.post(new Runnable() {
            @Override
            public void run() {
                
                pjWidth = pjTextView.getWidth();
                pjHeight = pjTextView.getHeight();
                
                spawn.spawn(context,pjTextView,screenHeight,screenwidth, pjWidth,pjHeight);

            }
        });
        AnimacionStandardMe animacionStandardMe = new AnimacionStandardMe(pjTextView);
        animacionStandardMe.animar();

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

                    calcularCentros();
                    comprobarColision.comprobarColision(treeTextView,centroX,centroY,treeX,treeY,treeSurpriseTextView,"⚔️");
                    comprobarColision.comprobarColision(casaTextView,centroX,centroY,casaX,casaY,casaSurpriseTextView,"\uD83D\uDDE8️");
                    comprobarColision.comprobarColision(heartButton,centroX,centroY,heartX,heartY,heartSurpriseTextView,"\uD83C\uDF20️");
                    comprobarColision.comprobarColision(changeEmoji,centroX,centroY,changeEmojiX,changeEmojiY,surpriseChangeEmoji,"\uD83D\uDD03");
                    switch (comprobarBordes.comprobarBordes(pjTextView, screenwidth, screenHeight, context)){
                        case "arriba":

                            break;
                        case "abajo":

                            break;
                        case "izquierda":
                           irAventura();
                            break;
                        case "derecha":

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
        definirMovimiento.setRunnable(runnable);
        moverPj.setRunnable(runnable);
       

        treeSurpriseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irFight();
            }
        });
        treeSurpriseTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    treeSurpriseTextView.setScaleX(1.1f);
                    treeSurpriseTextView.setScaleY(1.1f);

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    treeSurpriseTextView.setScaleX(1.0f);
                    treeSurpriseTextView.setScaleY(1.0f);

                }

                return false;
            }

        });

        casaSurpriseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irVisualNovel();
            }
        });
        casaSurpriseTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    casaSurpriseTextView.setScaleX(1.1f);
                    casaSurpriseTextView.setScaleY(1.1f);

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    casaSurpriseTextView.setScaleX(1.0f);
                    casaSurpriseTextView.setScaleY(1.0f);

                }

                return false;
            }

        });


        heartSurpriseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irGacha();
            }
        });
        heartSurpriseTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    heartSurpriseTextView.setScaleX(1.1f);
                    heartSurpriseTextView.setScaleY(1.1f);

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    heartSurpriseTextView.setScaleX(1.0f);
                    heartSurpriseTextView.setScaleY(1.0f);

                }

                return false;
            }

        });

        surpriseChangeEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irChangeEmoji();
            }
        });
        surpriseChangeEmoji.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    surpriseChangeEmoji.setScaleX(1.1f);
                    surpriseChangeEmoji.setScaleY(1.1f);

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    surpriseChangeEmoji.setScaleX(1.0f);
                    surpriseChangeEmoji.setScaleY(1.0f);

                }

                return false;
            }

        });
    }



    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, GachaActivity.class));
    }



    public void irCollection(){
        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);

    }
    public void irGacha(){
        Intent intent = new Intent(this, GachaActivity.class);
        startActivity(intent);

    }
    public void irVisualNovel(){
        Intent intent = new Intent(this, VisualNovelActivity.class);
        startActivity(intent);

    }

    public void irAventura(){
        Intent intent = new Intent(this, AdventureMapActivity.class);
        startActivity(intent);

    }
    public void irFight(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (Integer.parseInt(databaseHelper.getVida(pjEmoji))<=0){
            Toast.makeText(MapaActivity.this, "Your "+pjEmoji+" is dead, change it!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, EmojiCombatActivity.class);
            startActivity(intent);
        }
    }
    public void irChangeEmoji(){
        Intent intent = new Intent(this, ChangeEmojiActivity.class);
        startActivity(intent);
    }


    public void calcularCentros(){
        treeX=calcularMiCentro.calcularMiCentro(treeTextView)[0];
        treeY= calcularMiCentro.calcularMiCentro(treeTextView)[1];
///////////////////////////////////////////////////////////////
        casaX= calcularMiCentro.calcularMiCentro(casaTextView)[0];
        casaY= calcularMiCentro.calcularMiCentro(casaTextView)[1];
///////////////////////////////////////////////////////////////
        heartX= calcularMiCentro.calcularMiCentro(heartButton)[0];
        heartY= calcularMiCentro.calcularMiCentro(heartButton)[1];
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
        changeEmojiX= calcularMiCentro.calcularMiCentro(changeEmoji)[0];
        changeEmojiY= calcularMiCentro.calcularMiCentro(changeEmoji)[1];
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        destroyHandlers();
    }
    public void destroyHandlers(){
        if (handler != null) {
            handler.removeCallbacksAndMessages(runnable);
        }
        handler=null;
    }
}