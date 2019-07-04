package com.example.gtraderprototype.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtraderprototype.R;

public class SkillsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        /*ArrayAdapter<Item> adapter = new ArrayAdapter<>(SpacePortActivity.this, R.layout.fragment_market, Arrays.asList(player.getShip().getCargo()));
        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);*/
        return inflater.inflate(R.layout.fragment_skills, container, false);
    }
}
