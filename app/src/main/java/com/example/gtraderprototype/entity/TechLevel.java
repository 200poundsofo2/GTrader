package com.example.gtraderprototype.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TechLevel{
    PRE_AGRICULTURE(0), AGRICULTURE(1), MEDIEVAL(2), RENAISSANCE(3), EARLY_INDUSTRIAL(4), INDUSTRIAL(5), POST_INDUSTRIAL(6), HI_TECH(7);
    private int techLevel;
    public  int getTechLevel(){ return techLevel;}
    TechLevel (int techLevel){
        this.techLevel = techLevel;
    }
    public static TechLevel getRandomLevel(){
        final List<TechLevel> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        int SIZE = VALUES.size();
        final Random RANDOM = new Random();
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}