package com.example.gtraderprototype.model;
import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Region;
import com.example.gtraderprototype.entity.System;
import com.example.gtraderprototype.entity.Universe;
import com.example.gtraderprototype.views.SpacePortActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * the class that connects to firebase or the back end
 */
public class Database {
    private static final ArrayList<String> names = new ArrayList<>();
    /*
        This class communicates with the firebase database
     */

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference gameIDListRef = database.getReference("Save States");

    /**
     * creates a serialized key as an id for the game
     * @return
     */
    public static String getNewGameID(){
        int numberOfCharacters = 16;
        int charactersInAlphabet = 26;
                StringBuilder tempID;
                    tempID = new StringBuilder();
                    for(int i=0;i<numberOfCharacters;i++){
                        int key = (int)(Math.floor(Math.random()*charactersInAlphabet));
                        tempID.append((char) ((int) 'a' + key));
                    }
        Log.d("GTrader", "Key "+tempID+" generated.");
                return tempID.toString();

    }

    /**
     * saves an instance of the game to the database
     * @param instance the game
     */
    public static void saveState(GameInstance instance){
        DatabaseReference stateref = database.getReference("Save States/"+instance.getGameID());
        stateref.setValue(instance);
        Log.d("Gtrader", instance.getGameID()+" Saved to DB");
    }

    /**
     * delete an instance of the game
     * @param instance
     */
    public static void removeState(GameInstance instance){
        DatabaseReference ref = database.getReference("Save States");
        ref.child(instance.getGameID()).removeValue();
    }
    public static void removeState(String gameID){
        DatabaseReference ref = database.getReference("Save States");
        ref.child(gameID).removeValue();
    }
    /**
     * loads the game from teh database
     * @param gameID the serialized key
     */
    public static void retrieveGameFromDB(String gameID){
        DatabaseReference ref = database.getReference("Save States/"+gameID);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                /*
                Post post = dataSnapshot.getValue(Post.class);
                System.out.println(post);
                 */
                Log.d("GTraderDB", "Retrieved save from DB: "+dataSnapshot.toString());
                GameInstance instance = dataSnapshot.getValue(GameInstance.class);
                Model.getInstance().getGameInstanceInteractor().setInstance(instance);
                Model.getInstance().getPlayerInteractor().setPlayer(instance.getPlayer());
                Intent myIntent = new Intent(context, SpacePortActivity.class);
                context.startActivity(myIntent);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    /**
     *  gets the universe
     */
    public void getGlobalUniverse(){
        DatabaseReference ref = database.getReference("INDEX/SYSTEMS");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Log.d("GTraderFirebaseWhole", dataSnapshot.getValue().toString());

                    //Make new Universe
                    Model.getInstance().getUniverseInteractor().setUniverse(new Universe());
                    for(DataSnapshot systemSnapshot : dataSnapshot.getChildren()){
                        Log.d("GTraderFirebaseSys", systemSnapshot.toString());
                        System subSystem = new System(systemSnapshot.getKey(),
                                systemSnapshot.child("/location/lat").getValue(Double.class),
                                systemSnapshot.child("/location/lng").getValue(Double.class));
                        for(DataSnapshot regionSnapshot : systemSnapshot.child("REGIONS")
                                .getChildren()){
                            Log.d("GTraderFirebaseRegion", regionSnapshot.toString());
                            Region newRegion = new Region(regionSnapshot.getKey(),
                                    regionSnapshot.child("/location/lat").getValue(Double.class),
                                    regionSnapshot.child("/location/lng").getValue(Double.class));

                            //Add region to system
                            subSystem.addRegion(newRegion);
                        }

                        //Add system to Universe
                        Model.getInstance().getUniverseInteractor().addSystem(subSystem);

                    }

                    //Log Universe
                    Log.d("GTrader", Model.getInstance().getUniverseInteractor().getUniverse()
                            .toString());
                }/*else{
                    Universe universe = dataSnapshot.getValue(Universe.class);
                    Model.getInstance().getUniverseInteractor().setUniverse(universe);
                    Log.d("GTrader", "Universe is created");
                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    /**
     * creates a random name for the game id
     * @return game id
     */
    public static String getRandomName(){
        int rangeOfValues = 90;
       return names.get((int)(Math.random()*rangeOfValues));
    }



}
