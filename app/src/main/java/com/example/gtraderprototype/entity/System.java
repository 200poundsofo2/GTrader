package com.example.gtraderprototype.entity;


import com.example.gtraderprototype.model.Database;

import java.util.ArrayList;

public class System {
    //public Pirate[] pirates;
    private final ArrayList<Region> regions = new ArrayList<>();
    private final int systemSize;
    private String systemName;
    private double[] coordinates;

    public System(){
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;

    }
    public System(int universeSize){
        this.systemName = Database.getRandomName();
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;
        this.coordinates = new double[]{(Math.random()*universeSize), (Math.random()*universeSize)};
    }
    public System(String name, double lat, double lng){
        this.systemName = name;
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;
        this.coordinates = new double[]{lat, lng};
    }

    public void addRegion(Region region){
        regions.add(region);
    }

    public Region getRandomRegion(){
        return regions.get((int)(Math.random()*regions.size()));
    }
    public Iterable<Region> getRegions(){
        return regions;
    }
    public String getSystemName(){
        return systemName;
    }
    public int getSystemSize(){
        return systemSize;
    }
    public double[] getcoordinates(){
        return coordinates;
    }


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
