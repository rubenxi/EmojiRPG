package com.rubenxi.emojirpg;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private TextView textView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        textView10 = findViewById(R.id.textView10);
        textView10.setMovementMethod(new ScrollingMovementMethod());
        String lista="";
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<String> emojis = dbHelper.getTodos();
        for (String emoji : emojis){
            if (emoji.length()>0 && emoji!="")
                lista = lista+" "+emoji;
        }
        textView10.setText(lista);

    }
    public void onBackPressed(){
        startActivity(new Intent(this, MapaActivity.class));
    }
}