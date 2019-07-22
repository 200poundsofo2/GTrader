package com.example.gtraderprototype.entity;

import java.util.ArrayList;

public class Universe {
    public static volatile Universe universe = null;
    public ArrayList<System> systems = new ArrayList<>();
    public int size = 200;


    public Universe(){

    }
    public void addSystem(System system){
        systems.add(system);
    }
    public void addRandomSystem(){
        systems.add(new System(size));
    }
    public String toString(){
        String str = "Universe of size "+this.size;
        StringBuilder strb = new StringBuilder(str);
        for(System sys: systems){
            strb.append(" system: "+sys.toString());
        }
        return strb.toString();
    }
}
