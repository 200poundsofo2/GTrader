package com.example.gtraderprototype.entity;

import android.provider.ContactsContract;

import com.example.gtraderprototype.model.Database;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.HashSet;

enum TechLevel{
    PRE_AGRICULTURE(0), AGRICULTURE(1), MEDIEVAL(2), RENAISSANCE(3), EARLY_INDUSTRIAL(4), INDUSTRIAL(5), POST_INDUSTRIAL(6), HI_TECH(7);

    private int level;
    TechLevel(int level){
        this.level=level;
    }

    public int getLevel(){
        return level;
    }

    public static TechLevel getRandomLevel(){
        final List<TechLevel> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        int SIZE = VALUES.size();
        final Random RANDOM = new Random();
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

enum Resources{
    NO_SPECIAL_RESOURCES, MINERAL_RICH, MINERAL_POOR, DESERT, LOTS_OF_WATER, RICH_SOIL, POOR_SOIL, RICH_FAUNA, LIFELESS, WEIRD_MUSHROOMS, LOTS_OF_HERBS, ARTISTIC, WARLIKE;

    public static Resources getRandomResources(){
        final List<Resources> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        int SIZE = VALUES.size();
        final Random RANDOM = new Random();
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

enum Condition{
    DROUGHT,LOTSOFWATER,DESERT,COLD,RICHFAUNA,LIFELESS,CROPFAIL,RICHSOIL,POORSOIL,WAR,MINERALRICH,MINERALPOOR,BOREDOM,ARTISTIC,WARLIKE,PLAGUE,LOTSOFHERBS,
    LACKOFWORKERS,WEIRDMUSHROOMS;

}

public class Region {
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void addCondition(Condition condition){
        conditionSet.add(condition);
    }

    public HashSet<Condition> getConditionSet() {
        return conditionSet;
    }

    //public Police[] police;
    //public Trader[] traders;
    public String regionName;
    public int[] coordinates;
    public TechLevel techLevel;
    public Resources resources;

    private HashSet<Condition> conditionSet;

    public Region(){
        this.regionName = Database.getRandomName();
        this.coordinates = new int[]{(int)(Math.random()*90), (int)(Math.random()*90)};
        this.techLevel = TechLevel.getRandomLevel();
        this.resources = Resources.getRandomResources();
    }

    public String toString(){
        return "REGION "+this.regionName+"("+coordinates[0]+","+coordinates[1]+") tech:"+this.techLevel+",rsc:"+this.resources;
    }

}
