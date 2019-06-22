package com.example.gtraderprototype.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.R;
import com.example.gtraderprototype.viewmodels.ConfigurationViewModel;

import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewmodel;

    private TextView remainingPoints;
    private EditText playerName;
    private Spinner difficultySpinner;

    /* allocated point vlaues for the individual skills */
    private TextView pilotPoints;
    private TextView fighterPoints;
    private TextView traderPoints;
    private TextView engineerPoints;


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
    private int concurrentPoints = 16;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_configuration);
        playerName = findViewById(R.id.playerName);
        remainingPoints = findViewById(R.id.remainingPoints);
        pilotPoints = findViewById(R.id.pilotPoints);
        engineerPoints = findViewById(R.id.engineerPoints);
        fighterPoints = findViewById(R.id.fighterPoints);
        traderPoints = findViewById(R.id.traderPoints);
        removePilot = findViewById(R.id.removePilot);
        removeEngineer = findViewById(R.id.removeEngineer);
        removeFighter = findViewById(R.id.removeFighter);
        removeTrader = findViewById(R.id.removeTrader);
        addPilot = findViewById(R.id.addPilot);
        addEngineer = findViewById(R.id.addEngineer);
        addFighter = findViewById(R.id.addFighter);
        addTrader = findViewById(R.id.addTrader);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        back = findViewById(R.id.back);
        create = findViewById(R.id.create);

        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(Difficulty.values()));
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        viewmodel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    public void decrementSkillPointsForPilot(View view) {
        if (canDecrement(pilotPoints)) {
            int pointsAllocated = Integer.parseInt(pilotPoints.getText().toString());
            pilotPoints.setText("" + (pointsAllocated - 1));
            incrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForEngineer(View view) {
        if (canDecrement(engineerPoints)) {
            int pointsAllocated = Integer.parseInt(engineerPoints.getText().toString());
            engineerPoints.setText("" + (pointsAllocated - 1));
            incrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForFighter(View view) {
        if (canDecrement(fighterPoints)) {
            int pointsAllocated = Integer.parseInt(fighterPoints.getText().toString());
            fighterPoints.setText("" + (pointsAllocated - 1));
            incrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "The lowest point allocation is 0", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForTrader(View view) {
        if (canDecrement(traderPoints)) {
            int pointsAllocated = Integer.parseInt(traderPoints.getText().toString());
            traderPoints.setText("" + (pointsAllocated - 1));
            incrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForPilot(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(pilotPoints.getText().toString());
            pilotPoints.setText("" + (pointsAllocated + 1));
            decrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForEngineer(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(engineerPoints.getText().toString());
            engineerPoints.setText("" + (pointsAllocated + 1));
            decrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForFighter(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(fighterPoints.getText().toString());
            fighterPoints.setText("" + (pointsAllocated + 1));
            decrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForTrader(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(traderPoints.getText().toString());
            traderPoints.setText("" + (pointsAllocated + 1));
            decrementRemainingSkillPoints(view);
        } else {
            Toast.makeText(ConfigurationActivity.this, "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }


    public void decrementRemainingSkillPoints(View view) {
        concurrentPoints--;
        remainingPoints.setText("Remaining Skill points: " + (concurrentPoints));
    }

    public void incrementRemainingSkillPoints(View view) {
        concurrentPoints++;
        remainingPoints.setText("Remaining Skill points: " + (concurrentPoints));
    }

    public boolean canDecrement(TextView skillPoints) {
        boolean checkSkillIsNot0 = Integer.parseInt(skillPoints.getText().toString()) != 0;
        return getAllocatedSkillPoints() > 0 && checkSkillIsNot0;
    }

    public boolean doSkillPointsRemainUnallocated() {
        return getAllocatedSkillPoints() < 16;
    }

    public int getAllocatedSkillPoints() {
        int pointsAllocatedForPilot = Integer.parseInt(pilotPoints.getText().toString());
        int pointsAllocatedForEngineer = Integer.parseInt(engineerPoints.getText().toString());
        int pointsAllocatedForFighter = Integer.parseInt(fighterPoints.getText().toString());
        int pointsAllocatedForTrader = Integer.parseInt(traderPoints.getText().toString());
        int pointsAllocatedTotal = pointsAllocatedForPilot + pointsAllocatedForEngineer + pointsAllocatedForFighter + pointsAllocatedForTrader;
        return pointsAllocatedTotal;
    }

    public void loadMainMenu(View view) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigurationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createPlayer(View view) {
        if (!doSkillPointsRemainUnallocated()) {
            String playerNamevar = playerName.getText().toString();
            int pointsAllocatedForPilot = Integer.parseInt(pilotPoints.getText().toString());
            int pointsAllocatedForEngineer = Integer.parseInt(engineerPoints.getText().toString());
            int pointsAllocatedForFighter = Integer.parseInt(fighterPoints.getText().toString());
            int pointsAllocatedForTrader = Integer.parseInt(traderPoints.getText().toString());
            Difficulty difficulty = (Difficulty) difficultySpinner.getSelectedItem();
            Player player = new Player(playerNamevar, pointsAllocatedForPilot, pointsAllocatedForEngineer, pointsAllocatedForFighter, pointsAllocatedForTrader);
            viewmodel.newGame(player, difficulty, this);
            setContentView(R.layout.activity_spaceport);
            create = findViewById(R.id.create);
        } else {
            Toast.makeText(ConfigurationActivity.this, "all of your skill points have not been allocated.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * configures new page
     * @param view
     */
    public void loadConfigurationPage(View view) {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigurationActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }
}
