package com.example.gtraderprototype.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.R;

public class ConfigurationActivity extends AppCompatActivity {

    private Player player;
    private TextView remainingPoints;
    private EditText playerName;
    private Spinner difficulty;

    /* buttons */
    private Button removePilot;
    private Button addPilot;
    private Button removeFighter;
    private Button addFighter;
    private Button removeTrader;
    private Button addTrader;
    private Button removeEngineer;
    private Button addEngineer;
    private Button back;
    private Button create;

    /*
    TODO: functions
    - create
    - back
    - minus and plus on each different skill alocation
    - make sure skill alocation does not go over 16 or under
    - be able to store all this data
     */

}
