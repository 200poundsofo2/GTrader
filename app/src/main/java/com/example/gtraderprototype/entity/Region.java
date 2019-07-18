package com.example.gtraderprototype.entity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
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
    public List<Double> coordinates = new ArrayList<>();
    public TechLevel techLevel;
    public Resources resources;
    public RegionBasedEvent regionBasedEvent;
    public ArrayList<Item> sellableItems = new ArrayList<>();
    public ArrayList<Item> buyableItems = new ArrayList<>();
    public Marketplace marketplace;

    public Region(String name, double lat, double lng){
        this.regionName = name;
        this.coordinates.add(lat);
        this.coordinates.add(lng);
        this.techLevel = TechLevel.getRandomLevel();
        this.regionBasedEvent = RegionBasedEvent.getRandomRegionEvent();
        this.resources = Resources.getRandomResources();
        Item[] items = Item.values();
        for (Item item: items) {
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
        return "REGION "+this.regionName+"("+coordinates.get(0)+","+coordinates.get(0)+") tech:"+this.techLevel+",rsc:"+this.resources+", Sellable: "+sellableItems.toString()+ ", Buyable: "+buyableItems.toString();
    }

}
