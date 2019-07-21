package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.GameInstance;
import com.example.gtraderprototype.entity.Item;
import com.example.gtraderprototype.model.Database;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.ConfigurationViewModel;
import com.example.gtraderprototype.viewmodels.MarketViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class SavedGamesActivity extends AppCompatActivity {
    private RecyclerView saveRecyclerView;
    private SaveAdapter saveAdapter, removeAdapter;
    private ArrayList<String> savesList = new ArrayList<>();
    private ConfigurationViewModel viewmodel;
    private RecyclerView.LayoutManager layoutManagerSaves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_games);
        saveRecyclerView = findViewById(R.id.saves_recycler_view);
        layoutManagerSaves = new LinearLayoutManager(this);
        saveRecyclerView.setLayoutManager(layoutManagerSaves);
        viewmodel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

        savesList = Model.getInstance().getGameInstanceInteractor().getLocalGames(this);
        saveAdapter = new SaveAdapter(savesList, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                String saveID = saveAdapter.getGameIDAt(position);
                Log.d("GTrader", "Loading "+saveID);
                Database.retrieveGameFromDB(saveID, SavedGamesActivity.this);
            }
            public void recyclerViewListClickedRemove(View v, int position) {
                String saveID = saveAdapter.getGameIDAt(position);
                Log.d("GTrader", "Deleting "+saveID);
                viewmodel.removeGame(saveID, SavedGamesActivity.this);
                savesList.remove(position);
                saveAdapter.notifyDataSetChanged();

            }
        });
        saveRecyclerView.setAdapter(saveAdapter);
    }

    public void backToWelcome(View view){
        startActivity(new Intent(SavedGamesActivity.this, MainActivity.class));
    }
}
