package com.example.gtraderprototype.model;

import com.example.gtraderprototype.entity.Marketplace;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.entity.Region;

/**
 * a class that retrieves the relevant information from the database for the player
 */
public class PlayerInteractor extends Interactor {
    /**
     * constructor for the player interactor
     * @param db database
     */
    public PlayerInteractor(Database db){
        super(db);
    }

    private final Player player = Player.getPlayer();

    private Marketplace marketplace = new Marketplace(player);

    /**
     * gets the player
     * @return the player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * set the region the the player is located in
     * @param region a space that exits within a system
     */
    public void setLocation(Region region){
        player.setRegionName(region.regionName);
        marketplace = new Marketplace(Player.getPlayer());
        Database.saveState(Model.getInstance().getGameInstanceInteractor().getGameInstance());
    }

    /**
     * subtracts the fuel from the player's ship
     * @param fuelAmt amount of fuel to be subtracted
     */
    public void deductFuel(int fuelAmt){
        player.getSpaceShip().deductFuel(fuelAmt);
    }

    /**
     * get the region that the player is currently located in
     * @return the player's location
     */
    public Region getLocation(){
        return Model.getInstance().getUniverseInteractor().getRegionByName(player.getRegionName());
    }

    /**
     * gets an instance of the market place
     * @return an instance of the market place
     */
    public Marketplace getMarketplace(){
        return marketplace;
    }

}
