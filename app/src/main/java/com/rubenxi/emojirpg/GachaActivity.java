package com.rubenxi.emojirpg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.AtaquesGame.Ataques;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GachaActivity extends AppCompatActivity {
private ArrayList emojiListArray;
        public String emojiActual;
       private Button button, masOpcionesButton;
    private TextView wishAnimationTextView, textView8, alreadyPulled;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gacha);
        button = findViewById(R.id.button);
        textView8 = findViewById(R.id.textView8);
        alreadyPulled = findViewById(R.id.textView14);
        emojiListArray = new ArrayList();
       masOpcionesButton = findViewById(R.id.masOpcionesButton3);
        AllEmojis allEmojis = new AllEmojis();
        context = this;
        emojiListArray = allEmojis.getAllEmojis();
        wishAnimationTextView= findViewById(R.id.wishAnimationTextView);
           masOpcionesButton = (Button) findViewById(R.id.masOpcionesButton3);
           masOpcionesButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                         abrirMenuGeneral();
                  }
           });


            textView8.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction()==MotionEvent.ACTION_DOWN){
                                    textView8.setScaleX(1.1f);
                                    textView8.setScaleY(1.1f);

                            }else if(event.getAction()==MotionEvent.ACTION_UP){
                                    textView8.setScaleX(1.0f);
                                    textView8.setScaleY(1.0f);

                            }

                            return false;
                    }

            });
            textView8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                return;
                    }
            });

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int gachaDoneFecha = sharedPref.getInt(getString(R.string.gachaDone),0);

        SharedPreferences.Editor editor = sharedPref.edit();


            Date currentDate = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
            int formattedDate = Integer.parseInt(sdf.format(currentDate));

            if(formattedDate==gachaDoneFecha){
                alreadyPulled.setVisibility(View.VISIBLE);
                String gachaToday = sharedPref.getString(getString(R.string.gachaToday),"");
                textView8.setText(gachaToday);
            }
            else{
                textView8.setText("");
            }


            button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction()==MotionEvent.ACTION_DOWN){
                                    button.setScaleX(1.1f);
                                    button.setScaleY(1.1f);
                                    button.setText("\uD83C\uDF20 PULL \uD83C\uDF20");

                            }else if(event.getAction()==MotionEvent.ACTION_UP){
                                    button.setScaleX(1.0f);
                                    button.setScaleY(1.0f);
                                    button.setText("✨ PULL ✨");

                            }

                            return false;
                    }

            });
            button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (formattedDate==gachaDoneFecha){
                            animacionNoPull();
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                            String gachaToday = sharedPref.getString(getString(R.string.gachaToday),"");
                                Toast.makeText(GachaActivity.this, "You got "+gachaToday+" today\nTry again tomorrow!!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            DatabaseHelper dbHelper = new DatabaseHelper(context);
                            List<String> emojis = dbHelper.getTodos();
                            for (String emoji : emojis){
                                dbHelper.setVida(emoji,dbHelper.getVidaTotal(emoji));
                            }
                                animacionPull();
                        }
                    }
            });

        emojiActual = sharedPref.getString(getString(R.string.emojiActual),"");
        if (emojiActual.length()==0 || emojiActual==""){
            masOpcionesButton.setVisibility(View.INVISIBLE);
            String gachaToday = sharedPref.getString(getString(R.string.gachaToday),"");
            textView8.setText(gachaToday);
            editor.putString(getString(R.string.emojiActual),gachaToday);
            editor.apply();
            masOpcionesButton.setText(gachaToday);
        }
        else {
            masOpcionesButton.setText(emojiActual);
        }

    }

    public void pull() throws InterruptedException {
           Random random = new Random();
           int randomNumber = random.nextInt(emojiListArray.size());

           String emoji = (String) emojiListArray.get(randomNumber);

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPref.edit();

        Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        int formattedDate = Integer.parseInt(sdf.format(currentDate));
        SharedPreferences.Editor editoruv = sharedPref.edit();
        editoruv.putInt(getString(R.string.gachaDone), formattedDate);
        editoruv.apply();


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<String> emojisDesbloqueados = dbHelper.getTodos();

            while (emojisDesbloqueados.contains(emoji) && emojisDesbloqueados.size()!=emojiListArray.size()){
                    random = new Random();
                    randomNumber = random.nextInt(emojiListArray.size());
                    emoji = (String) emojiListArray.get(randomNumber);
            }

        editor.putString(getString(R.string.gachaToday),emoji);
        editor.apply();

        String emojiActual = sharedPref.getString(getString(R.string.emojiActual),"");

        if (emojiActual.length()==0 || emojiActual==""){
            String gachaToday = sharedPref.getString(getString(R.string.gachaToday),"");
            textView8.setText(gachaToday);
            editor.putString(getString(R.string.emojiActual),gachaToday);
            editor.apply();
            masOpcionesButton.setText(gachaToday);

        }
        if (emojisDesbloqueados.size()!=emojiListArray.size()) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.insertarNuevo(emoji);
            Date currentDate2 = Calendar.getInstance().getTime();
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate2 = sdf2.format(currentDate2);

            databaseHelper.setFechaCaptura(emoji,formattedDate2);
            ArrayList<String> ataques = new Ataques().getAllAtaques();
            Random random2 = new Random();
            int randomNumber2 = random2.nextInt(ataques.size());
            ArrayList<String> ataquesGuardar = new ArrayList<>();
            ataquesGuardar.add(ataques.get(randomNumber2));
            int randomNumber3 = random2.nextInt(ataques.size());
            while (randomNumber2==randomNumber3){
                randomNumber3 = random2.nextInt(ataques.size());
            }
            ataquesGuardar.add(ataques.get(randomNumber3));
            databaseHelper.setAtaquesString(emoji,ataquesGuardar);
            databaseHelper.setNivel(emoji,"0");
            databaseHelper.setVida(emoji,"5");
            databaseHelper.setVidaTotal(emoji,"5");
        }


            textView8.setText(emoji);
            Toast.makeText(GachaActivity.this, "You got "+textView8.getText(), Toast.LENGTH_SHORT).show();

            button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                        String gachaToday = sharedPref.getString(getString(R.string.gachaToday),"");
                        Toast.makeText(GachaActivity.this, "You got "+gachaToday+" today\nTry again tomorrow!!", Toast.LENGTH_SHORT).show();
                    
                    }
            });
            masOpcionesButton.setVisibility(View.VISIBLE);
    }
       public void abrirMenuGeneral(){
              PopupMenu popupMenu = new PopupMenu(GachaActivity.this, masOpcionesButton);

              
              popupMenu.getMenuInflater().inflate(R.menu.floating_gacha_options, popupMenu.getMenu());
              popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                     @Override
                     public boolean onMenuItemClick(MenuItem menuItem) {
                            
                            if(menuItem.getItemId() == R.id.item1) {
                                   irCollection();
                            }
                             if(menuItem.getItemId() == R.id.item2) {
                                     irFight();
                             }
                         if(menuItem.getItemId() == R.id.item3) {
                             irChangeEmoji();
                         }
                         if(menuItem.getItemId() == R.id.item4) {
                             irVisualNovel();
                         }
                         if(menuItem.getItemId() == R.id.item5) {
                             irMap();
                         }
                         return true;
                     }
              });
              popupMenu.show();
       }

       public void irCollection(){
               Intent intent = new Intent(this, CollectionActivity.class);
               startActivity(intent);

       }
    public void irVisualNovel(){
        Intent intent = new Intent(this, VisualNovelActivity.class);
        startActivity(intent);

    }
    public void irFight(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (Integer.parseInt(databaseHelper.getVida(emojiActual))<=0){
            Toast.makeText(GachaActivity.this, "Your "+emojiActual+" is dead, change it!", Toast.LENGTH_SHORT).show();
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
    public void irMap(){
        Intent intent = new Intent(this, MapaActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MapaActivity.class);
        SharedPreferences sharedPrefOld = this.getPreferences(Context.MODE_PRIVATE);
        String emojiActual = sharedPrefOld.getString(getString(R.string.emojiActual), "");
        if (emojiActual == "" || emojiActual.length() == 0) {
            intent = new Intent(this, GachaActivity.class);

        } else {
            intent = new Intent(this, MapaActivity.class);

        }
        startActivity(intent);
        finish();
    }

    public void animacionNoPull(){
        Random random = new Random();
        int size = 10 + random.nextInt(30); 
        int speed = 800 + random.nextInt(1000); 
        int speedRota = 100 + random.nextInt(300); 

        wishAnimationTextView.setTextSize(size); 



        float randomY1 = -0.2F + (-0.5F - (-0.2F)) * random.nextFloat();
        float randomY2 = 0.1F + (0.6F - (0.1F)) * random.nextFloat();
        int randomNumber2 = random.nextInt(emojiListArray.size());

        wishAnimationTextView.setVisibility(View.VISIBLE);
        Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, randomY1,
                Animation.RELATIVE_TO_PARENT, randomY2);


        animationMove.setDuration(speed); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(true);




        Animation animationRotation;
        animationRotation = new RotateAnimation(0F,360F,Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);

        animationRotation.setDuration(speedRota); 
        animationRotation.setInterpolator(new LinearInterpolator()); 

        animationRotation.setRepeatCount(Animation.INFINITE);
        animationRotation.setRepeatMode(Animation.RESTART); 
        animationRotation.setFillAfter(true);

        AnimationSet animationSet1 = new AnimationSet(false);
        animationSet1.setFillAfter(true);
        animationSet1.addAnimation(animationRotation);
        animationSet1.addAnimation(animationMove);

        wishAnimationTextView.startAnimation(animationSet1);
    }
    public void animacionPull(){
        wishAnimationTextView.setVisibility(View.VISIBLE);
           Animation animationMove;
        animationMove = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f,
                Animation.RELATIVE_TO_PARENT, -0.2f,
                Animation.RELATIVE_TO_PARENT, -0.35f,
                Animation.RELATIVE_TO_PARENT, -0.2f);


        animationMove.setDuration(1000); 
        animationMove.setInterpolator(new LinearInterpolator()); 
        animationMove.setRepeatCount(0);
        animationMove.setFillAfter(false);


        Animation animationScale;
        animationScale = new ScaleAnimation(
                1.0F,
                2.2F,
                1.0F,
                2.2F,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF,0.5F);


        animationScale.setDuration(1000); 
        animationScale.setInterpolator(new LinearInterpolator()); 
        animationScale.setRepeatCount(0);
        animationScale.setFillAfter(false);
        AnimationSet animationSet= new AnimationSet(true);
        animationSet.setFillAfter(false);
        animationSet.addAnimation(animationScale);
        animationSet.addAnimation(animationMove);

        wishAnimationTextView.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                wishAnimationTextView.setText("\uD83C\uDF1F");
                Animation animationMove;
                animationMove = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -0.2f,
                        Animation.RELATIVE_TO_PARENT, 0.015f,
                        Animation.RELATIVE_TO_PARENT, -0.2f,
                        Animation.RELATIVE_TO_PARENT, -0.05f);

                animationMove.setDuration(1000); 
                animationMove.setInterpolator(new LinearInterpolator()); 
                animationMove.setRepeatCount(0);
                        animationMove.setFillAfter(false);


                Animation animationScale;
                animationScale = new ScaleAnimation(
                        2.2F,
                        6.0F,
                        2.2F,
                        6.0F,
                        Animation.RELATIVE_TO_SELF, 0.5F,
                        Animation.RELATIVE_TO_SELF,0.5F);


                animationScale.setDuration(1000); 
                animationScale.setInterpolator(new LinearInterpolator()); 
                animationScale.setRepeatCount(0);
                animationScale.setFillAfter(false);
                AnimationSet animationSet= new AnimationSet(true);
                animationSet.setFillAfter(true);

                animationSet.addAnimation(animationScale);
                animationSet.addAnimation(animationMove);

                wishAnimationTextView.startAnimation(animationSet);

                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Animation animation5;
                        animation5 = new AlphaAnimation(1.0F,0.0F);
                        animation5.setDuration(2000); 
                        animation5.setInterpolator(new LinearInterpolator()); 
                        animation5.setRepeatCount(0);
                        animation5.setFillAfter(true);

                        wishAnimationTextView.startAnimation(animation5);
                        animation5.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                wishAnimationTextView.setVisibility(View.INVISIBLE);

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

                animationScale.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                        try {
                            pull();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
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
    protected void onDestroy() {
        super.onDestroy();
        
        wishAnimationTextView.clearAnimation();
    }
}

