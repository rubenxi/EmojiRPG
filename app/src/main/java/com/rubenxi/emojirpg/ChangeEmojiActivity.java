package com.rubenxi.emojirpg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rubenxi.emojirpg.Adventure.MapaActivity;
import com.rubenxi.emojirpg.Emoji.AnimacionStandardMe;
import com.rubenxi.emojirpg.Emoji.Equipo.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ChangeEmojiActivity extends AppCompatActivity {

    public ListView listView;
    private TextView textView13, ataquesTextView, levelTextView;
    public String emojiSeleccionado;
    private ArrayAdapter<String> adapter;
    DatabaseHelper dbHelper;
    public EditText nameEditText;
    public Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_emoji);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        textView13 = findViewById(R.id.textView13);
        nameEditText = findViewById(R.id.nameEditText);
        ataquesTextView = findViewById(R.id.ataquesTextView);
        levelTextView = findViewById(R.id.levelTextView);
        listView = findViewById(R.id.listView);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        emojiSeleccionado=sharedPref.getString(getString(R.string.emojiActual),"");
        textView13.setText(emojiSeleccionado);
        dbHelper = new DatabaseHelper(this);
        List<String> emojis = dbHelper.getTodos();
        for (String ataque : dbHelper.getAtaques(textView13.getText().toString())){
            ataquesTextView.setText(ataquesTextView.getText().toString()+ataque);
        }
        nameEditText.setText(dbHelper.getNombre(textView13.getText().toString()));
        levelTextView.setText("lvl " + dbHelper.getNivel(emojiSeleccionado));

        List<String> listaFinal = new ArrayList<>();
        for (String emoji : emojis){
            listaFinal.add(emoji+"  "+dbHelper.getFechaCaptura(emoji));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFinal);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameEditText.setText("");
                ataquesTextView.setText("");
                emojiSeleccionado = emojis.get(position);
                textView13.setText(emojiSeleccionado);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.emojiActual),emojiSeleccionado);
                editor.apply();
                for (String ataque : dbHelper.getAtaques(emojiSeleccionado)){
                    ataquesTextView.setText(ataquesTextView.getText().toString()+ataque);
                }
                nameEditText.setText(dbHelper.getNombre(emojiSeleccionado));
                levelTextView.setText("lvl "+dbHelper.getNivel(emojiSeleccionado));
            }
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s!=null && s.toString().length()>0){
                    dbHelper.setNombre(emojiSeleccionado,s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        AnimacionStandardMe animacionStandardMe = new AnimacionStandardMe(textView13);
        animacionStandardMe.animar();
    }

    public void onBackPressed(){
        dbHelper.setNombre(emojiSeleccionado,nameEditText.getText().toString());
        startActivity(new Intent(this, MapaActivity.class));
    }
}