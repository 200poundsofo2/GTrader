package com.example.gtraderprototype.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String localStateFilename = "states";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame();

    }

    private ArrayList<String> getLocalGames(){
        Context context = this;
        File file = new File(context.getFilesDir(), localStateFilename);
        ArrayList<String> gameIDs = new ArrayList<>();
        try{

            if(file.exists()){
                Log.d("GTrader", "Retrieving games...");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String temp = "";
                temp = br.readLine();
                while(temp!=null){
                    gameIDs.add(temp);
                    temp = br.readLine();
                    Database.retrieveGameFromDB(temp);
                }
                Log.d("GTrader", gameIDs.toString());
            }
        }catch(Exception e){
            Log.d("ERR", e.toString());
        }
        return gameIDs;
    }
    void newGame(){
        GameInstance newinst = new GameInstance();
        try{
                FileOutputStream outputStream;
                String fileContents = newinst.getGameID();
                ArrayList<String> oldergames = getLocalGames();
                outputStream = openFileOutput(localStateFilename, Context.MODE_APPEND);
                outputStream.write((fileContents+"\r\n").getBytes());
                Log.d("GTrader", fileContents);
                outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Database.saveState(newinst);

    }
}
