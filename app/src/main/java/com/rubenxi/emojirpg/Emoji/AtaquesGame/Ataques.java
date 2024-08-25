package com.rubenxi.emojirpg.Emoji.AtaquesGame;

import java.util.ArrayList;

public class Ataques {

    public String fire = "\uD83D\uDD25";
    public String heal = "\uD83D\uDC93";
    public String surf = "\uD83C\uDF0A";
    public String darkBall = "\uD83C\uDF11";
    public String transform = "\uD83C\uDFAD";
    public ArrayList<String> ataques;

    public Ataques(){
        ataques = new ArrayList<>();
        ataques.add(fire);
        ataques.add(surf);
        ataques.add(darkBall);
        ataques.add(transform);

    }
    public ArrayList<String> getAllAtaques(){
        return  ataques;
    }


}
