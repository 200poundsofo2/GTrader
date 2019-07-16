package com.example.gtraderprototype.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.SkillsViewModel;

class SkillsFragment extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        View v = inflater.inflate(R.layout.fragment_skills, container, false);

        SkillsViewModel player = ViewModelProviders.of(this).get(SkillsViewModel.class);

        TextView playerName = v.findViewById(R.id.welcomePlayer);
        playerName.setText(getString(R.string.welcome) +
                player.getName());

        return v;
    }
}
