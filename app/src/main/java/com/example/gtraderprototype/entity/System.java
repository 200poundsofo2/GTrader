package com.example.gtraderprototype.entity;




import com.example.gtraderprototype.model.Database;

import java.util.ArrayList;
import java.util.Collections;

/**
 * this class is the entity that represents a system that is contained in universe
 */
public class System {
    //public Pirate[] pirates;
    private final ArrayList<Region> regions = new ArrayList<>();
    private final int systemSize;
    private String systemName;
    private double[] coordinates;

    /**
     * constructor for a randomly generates a system
     */
    public System(){
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;

    }

    /**
     * constructor for a randomly generates a system
     * @param universeSize the number of systems to be created
     */
    public System(int universeSize){
        this.systemName = Database.getRandomName();
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;
        this.coordinates = new double[]{(Math.random()*universeSize), (Math.random()*universeSize)};
    }

    /**
     * constructor for a specific system
     * @param name name of the system created
     * @param lat latitude of the system to be created
     * @param lng longitude of the system to be created
     */
    public System(String name, double lat, double lng){
        this.systemName = name;
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;
        this.coordinates = new double[]{lat, lng};
    }

    /**
     * adds a region to the system
     * @param region region to be added
     */
    public void addRegion(Region region){
        regions.add(region);
    }

    /**
     * get a random region from the system
     * @return a random region from the system
     */
    public Region getRandomRegion(){
        return regions.get((int)(Math.random()*regions.size()));
    }

    /**
     * get all regions from the system
     * @return all regions from the system
     */
    public Iterable<Region> getRegions(){
        return Collections.unmodifiableList(regions);
    }

    /**
     * get the name of the system
     * @return the region's name
     */
    public String getSystemName(){
        return systemName;
    }

    /**
     * gets the system's size
     * @return the size of the system
     */
    public int getSystemSize(){
        return systemSize;
    }

    /**
     * get rhe location of the system
     * @return the coordinates of the system
     */
    public double[] getcoordinates(){
        return coordinates.clone();
    }

    /**
     * the string representation of the system and it's contents
     * @return the string representation of the system and it's contents
     */
    public String toString(){
        String str =
                " Sys "+this.systemName+'('+this.coordinates[0]
                        +','+this.coordinates[1]+") with Regions: ";
        StringBuilder strb = new StringBuilder();
        strb.append(str);
        for(Region sys: regions){
            strb.append(" region: ").append(sys.toString());
        }
        return strb.toString();
    }

}
