package com.example.gtraderprototype.model;
import android.util.Log;

import com.example.gtraderprototype.entity.GameInstance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Database {
    /*
        This class communicates with the firebase database
     */

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference gameIDListRef = database.getReference("Save States");

    public Database(){


    }
    public static String getNewGameID(){

                String tempID;
                    tempID = "";
                    for(int i=0;i<16;i++){
                        int key = (int)(Math.floor(Math.random()*10));
                        tempID+=(char)((int)'a'+key);
                    }
        Log.d("GTrader", "Key "+tempID+" generated.");
                return tempID;

    }
    public static void saveState(GameInstance instance){
        DatabaseReference stateref = database.getReference("Save States/"+instance.getGameID());
        stateref.setValue(instance);
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
                Log.d("GTraderDB", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }




}
