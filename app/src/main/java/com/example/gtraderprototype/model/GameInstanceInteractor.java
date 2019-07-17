package com.example.gtraderprototype.model;

import android.content.Context;
import android.provider.ContactsContract;
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

    final String localStateFilename = "states";
    private GameInstance gameInstance;
    public GameInstanceInteractor(Database db){
        super(db);

    }

    public GameInstance getGameInstance(){
        return gameInstance;
    }
    public ArrayList<String> getLocalGames(Context context){

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
                }
                Log.d("GTrader", gameIDs.toString());
            }
        }catch(Exception e){
            Log.d("ERR", e.toString());
        }
        return gameIDs;
    }
    public void setInstance(GameInstance instance){
        this.gameInstance = instance;
        Log.d("GTrader", instance.toString());
    }
    public void newGame(Difficulty difficulty, Player player, Context context){
        gameInstance = new GameInstance(difficulty, player);
        try{
            FileOutputStream outputStream;
            String fileContents = gameInstance.getGameID();
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            outputStream.write((fileContents+"\r\n").getBytes());
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("GTrader", "New instance: "+gameInstance.toString());
        Database.saveState(gameInstance);
    }


    public void removeGame(String gameID, Context context){
        ArrayList<String> currentSaves = getLocalGames(context);
        File file = new File(context.getFilesDir(), localStateFilename);
        file.delete();

        try{
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            for(String save: currentSaves){
                if(!save.equals(gameID)){
                    outputStream.write((save+"\r\n").getBytes());
                }
            }
            outputStream.close();
            Database.removeState(gameID);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void saveGameToDB(){
        Database.saveState(this.gameInstance);
    }
}
