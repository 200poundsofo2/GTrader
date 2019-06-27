package com.example.gtraderprototype.entity;

import android.provider.ContactsContract;

import com.example.gtraderprototype.model.Database;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum TechLevel{
    PRE_AGRICULTURE, AGRICULTURE, MEDIEVAL, RENAISSANCE, EARLY_INDUSTRIAL, INDUSTRIAL, POST_INDUSTRIAL, HI_TECH;

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

    //public Police[] police;
    //public Trader[] traders;
    public String regionName;
    public int[] coordinates;
    public TechLevel techLevel;
    public Resources resources;
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
