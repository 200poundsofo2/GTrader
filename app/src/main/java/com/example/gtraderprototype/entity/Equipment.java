package com.example.gtraderprototype.entity;

/**
 * equipment that can be equipped for the spaceship
 */
public class Equipment {
    private int strength;
    private int health;
    private TypeOfEquipment type;
    private Item item;

    /**
     * initializing attributes of the equipment
     *
     * @param strength the strengh of the equipment
     * @param health the health of the equipment
     * @param type the type of the equipment
     * @param item the items that are in the equipment
     */
    public Equipment(int strength, int health, TypeOfEquipment type, Item item){
        this.strength=strength;
        this.health=health;
        this.type = type;
        this.item = item;
    }

    /**
     * getting the strength of the equipment
     * @return strength
     */
    public int getStrength() {
        return strength;
    }
    /**
     * getting the health of the equipment
     * @return health
     */
    public int getHealth(){
        return this.health;
    }
    /**
     * getting the type of the equipment
     * @return type
     */
    public TypeOfEquipment getType() { return this.type;}
    /**
     * getting the item of the equipment
     * @return item
     */
    public Item getItem() { return this.item; }
}
