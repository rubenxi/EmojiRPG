package com.rubenxi.emojirpg.Emoji;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.AllEmojis;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Captura {
    Enemy enemy;
    Context context;
    public Captura(Enemy enemy, Context context){
        this.enemy=enemy;
        this.context=context;
    }

    public void capturar(){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        List<String> emojisDesbloqueados = dbHelper.getTodos();

            if (!emojisDesbloqueados.contains(enemy) && emojisDesbloqueados.size()!=new AllEmojis().emojiListArray.size()){
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.insertarNuevo(enemy.enemyEmoji);
                Date currentDate = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(currentDate);
                databaseHelper.setFechaCaptura(enemy.enemyEmoji,formattedDate);
                databaseHelper.setAtaques(enemy.enemyEmoji,enemy.ataques);
                databaseHelper.setNivel(enemy.enemyEmoji, "0");
                databaseHelper.setVida(enemy.enemyEmoji,"5");
                databaseHelper.setVidaTotal(enemy.enemyEmoji,"5");
                Toast.makeText(context, "You got "+enemy.enemyEmoji+"!", Toast.LENGTH_SHORT).show();

            }

            irGacha();

    }

    public void irGacha() {

        Intent intent = new Intent(context, MapaActivity.class);
        context.startActivity(intent);
    }
}
