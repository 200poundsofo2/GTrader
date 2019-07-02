package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.HashSet;

enum TechLevel{
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
    public RegionBasedEvent regionBasedEvent;
    public ArrayList<Item> sellableItems;
    public ArrayList<Item> buyableItems;
    public Region(){
        this.regionName = Database.getRandomName();
        this.coordinates = new int[]{(int)(Math.random()*90), (int)(Math.random()*90)};
        this.techLevel = TechLevel.getRandomLevel();
        this.regionBasedEvent = RegionBasedEvent.getRandomRegionEvent();
        this.sellableItems = new ArrayList<>();
        this.buyableItems = new ArrayList<>();
        this.resources = Resources.getRandomResources();
        Item[] Items = Item.values();
        for (Item item: Items) {
            int numericTechLevel = techLevel.getTechLevel();
            boolean regionCanSell = numericTechLevel >= item.getMinimumTechLevelToProduce();
            boolean regionCanBuy = numericTechLevel >= item.getMinimumTechLevelToUse();
            if ( regionCanSell ){
                Item sellableItem = item;
                sellableItem.setRegionPrice(numericTechLevel, resources, regionBasedEvent);
                sellableItems.add(sellableItem);
            }
            if ( regionCanBuy ){
                buyableItems.add(item);
            }
        }
    }

    public String toString(){
        return "REGION "+this.regionName+"("+coordinates[0]+","+coordinates[1]+") tech:"+this.techLevel+",rsc:"+this.resources;
    }

}
