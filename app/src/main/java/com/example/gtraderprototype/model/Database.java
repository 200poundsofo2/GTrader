package com.example.gtraderprototype.model;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Universe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Database {
    static ArrayList<String> names = new ArrayList<>();
    /*
        This class communicates with the firebase database
     */

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference gameIDListRef = database.getReference("Save States");
    public Database(){

    }
    public void getNames(){

        DatabaseReference ref = database.getReference("INDEX/NAMES/LOCATIONS");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    names.add(postSnapshot.getValue().toString());
                }
                getGlobalUniverse();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public static String getNewGameID(){

                String tempID;
                    tempID = "";
                    for(int i=0;i<16;i++){
                        int key = (int)(Math.floor(Math.random()*26));
                        tempID+=(char)((int)'a'+key);
                    }
        Log.d("GTrader", "Key "+tempID+" generated.");
                return tempID;

    }
    public static void saveState(GameInstance instance){
        DatabaseReference stateref = database.getReference("Save States/"+instance.getGameID());
        stateref.setValue(instance);
        Log.d("Gtrader", instance.getGameID()+" Saved to DB");
    }

    public static void removeState(GameInstance instance){
        DatabaseReference ref = database.getReference("Save States");
        ref.child(instance.getGameID()).removeValue();
    }

    public static void retrieveGameFromDB(String gameID){
        DatabaseReference ref = database.getReference("Save States/"+gameID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                Post post = dataSnapshot.getValue(Post.class);
                System.out.println(post);
                 */
                Log.d("GTraderDB", "DB Change: "+dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void getGlobalUniverse(){
        DatabaseReference ref = database.getReference("Universe");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null){
                    Log.d("GTrader", "Universe not created");

                    Model.getInstance().getUniverseInteractor().setUniverse(new Universe());
                    Log.d("GTrader", Model.getInstance().getUniverseInteractor().getUniverse().toString());
                }else{
                    /*-- TODO
                    Universe universe = dataSnapshot.getValue(Universe.class);
                    Model.getInstance().getUniverseInteractor().setUniverse(universe);
                    Log.d("GTrader", "Universe is created");
                    */
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    public static String getRandomName(){
       return names.get((int)(Math.random()*90));
    }



}
