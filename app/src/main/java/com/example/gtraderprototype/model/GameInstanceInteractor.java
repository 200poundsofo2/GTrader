package com.example.gtraderprototype.model;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.GameInstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * a connection between the database and the current instance of the game
 */
public class GameInstanceInteractor extends Interactor{

    private final String localStateFilename = "states";

    /**
     * a constructor for GameInstanceInteractor
     * @param db the database
     */
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
                String temp;
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

    /**
     * create a new game
     * @param difficulty the difficulty of the game
     * @param context android stuff
     */
    public void newGame(Difficulty difficulty, Context context){
        GameInstance newinst = new GameInstance(difficulty);
        try{
            FileOutputStream outputStream;
            String fileContents = gameInstance.getGameID();
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            outputStream.write((fileContents+"\r\n").getBytes());
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Database.saveState(newinst);
    }

    void removeGame(GameInstance instance, Context context){
        Iterable<String> currentsaves = getLocalGames(context);
        File file = new File(context.getFilesDir(), localStateFilename);
        file.delete();

        try{
            FileOutputStream outputStream;
            outputStream = context.openFileOutput(localStateFilename, Context.MODE_APPEND);
            for(String save: currentsaves){
                if(!Objects.equals(save, instance.getGameID())){
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
