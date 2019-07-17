package com.example.gtraderprototype.entity;



import java.util.ArrayList;

/**
 * The entity class for the universe
 */
public class Universe {
    public static volatile Universe universe;
    public final ArrayList<System> systems = new ArrayList<>();
    private final int size = 200;

    /**
     *  adds a system to the universe
     * @param system the system desired
     */
    public void addSystem(System system){
        systems.add(system);
    }

    /**
     * adds a random system to the universe
     */
    public void addRandomSystem(){
        systems.add(new System(size));
    }

    /**
     * a string representation for the universe
     * @return
     */
    public String toString(){
        String str = "Universe of size "+this.size;
        StringBuilder strb = new StringBuilder(str);
        for(System sys: systems){
            strb.append(" system: ").append(sys.toString());

        }
        return strb.toString();
    }
}
