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
    /**
     * gets the name of the region
     * @return gets the name of the region
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * sets the region's name
     * @param regionName the desired name of the region
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * the coordinates of the region
     * @return the coordinate of the region
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     * sets the coordinates of the region
     * @param coordinates the desired location of the region
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * gets the tech level of the region
     * @return the tech level of the region
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * sets the tech level of the region
     * @param techLevel desired tech level
     */
    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * the resource type of the region
     * @return the resource type
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * sets the resource type
     * @param resources desired resource type
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public String regionName;
    public List<Double> coordinates = new ArrayList<>();
    private TechLevel techLevel;
    public Resources resources;
    private RegionBasedEvent regionBasedEvent;
    public ArrayList<Item> sellableItems = new ArrayList<>();
    public ArrayList<Item> buyableItems = new ArrayList<>();

    /**
     * constructor for a region
     * @param name name of region
     * @param lat latitude for a region
     * @param lng longitude for a region
     */
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

    /**
     * a string representation of a region and it contents
     * @return a string representation of a region and it contents
     */
    public String toString(){
        return "REGION "+this.regionName+"("+coordinates.get(0)+","+coordinates.get(0)+") tech:"+this.techLevel+",rsc:"+this.resources+", Sellable: "+sellableItems.toString()+ ", Buyable: "+buyableItems.toString();
    }

}
