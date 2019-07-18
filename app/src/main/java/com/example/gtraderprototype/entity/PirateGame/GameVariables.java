package com.example.gtraderprototype.entity.PirateGame;

import android.graphics.Bitmap;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

public class GameVariables {
    public  static int kill;
    public  static int width,height;
    public  static float ratio;
    public static List<Plane> objects = new LinkedList<>();
    public static List<Plane> enemy = new LinkedList<>();
    public static Bitmap player,pirate,bullet;
    public static playerPlane playerPlane;
}
