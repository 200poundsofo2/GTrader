package com.example.gtraderprototype.views;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.entity.Player;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.ConfigurationViewModel;

import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    private TextView remainingPoints;
    private EditText playerName;
    private Spinner difficultySpinner;

    /* allocated point values for the individual skills */
    private TextView pilotPoints;
    private TextView fighterPoints;
    private TextView traderPoints;
    private TextView engineerPoints;

    private int concurrentPoints = 16;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_configuration);

        //this section activates the background gif
        ImageView imgView = findViewById(R.id.backgroundImageConfig);

        if (imgView != null) {
            AnimationDrawable progressAnimation = (AnimationDrawable) imgView.getBackground();
            progressAnimation.start();
        } else {
            System.out.println("Img view is null");
        }


        playerName = findViewById(R.id.playerName);
        remainingPoints = findViewById(R.id.remainingPoints);
        pilotPoints = findViewById(R.id.pilotPoints);
        engineerPoints = findViewById(R.id.engineerPoints);
        fighterPoints = findViewById(R.id.fighterPoints);
        traderPoints = findViewById(R.id.traderPoints);
        difficultySpinner = findViewById(R.id.difficultySpinner);

        ArrayAdapter<Difficulty> difficultyAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        Arrays.asList(Difficulty.values()));
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    public void decrementSkillPointsForPilot(View view) {
        if (canDecrement(pilotPoints)) {
            int pointsAllocated = Integer.parseInt(pilotPoints.getText().toString());
            pilotPoints.setText(getString(R.string.points, pointsAllocated - 1));
            incrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForEngineer(View view) {
        if (canDecrement(engineerPoints)) {
            int pointsAllocated = Integer.parseInt(engineerPoints.getText().toString());
            engineerPoints.setText(getString(R.string.points, pointsAllocated - 1));
            incrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForFighter(View view) {
        if (canDecrement(fighterPoints)) {
            int pointsAllocated = Integer.parseInt(fighterPoints.getText().toString());
            fighterPoints.setText(getString(R.string.points, pointsAllocated - 1));
            incrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "The lowest point allocation is 0", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementSkillPointsForTrader(View view) {
        if (canDecrement(traderPoints)) {
            int pointsAllocated = Integer.parseInt(traderPoints.getText().toString());
            traderPoints.setText(getString(R.string.points, pointsAllocated - 1));
            incrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "The lowest point allocation is 0.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForPilot(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(pilotPoints.getText().toString());
            pilotPoints.setText(getString(R.string.points, pointsAllocated + 1));
            decrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForEngineer(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(engineerPoints.getText().toString());
            engineerPoints.setText(getString(R.string.points, pointsAllocated + 1));
            decrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForFighter(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(fighterPoints.getText().toString());
            fighterPoints.setText(getString(R.string.points, pointsAllocated + 1));
            decrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementSkillPointsForTrader(View view) {
        if (doSkillPointsRemainUnallocated()) {
            int pointsAllocated = Integer.parseInt(traderPoints.getText().toString());
            traderPoints.setText(getString(R.string.points, pointsAllocated + 1));
            decrementRemainingSkillPoints();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "You are out of skill points to spend.", Toast.LENGTH_LONG).show();
        }
    }

    private void decrementRemainingSkillPoints() {
        concurrentPoints--;
        remainingPoints.setText("Remaining Points: "
                + getString(R.string.points, concurrentPoints));
    }

    private void incrementRemainingSkillPoints() {
        concurrentPoints++;
        remainingPoints.setText("Remaining Points: "
                + getString(R.string.points, concurrentPoints));
    }

    private boolean canDecrement(TextView skillPoints) {
        boolean checkSkillIsNot0 = Integer.parseInt(skillPoints.getText().toString()) != 0;
        return getAllocatedSkillPoints() > 0 && checkSkillIsNot0;
    }

    private boolean doSkillPointsRemainUnallocated() {
        return getAllocatedSkillPoints() < 16;
    }

    private int getAllocatedSkillPoints() {
        int pointsForPilot = Integer.parseInt(pilotPoints.getText().toString());
        int pointsForEngineer = Integer.parseInt(engineerPoints.getText().toString());
        int pointsForFighter = Integer.parseInt(fighterPoints.getText().toString());
        int pointsForTrader = Integer.parseInt(traderPoints.getText().toString());
        return pointsForPilot + pointsForEngineer + pointsForFighter + pointsForTrader;
    }

    public void loadMainMenu(View view) {
        startActivity(new Intent(ConfigurationActivity.this, MainActivity.class));
    }

    public void createPlayer(View view) {
        if (!doSkillPointsRemainUnallocated()) {
            String name = playerName.getText().toString();
            int pointsPilot = Integer.parseInt(pilotPoints.getText().toString());
            int pointsEngineer = Integer.parseInt(engineerPoints.getText().toString());
            int pointsFighter = Integer.parseInt(fighterPoints.getText().toString());
            int pointsTrader = Integer.parseInt(traderPoints.getText().toString());
            Difficulty difficulty = (Difficulty) difficultySpinner.getSelectedItem();
            Player player = new Player((name.equals("")) ? "Default" : name, pointsPilot, pointsEngineer, pointsFighter, pointsTrader);
            Log.d("GTrader", "Setting up player |"+name+"|");
            Model.getInstance().getPlayerInteractor().setPlayer(player);
            viewModel.newGame(difficulty, Model.getInstance().getPlayerInteractor().getPlayer(), this);
            loadSpacePortPage();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "all of your skill points have not been allocated.", Toast.LENGTH_LONG).show();
        }
    }

    private void loadSpacePortPage() {
        startActivity(new Intent(ConfigurationActivity.this, SpacePortActivity.class));
    }
}
