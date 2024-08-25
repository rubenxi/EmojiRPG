package com.rubenxi.emojirpg.Emoji.Equipo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rubenxi.emojirpg.Emoji.Ataque;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "equipo.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE equipo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "emoji TEXT," +
                "nombre TEXT," +
                "fechaCaptura TEXT," +
                "nivel TEXT," +
                "vida TEXT," +
                "vidaTotal TEXT," +
                "estado TEXT," +
                "ataques TEXT," +
                "listaAtaques TEXT," +
                "fusion TEXT," +
                "tipo TEXT," +
                "amistad TEXT," +
                "notas TEXT," +
                "objetoEquipado TEXT," +
                "frases TEXT," +
                "edad TEXT," +
                "abilidad TEXT," +
                "atributo1 TEXT," +
                "atributo2 TEXT," +
                "atributo3 TEXT," +
                "atributo4 TEXT," +
                "atributo5 TEXT," +
                "atributo6 TEXT" +
                ")");


    }
    public void eliminarTodo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM tests");

        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
        }
    }
    @SuppressLint("Range")
    public List<String> getTodos() {
        List<String> emojis = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT emoji FROM equipo", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                emojis.add(cursor.getString(0));
            }
            cursor.close();
        }
        db.close();
        return emojis;
    }

    public void insertarNuevo(String emoji){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("emoji",emoji);
        db.insert("equipo",null,values);
        db.close();
    }
    public void setNombre(String emoji,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }
    public void setVida(String emoji,String vida){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("vida",vida);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }
    public void setVidaTotal(String emoji,String vidaTotal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("vidaTotal",vidaTotal);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }
    public void setFechaCaptura(String emoji,String fechaCaptura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fechaCaptura",fechaCaptura);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }

    public void setAtaques(String emoji,ArrayList<Ataque> ataques){
        String ataquesString = "";
        for (Ataque ataque : ataques){
            if (ataquesString.length()==0){
                ataquesString = ataque.attackEmoji;
            }
            else {
                ataquesString = ataquesString + " " + ataque.attackEmoji;
            }
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ataques",ataquesString);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }

    public void setAtaquesString(String emoji,ArrayList<String> ataques){
        String ataquesString = "";
        for (String ataque : ataques){
            if (ataquesString.length()==0){
                ataquesString = ataque;
            }
            else {
                ataquesString = ataquesString + " " + ataque;
            }
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ataques",ataquesString);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }

    public void setNivel(String emoji,String nivel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nivel",nivel);
        String whereClause = "emoji=?";
        String[] whereArgs = {emoji};
        db.update("equipo",values,whereClause,whereArgs);
        db.close();
    }

    public void delete(String emoji){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("equipo","emoji" + " = ?", new String[]{emoji});
        db.close();
    }

    @SuppressLint("Range")
    public String getFechaCaptura(String emoji) {
        String fechaCaptura = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM equipo WHERE emoji = ?", new String[]{emoji});

        if (cursor != null && cursor.moveToFirst()) {
            fechaCaptura = cursor.getString(cursor.getColumnIndex("fechaCaptura"));
            cursor.close();
        }
        db.close();
        return fechaCaptura;
    }

    @SuppressLint("Range")
    public List<String> getAtaques(String emoji) {
        List<String> ataques = new ArrayList<>();
        String ataquesString = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ataques FROM equipo WHERE emoji = ?", new String[]{emoji});
        if (cursor != null && cursor.moveToFirst()) {
            ataquesString = cursor.getString(cursor.getColumnIndex("ataques"));
            cursor.close();
        }
        String[] ataquesSplit = ataquesString.split(" ");

        
        for (String ataque : ataquesSplit) {
            ataques.add(ataque);
        }
        db.close();
        return ataques;
    }

    @SuppressLint("Range")
    public String getNombre(String emoji) {
        String nombre = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM equipo WHERE emoji = ?", new String[]{emoji});
        if (cursor != null && cursor.moveToFirst()) {
            nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            cursor.close();
        }

        db.close();
        return nombre;
    }
    @SuppressLint("Range")
    public String getVida(String emoji) {
        String vida = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT vida FROM equipo WHERE emoji = ?", new String[]{emoji});
        if (cursor != null && cursor.moveToFirst()) {
            vida = cursor.getString(cursor.getColumnIndex("vida"));
            cursor.close();
        }

        db.close();
        return vida;
    }
    @SuppressLint("Range")
    public String getVidaTotal(String emoji) {
        String vidaTotal = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT vidaTotal FROM equipo WHERE emoji = ?", new String[]{emoji});
        if (cursor != null && cursor.moveToFirst()) {
            vidaTotal = cursor.getString(cursor.getColumnIndex("vidaTotal"));
            cursor.close();
        }

        db.close();
        return vidaTotal;
    }
    @SuppressLint("Range")
    public String getNivel(String emoji) {
        String nombre = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nivel FROM equipo WHERE emoji = ?", new String[]{emoji});
        if (cursor != null && cursor.moveToFirst()) {
            nombre = cursor.getString(cursor.getColumnIndex("nivel"));
            cursor.close();
        }

        db.close();
        return nombre;
    }
}
