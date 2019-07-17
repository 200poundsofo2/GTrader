package com.example.gtraderprototype.entity;

import com.example.gtraderprototype.model.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.HashSet;


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
