package com.example.gtraderprototype.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Resources {
    LOTSOFWATER(0),
    RICHFAUNA(1),
    RICHSOIL(2),
    MINERALRICH(3),
    ARTISTIC(4),
    WARLIKE(5),
    LOTSOFHERBS(6),
    WEIRDMUSHROOMS(7),
    DESERT(9),
    LIFELESS(10),
    POORSOIL(11),
    MINERALPOOR(12),
    NotApplicable(13);
    private final int resourceLevel;
    Resources(int resourceLevel){
        this.resourceLevel = resourceLevel;
    }
    public int getResourceLevel(){ return resourceLevel; }
        public static com.example.gtraderprototype.entity.Resources getRandomResources(){
            final List<com.example.gtraderprototype.entity.Resources> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
            int SIZE = VALUES.size();
            final Random RANDOM = new Random();
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
}
