package com.example.gtraderprototype.entity;


import java.util.ArrayList;

/**
 *  a space that exists within a system
 */
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
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * sets the coordinates of the region
     * @param coordinates the desired location of the region
     */
    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates.clone();
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

    //public Police[] police;
    //public Trader[] traders;
    public String regionName;
    public double[] coordinates;
    private TechLevel techLevel;
    private Resources resources;

    /**
     * gets the region event
     * @return a region based event
     */
    public RegionBasedEvent getRegionBasedEvent() {
        return regionBasedEvent;
    }

    private RegionBasedEvent regionBasedEvent;;
    public final ArrayList<Item> sellableItems = new ArrayList<>();
    public final ArrayList<Item> buyableItems = new ArrayList<>();
    public Marketplace marketplace;

    /**
     * constructor for a region
     * @param name name of region
     * @param lat latitude for a region
     * @param lng longitude for a region
     */
    public Region(String name, double lat, double lng){
        this.regionName = name;
        this.coordinates = new double[]{lat,lng};
        this.techLevel = TechLevel.getRandomLevel();
        regionBasedEvent = RegionBasedEvent.getRandomRegionEvent();
        this.resources = Resources.getRandomResources();
        Item[] items = Item.values();
        for (Item item: items) {
            int numericTechLevel = techLevel.getTechLevel();
            boolean regionCanSell = numericTechLevel >= item.getMinimumTechLevelToProduce();
            boolean regionCanBuy = numericTechLevel >= item.getMinimumTechLevelToUse();
            if ( regionCanSell ){
                item.setRegionPrice(numericTechLevel, resources, regionBasedEvent);
                sellableItems.add(item);
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
        return "REGION "+this.regionName+"("+coordinates[0]+","+coordinates[1]
                +") tech:"+this.techLevel+",rsc:"+this.resources+", Sellable: "
                +sellableItems.toString()+ ", Buyable: "+buyableItems.toString();
    }

}
