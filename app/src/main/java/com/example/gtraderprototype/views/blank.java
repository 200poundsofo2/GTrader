package com.example.gtraderprototype.views;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.MarketViewModel;

public class blank extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_blank, container, false);
        super.onCreate(savedInstanceState);
        return rootView;
    }
}
