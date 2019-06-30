package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Item;

import java.util.Arrays;

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
