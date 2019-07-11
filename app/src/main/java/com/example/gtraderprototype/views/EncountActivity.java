package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gtraderprototype.R;

public class EncountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
    }

    public void closeApp(View view) {
        finish();
        moveTaskToBack(true);
    }
}
