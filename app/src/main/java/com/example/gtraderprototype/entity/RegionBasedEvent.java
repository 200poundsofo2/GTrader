package com.example.gtraderprototype.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum RegionBasedEvent {
    DROUGHT(0),
    COLD(1),
    CROPFAIL(2),
    WAR(3),
    BOREDOM(4),
    PLAGUE(5),
    LACKOFWORKERS(6),
    NotApplicable(7);
    private int regionEvent;
    RegionBasedEvent(int regionEvent){
        this.regionEvent = regionEvent;
    }
    public int getRegionEvent(){ return regionEvent; }
    public static com.example.gtraderprototype.entity.RegionBasedEvent getRandomRegionEvent(){
        final List<RegionBasedEvent> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        int SIZE = VALUES.size();
        final Random RANDOM = new Random();
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
