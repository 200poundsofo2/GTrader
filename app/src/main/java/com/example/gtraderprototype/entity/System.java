package com.example.gtraderprototype.entity;

import android.provider.ContactsContract;

import com.example.gtraderprototype.model.Database;

import java.util.ArrayList;

public class System {
    //public Pirate[] pirates;
    private ArrayList<Region> regions = new ArrayList<>();
    private int systemSize;
    private String systemName;
    public int[] coordinates;

    public System(){
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;

    }
    public System(int universeSize){
        this.systemName = Database.getRandomName();
        this.systemSize = (int) Math.floor(Math.random()*15)+ 5;
        this.coordinates = new int[]{(int)(Math.random()*universeSize), (int)(Math.random()*universeSize)};
        for(int r = 0; r<universeSize/40||(Math.random()*1<0.3); r++){
            regions.add(new Region());
        }
    }
    public ArrayList<Region> getRegions(){
        return regions;
    }
    public String getSystemName(){
        return systemName;
    }
    public int getSystemSize(){
        return systemSize;
    }
    public int[] getcoordinates(){
        return coordinates;
    }
    public String toString(){
        String str = " Sys "+this.systemName+'('+this.coordinates[0]+','+this.coordinates[1]+") with Regions: ";
        StringBuilder strb = new StringBuilder();
        strb.append(str);
        for(Region sys: regions){
            strb.append(" region: "+sys.toString());
        }
        return strb.toString();
    }

}
