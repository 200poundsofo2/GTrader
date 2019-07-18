package com.example.gtraderprototype.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Difficulty;
import com.example.gtraderprototype.model.Model;
import com.example.gtraderprototype.viewmodels.ConfigurationViewModel;

import java.util.Arrays;

/**
 * the configuration page
 */
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

    /**
     * decrements the point value for pilot
     * @param view android stuff
     */
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
    /**
     * decrements the point value for engineer
     * @param view android stuff
     */
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
    /**
     * decrements the point value for fighter
     * @param view android stuff
     */
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
    /**
     * decrements the point value for Trader
     * @param view android stuff
     */
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
    /**
     * increments the point value for pilot
     * @param view android stuff
     */
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
    /**
     * increments the point value for engineer
     * @param view android stuff
     */
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
    /**
     * increments the point value for fighter
     * @param view android stuff
     */
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
    /**
     * increments the point value for trader
     * @param view android stuff
     */
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

    /**
     * decrements the remaining skill points from the concurrent skill points
     */
    private void decrementRemainingSkillPoints() {
        concurrentPoints--;
        remainingPoints.setText(String.format("%s%s", getString(R.string.remaining_points),
                getString(R.string.points, concurrentPoints)));
    }
    /**
     * increments the remaining skill points from the concurrent skill points
     */
    private void incrementRemainingSkillPoints() {
        concurrentPoints++;
        remainingPoints.setText(String.format("%s%s", getString(R.string.remaining_points),
                getString(R.string.points, concurrentPoints)));
    }

   /**
     * checks if any skill points remain to be allocated
     * @param skillPoints skill points
     * @return true if the skill is not equal to zero
    * and there are points to be allocated false otherwise
     */
    private boolean canDecrement(TextView skillPoints) {
        boolean checkSkillIsNot0 = Integer.parseInt(skillPoints.getText().toString()) != 0;
        return (getAllocatedSkillPoints() > 0) && checkSkillIsNot0;
    }
    /**
     * checks if any skill points remain to be allocated
     * @return true if the net skill points is less than 16
     */
    private boolean doSkillPointsRemainUnallocated() {
        int mostSkillPoints = 16;
        return getAllocatedSkillPoints() < mostSkillPoints;
    }

    /**
     * get the net skill points from the summation of all skill allocated to
     * @return met skill points
     */
    private int getAllocatedSkillPoints() {
        int pointsForPilot = Integer.parseInt(pilotPoints.getText().toString());
        int pointsForEngineer = Integer.parseInt(engineerPoints.getText().toString());
        int pointsForFighter = Integer.parseInt(fighterPoints.getText().toString());
        int pointsForTrader = Integer.parseInt(traderPoints.getText().toString());
        return pointsForPilot + pointsForEngineer + pointsForFighter + pointsForTrader;
    }

    /**
     * loads the main menu via button press
     * @param view android stuff
     */
    public void loadMainMenu(View view) {
        startActivity(new Intent(ConfigurationActivity.this, MainActivity.class));
    }

    /**
     * creates a player
     * @param view android stuff
     */
    public void createPlayer(View view) {
        if (!doSkillPointsRemainUnallocated()) {
            String name = playerName.getText().toString();
            int pointsPilot = Integer.parseInt(pilotPoints.getText().toString());
            int pointsEngineer = Integer.parseInt(engineerPoints.getText().toString());
            int pointsFighter = Integer.parseInt(fighterPoints.getText().toString());
            int pointsTrader = Integer.parseInt(traderPoints.getText().toString());
            Difficulty difficulty = (Difficulty) difficultySpinner.getSelectedItem();

            Log.d("GTrader", "Setting up player |"+name+"|");
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setName(("".equals(name)) ? "Default" : name);
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setEngineerSkillPoints(pointsEngineer);
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setPilotSkillPoints(pointsPilot);
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setFighterSkillPoints(pointsFighter);
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setTraderSkillPoints(pointsTrader);
            Model.getInstance().getPlayerInteractor()
                    .getPlayer().setDifficulty(difficulty);
            viewModel.newGame(difficulty, this);
            loadSpacePortPage();
        } else {
            Toast.makeText(ConfigurationActivity.this,
                    "all of your skill points have not been allocated.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * loads the space port via button press
     */
    private void loadSpacePortPage() {
        startActivity(new Intent(ConfigurationActivity.this, SpacePortActivity.class));
    }
}
