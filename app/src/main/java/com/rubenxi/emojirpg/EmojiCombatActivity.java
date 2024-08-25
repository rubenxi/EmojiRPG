package com.rubenxi.emojirpg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.AnimacionCaptura;
import com.rubenxi.emojirpg.Emoji.AnimacionStandardEnemy;
import com.rubenxi.emojirpg.Emoji.AnimacionStandardMe;
import com.rubenxi.emojirpg.Emoji.Ataque;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.DarkBall;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.Fire;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.Heal;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.Surf;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.Transform;
import com.rubenxi.emojirpg.Emoji.BajarVidaEnemy;
import com.rubenxi.emojirpg.Emoji.BajarVidaMe;
import com.rubenxi.emojirpg.Emoji.Enemy;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;
import com.rubenxi.emojirpg.Emoji.Me;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class EmojiCombatActivity extends AppCompatActivity {
    public Ataque surf, fire, darkBall, transform, heal;
    public ArrayList<Ataque> allAttacks;
    public ArrayList<Ataque> ataquesEnemigo;
    private Button runButton, changeButton, catchButton, attackButton;
    private Button attackButton1, attackButton2, attackButton3, attackButton4;
    private TextView enemyTextView, meTextView, atackTextView, hpenemy, hpme, estadoEnemyTextView,estadoMeTextView, nameTextView;
    private LinearLayout linearLayout,linearLayoutAtaques;
    private String enemy;
    private ArrayList<Ataque> ataques;
    Random random = new Random();

    private String me;
    public Enemy enemyEnemy;
    public Me meMe;
    public Context context;

    private int vidaenemy, vidaBaseEnemy, vidaBaseMe, vidaMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_combat);
        runButton = findViewById(R.id.runButton);
        changeButton = findViewById(R.id.changeButton);
        catchButton = findViewById(R.id.catchButton);
        attackButton = findViewById(R.id.attackButton);
        meTextView = findViewById(R.id.meTextView);
        atackTextView = findViewById(R.id.atackTextView);
        enemyTextView = findViewById(R.id.enemyTextView);
        linearLayout = findViewById(R.id.linearLayout);
        nameTextView = findViewById(R.id.nameTextView);
        estadoMeTextView = findViewById(R.id.estadoMeTextView);
        estadoEnemyTextView = findViewById(R.id.estadoEnemyTextView);
        hpenemy = findViewById(R.id.hpTextView);
        hpme = findViewById(R.id.hpmeTextView);
        hpenemy.setVisibility(View.INVISIBLE);
        hpme.setVisibility(View.INVISIBLE);
        attackButton1 = findViewById(R.id.atacckButton1);
        attackButton2 = findViewById(R.id.atacckButton2);
        attackButton3 = findViewById(R.id.atacckButton3);
        attackButton4 = findViewById(R.id.atacckButton4);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        linearLayoutAtaques = findViewById(R.id.linearLayoutAtaques);
        linearLayoutAtaques.setVisibility(View.INVISIBLE);
        context = this;


        ArrayList<String> allEmojis = new AllEmojis().getAllEmojis();
        int randomNumber = random.nextInt(allEmojis.size());
        enemy = allEmojis.get(randomNumber);

        enemyTextView.setText(enemy);


        meTextView.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        me = sharedPref.getString(getString(R.string.emojiActual), "");

        vidaBaseMe = Integer.parseInt(databaseHelper.getVidaTotal(me));
        vidaBaseEnemy = 5;
        vidaMe =  Integer.parseInt(databaseHelper.getVida(me));;
        vidaenemy = vidaBaseEnemy;
        enemyEnemy = new Enemy(vidaenemy,vidaBaseEnemy,enemyTextView,enemy,hpenemy, estadoEnemyTextView);
        meMe = new Me(vidaMe,vidaBaseMe,meTextView,me,hpme,estadoMeTextView);
        nameTextView.setText(databaseHelper.getNombre(meMe.meEmoji));

        BajarVidaEnemy bajarVidaEnemy = new BajarVidaEnemy(enemyEnemy,context,0,meMe);
        bajarVidaEnemy.bajarVida();
        BajarVidaMe bajarVidaMe = new BajarVidaMe(meMe,context,0);
        bajarVidaMe.bajarVida();
        crearAtaques();
        setAtaquesEnemigo();
        enemyEnemy.setAtaques(ataquesEnemigo);
        setAtaquesMe();


        attackButton1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    attackButton1.setScaleX(1.1f);
                    attackButton1.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    attackButton1.setScaleX(1.0f);
                    attackButton1.setScaleY(1.0f);

                }

                return false;
            }

        });

        attackButton2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    attackButton2.setScaleX(1.1f);
                    attackButton2.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    attackButton2.setScaleX(1.0f);
                    attackButton2.setScaleY(1.0f);

                }

                return false;
            }

        });

        attackButton3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    attackButton3.setScaleX(1.1f);
                    attackButton3.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    attackButton3.setScaleX(1.0f);
                    attackButton3.setScaleY(1.0f);

                }

                return false;
            }

        });

        attackButton4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    attackButton4.setScaleX(1.1f);
                    attackButton4.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    attackButton4.setScaleX(1.0f);
                    attackButton4.setScaleY(1.0f);

                }

                return false;
            }

        });


        runButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    runButton.setScaleX(1.1f);
                    runButton.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    runButton.setScaleX(1.0f);
                    runButton.setScaleY(1.0f);

                }

                return false;
            }

        });
        catchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    catchButton.setScaleX(1.1f);
                    catchButton.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    catchButton.setScaleX(1.0f);
                    catchButton.setScaleY(1.0f);

                }

                return false;
            }

        });
        attackButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    attackButton.setScaleX(1.1f);
                    attackButton.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    attackButton.setScaleX(1.0f);
                    attackButton.setScaleY(1.0f);

                }

                return false;
            }

        });
        changeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    changeButton.setScaleX(1.1f);
                    changeButton.setScaleY(1.1f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    changeButton.setScaleX(1.0f);
                    changeButton.setScaleY(1.0f);

                }

                return false;
            }

        });

        attackButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ataque ataque = getAtaqueFromString(attackButton1.getText().toString());
                if (ataque!=null){
                    linearLayoutAtaques.setVisibility(View.INVISIBLE);
                    ataque.iniciarAnimacion(false);
                }
            }

        });

        attackButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ataque ataque = getAtaqueFromString(attackButton2.getText().toString());
                if (ataque!=null) {
                    linearLayoutAtaques.setVisibility(View.INVISIBLE);
                    ataque.iniciarAnimacion(false);
                }
            }

        });

        attackButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ataque ataque = getAtaqueFromString(attackButton3.getText().toString());
                if (ataque!=null) {
                    linearLayoutAtaques.setVisibility(View.INVISIBLE);
                    ataque.iniciarAnimacion(false);
                }
            }

        });

        attackButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ataque ataque = getAtaqueFromString(attackButton4.getText().toString());
                if (ataque!=null) {
                    linearLayoutAtaques.setVisibility(View.INVISIBLE);
                    ataque.iniciarAnimacion(false);
                }
            }

        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irGacha();
            }

        });
        catchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturar();
            }

        });
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atackTextView.setScaleX(1.0F);
                atackTextView.setScaleY(1.0F);
                linearLayout.setVisibility(View.INVISIBLE);
               linearLayoutAtaques.setVisibility(View.VISIBLE);

            }

        });

        animacionEntrar();
    }

    public void irGacha() {

        Intent intent = new Intent(this, MapaActivity.class);
        startActivity(intent);
    }


    public void animacionEntrar() {
        int velocidad = 5000;
        enemyTextView.setAlpha(0.1F);
        Animation animation;
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);


        animation.setDuration(velocidad); 
        animation.setInterpolator(new LinearInterpolator()); 
        animation.setRepeatCount(0);
        animation.setFillAfter(true);
        enemyTextView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                enemyTextView.setAlpha(1.0F);
                animacionEstandarEnemy();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animacionEnviar();
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void animacionEnviar() {
        int velocidad = 50;
        meTextView.setText(me);
        meTextView.setVisibility(View.VISIBLE);
        meTextView.setAlpha(0.1F);
        Animation animation;
        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.05f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);


        animation.setDuration(velocidad); 
        animation.setInterpolator(new LinearInterpolator()); 
        animation.setRepeatCount(0);
        animation.setFillAfter(true);
        meTextView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                meTextView.setAlpha(0.3F);

                Animation animation2;
                animation2 = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.05f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.05f);


                animation2.setDuration(velocidad); 
                animation2.setInterpolator(new LinearInterpolator()); 
                animation2.setRepeatCount(0);
                animation2.setFillAfter(true);
                meTextView.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        meTextView.setAlpha(0.6F);

                        Animation animation3;
                        animation3 = new TranslateAnimation(
                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                Animation.RELATIVE_TO_PARENT, -0.05f,
                                Animation.RELATIVE_TO_PARENT, 0.05f,
                                Animation.RELATIVE_TO_PARENT, 0.0f);


                        animation3.setDuration(velocidad); 
                        animation3.setInterpolator(new LinearInterpolator()); 
                        animation3.setRepeatCount(0);
                        animation3.setFillAfter(true);
                        meTextView.startAnimation(animation3);
                        animation3.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                meTextView.setAlpha(0.8F);

                                Animation animation4;
                                animation4 = new TranslateAnimation(
                                        Animation.RELATIVE_TO_PARENT, -0.05f,
                                        Animation.RELATIVE_TO_PARENT, 0.0f,
                                        Animation.RELATIVE_TO_PARENT, 0.0f,
                                        Animation.RELATIVE_TO_PARENT, -0.05f);


                                animation4.setDuration(velocidad); 
                                animation4.setInterpolator(new LinearInterpolator()); 
                                animation4.setRepeatCount(0);
                                animation4.setFillAfter(true);
                                meTextView.startAnimation(animation4);
                                animation4.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        meTextView.setAlpha(1.0F);

                                        Animation animation5;
                                        animation5 = new TranslateAnimation(
                                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                                Animation.RELATIVE_TO_PARENT, 0.0f,
                                                Animation.RELATIVE_TO_PARENT, -0.05f,
                                                Animation.RELATIVE_TO_PARENT, 0.0f);


                                        animation5.setDuration(velocidad); 
                                        animation5.setInterpolator(new LinearInterpolator()); 
                                        animation5.setRepeatCount(0);
                                        animation5.setFillAfter(true);
                                        meTextView.startAnimation(animation5);
                                        animation5.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {

                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {
                                                linearLayout.setVisibility(View.VISIBLE);
                                                animacionEstandarme();
                                                meTextView.setText(me);
                                                hpenemy.setVisibility(View.VISIBLE);
                                                hpme.setVisibility(View.VISIBLE);
                                                nameTextView.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    public void animacionEstandarEnemy(){
        AnimacionStandardEnemy animacionStandardEnemy = new AnimacionStandardEnemy(enemyTextView);
        animacionStandardEnemy.animar();
    }

    public void animacionEstandarme(){
        AnimacionStandardMe animacionStandardme = new AnimacionStandardMe(meTextView);
        animacionStandardme.animar();
    }

    public void capturar(){
        AnimacionCaptura animacionCaptura = new AnimacionCaptura(atackTextView,linearLayout,enemyEnemy,context);
        animacionCaptura.animar();
}
    @Override
    public void onBackPressed() {
        if (linearLayoutAtaques.getVisibility() == View.VISIBLE) {
            linearLayoutAtaques.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        } else {
           irGacha();
        }
    }

    public void crearAtaques(){
        surf = new Surf(enemyEnemy, atackTextView, context, linearLayout, meMe);
        fire = new Fire(enemyEnemy, atackTextView, context, linearLayout, meMe);
        heal = new Heal(enemyEnemy, atackTextView, context, linearLayout, meMe);
        darkBall = new DarkBall(enemyEnemy, atackTextView, context, linearLayout, meMe);
        transform = new Transform(enemyEnemy, atackTextView, context, linearLayout, meMe);

        allAttacks = new ArrayList<>();
        allAttacks.add(surf);
        allAttacks.add(fire);
        allAttacks.add(heal);
        allAttacks.add(transform);
        allAttacks.add(darkBall);
    }
    public void setAtaquesEnemigo(){
       ataquesEnemigo = new ArrayList<>();
        int randomNumber = random.nextInt(allAttacks.size());
        int randomNumber2 = random.nextInt(allAttacks.size());
        while (randomNumber2==randomNumber){
            randomNumber2 = random.nextInt(allAttacks.size());
        }
        ataquesEnemigo.add(allAttacks.get(randomNumber));
        ataquesEnemigo.add(allAttacks.get(randomNumber2));

    }

    public void setAtaquesMe(){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        List<String> ataquesMe = databaseHelper.getAtaques(meMe.meEmoji);
        ArrayList<Button> botonesAtaques = new ArrayList<>();
        botonesAtaques.add(attackButton1);
        botonesAtaques.add(attackButton2);
        botonesAtaques.add(attackButton3);
        botonesAtaques.add(attackButton4);
        attackButton1.setText("");
        attackButton2.setText("");
        attackButton3.setText("");
        attackButton4.setText("");
        ArrayList<String> ataquesYa = new ArrayList();
        System.out.println(ataquesMe);
        int botonActual = 0;
            for (String ataque : ataquesMe){
                if (ataque.contains(fire.attackEmoji) && !ataquesYa.contains(fire.attackEmoji)){
                botonesAtaques.get(botonActual).setText(fire.attackEmoji);
                ataquesYa.add(fire.attackEmoji);
                }
                else if (ataque.contains(surf.attackEmoji) && !ataquesYa.contains(surf.attackEmoji)){
                    botonesAtaques.get(botonActual).setText(surf.attackEmoji);
                    ataquesYa.add(surf.attackEmoji);
                }
                else if (ataque.contains(darkBall.attackEmoji) && !ataquesYa.contains(darkBall.attackEmoji)){
                    botonesAtaques.get(botonActual).setText(darkBall.attackEmoji);
                    ataquesYa.add(darkBall.attackEmoji);
                }
                else if (ataque.contains(transform.attackEmoji) && !ataquesYa.contains(transform.attackEmoji)){
                    botonesAtaques.get(botonActual).setText(transform.attackEmoji);
                    ataquesYa.add(transform.attackEmoji);
                }
                else if (ataque.contains(heal.attackEmoji) && !ataquesYa.contains(heal.attackEmoji)){
                    botonesAtaques.get(botonActual).setText(heal.attackEmoji);
                    ataquesYa.add(heal.attackEmoji);
                }
                else {
                    botonesAtaques.get(botonActual).setText("");
                }
            botonActual++;

        }


    }

    public Ataque getAtaqueFromString(String ataqueString){
        Ataque ataqueFinal = null;
        for (Ataque ataque : allAttacks){
            if (ataque.attackEmoji.contains(ataqueString) && ataqueString!="" && ataqueString.length()>0){
                ataqueFinal=ataque;
            }
        }
        return ataqueFinal;
    }
}