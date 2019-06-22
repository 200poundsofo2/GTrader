package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtraderprototype.R;

public class SkillsFragment extends Fragment {
    private static final SkillsFragment ourInstance = new SkillsFragment();

    public static SkillsFragment getInstance() {
        return ourInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skills, container, false);
    }
}
