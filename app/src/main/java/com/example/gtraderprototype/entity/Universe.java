package com.example.gtraderprototype.entity;



import java.util.ArrayList;

public class Universe {
    public static volatile Universe universe;
    public final ArrayList<System> systems = new ArrayList<>();
    private final int size = 200;


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
            strb.append(" system: ").append(sys.toString());

        }
        return strb.toString();
    }
}
