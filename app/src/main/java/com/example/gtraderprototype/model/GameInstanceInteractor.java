package com.example.gtraderprototype.model;

import android.content.Context;
import android.util.Log;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class GameInstanceInteractor extends Interactor{

    private final String localStateFilename = "states";
    public GameInstanceInteractor(Database db){
        super(db);

    }
    private Iterable<String> getLocalGames(Context context){

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
    public GameInstance newGame(Difficulty difficulty, Context context){
        GameInstance newinst = new GameInstance(difficulty);
        try{
            FileOutputStream outputStream;
            String fileContents = newinst.getGameID();
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            outputStream.write((fileContents+"\r\n").getBytes());
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Database.saveState(newinst);
        return newinst;
    }

    void removeGame(GameInstance instance, Context context){
        Iterable<String> currentsaves = getLocalGames(context);
        File file = new File(context.getFilesDir(), localStateFilename);
        file.delete();

        try{
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            for(String save: currentsaves){
                if(save!=instance.getGameID()){
                    outputStream.write((save+"\r\n").getBytes());
                }
            }
            outputStream.close();
            Database.removeState(instance);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
