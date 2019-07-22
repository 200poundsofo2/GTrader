package com.example.gtraderprototype.entity.PirateGame;

import android.graphics.Bitmap;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

public class GameVariables {
    public  boolean alive;
    public  int goal;
    public  int kill;
    public  int width,height;
    public  float ratio;
    public  List<Plane> objects = new LinkedList<>();
    public  List<Plane> enemy = new LinkedList<>();
    public  Bitmap player,pirate,bullet;
    public  playerPlane playerPlane;

    public GameVariables(){
        alive=true;
        goal=50;
    }

}
