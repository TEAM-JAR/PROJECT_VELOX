package com.example.microwave.project_velox;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MenuActivity extends Activity {

    private Pedometer pm;

    public static final int[] LEVEL_XP_CAPS = new int[] {
            10, 20, 40, 80
    };
    public static final int[] RANK_XP_CAPS = new int[] {
            30, 60, 90, 120
    };
    public static final int[] LEVEL_PACES_CAPS = new int[] {
            10, 20, 30, 40
    };

    private int pacesCounter;
    private int pacesLimit;
    private int level;
    private int lvlXP;
    private int lvlXPCap;
    public static final String LEVEL_TEXT = "Level ";
    private int rank;
    private int rkXP;
    private int rkXPCap;
    public static final String RANK_TEXT = "Rank ";

    private TextView _pacesCounter;
    private TextView _pacesLimit;
    private TextView _level;
    private TextView _rank;

    private ProgressBar _pacesBar;
    private ProgressBar _levelBar;
    private ProgressBar _rankBar;

    private Handler updateHandler;

    public static final String LEVEL_UP_MESSAGE = "Congratulations, you just advanced to level ";
    public static final String RANK_UP_MESSAGE = "Congratulations, you just advanced a rank ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Fight and Flight");
        getActionBar().setIcon(R.drawable.boot);
        initializeInstanceVars();
        linkWithUI();
        initializeHandler();
    }

    public void adventureClicked(View view) {
        Intent activityChangeIntent = new Intent(MenuActivity.this, BattleActivity.class);

        // currentContext.startActivity(activityChangeIntent);

        startActivity(activityChangeIntent);
    }

    private void linkWithUI() {
        _pacesCounter = (TextView) findViewById(R.id.Paces_Counter);
        _pacesLimit = (TextView) findViewById(R.id.Total_Paces_Limit);
        _level = (TextView) findViewById(R.id.Level_Text);
        _rank = (TextView) findViewById(R.id.Rank_Text);

        _pacesBar = (ProgressBar) findViewById(R.id.Paces_Bar);
        _levelBar = (ProgressBar) findViewById(R.id.Level_Bar);
        _rankBar = (ProgressBar) findViewById(R.id.Rank_Bar);
    }

    private void initializeInstanceVars() {
        pm = Global.pm;
        this.pm.setContext(getApplicationContext());
        pm.resume();

        this.pacesCounter = 0;
        this.pacesLimit = 15;
        this.level = 1;
        this.rank = 1;
        this.lvlXP = 0;
        this.rkXP = 0;
        this.lvlXPCap = 5;
        this.rkXPCap = 10;
    }

    /**
     * hello judges
     */
    public void initializeHandler() {
        updateHandler = new Handler();
        updateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /* do what you need to do */
                updateInstanceFields();
                updateUI();
                /* and here comes the "trick" */
                updateHandler.postDelayed(this, 100);
            }
        }, 100);
    }

    public void updateInstanceFields() {
        if (!inLimits()) {
            pm.stop();
        } else {
            pm.resume();
        }
        this.pacesCounter = pm.getSteps();

        //game determined amounts
//        this.pacesLimit = 15;
//        this.level = 1;
//        this.rank = 2;
//        this.lvlXP = 50;
//        this.rkXP = 60;

        this.lvlXP = pacesCounter;
        this.rkXP = pacesCounter;

        if (canLevelUp()) {
            levelUp();
        }
        if (canRankUp()) {
            rankUp();
        }
    }

    public void updatePacesCounter() {
        System.out.println(_pacesCounter);
        System.out.println(pm);
        _pacesCounter.setText("" + pm.getSteps());
    }

    public void updatePacesCounterBar() {
        _pacesBar.setProgress(pacesCounter);
    }

    public void updatePacesLimit() {
        _pacesLimit.setText("" + pacesLimit);
    }

    public void updateLevel() {
        _level.setText(LEVEL_TEXT + level);
    }

    public void updateLevelBar() {
        _levelBar.setMax(lvlXPCap);
        _levelBar.setProgress(lvlXP);
    }

    public void updateRank() {
        _rank.setText(RANK_TEXT + rank);
    }

    public void updateRankBar() {
        _rankBar.setMax(rkXPCap);
        _rankBar.setProgress(rkXP);
    }

    public boolean canLevelUp() {
        return lvlXP == lvlXPCap;
    }

    public boolean canRankUp() {
        return rkXP == rkXPCap;
    }

    public void levelUp() {
        generateLevelUpToast();
        level++;
        lvlXP = 0;
        lvlXPCap = LEVEL_XP_CAPS[level - 1];

    }

    public void rankUp() {
        generateRankUpToast();
        rank++;
        rkXP = 0;
        rkXPCap = RANK_XP_CAPS[level - 1];
        pacesLimit = LEVEL_PACES_CAPS[level - 1];
    }

    private void toaster(String message) {
        Helper.toaster(getBaseContext(), message, true);
    }

    public void updateUI() {
        updatePacesCounter();
        updatePacesCounterBar();
        updatePacesLimit();
        updateLevel();
        updateLevelBar();
        updateRank();
        updateRankBar();
    }

    public boolean inLimits() {
        return pm.getSteps() < pacesLimit;
    }

    public void lvluphit(View view) {
        //levelUp();
        pm.spendSteps(10);
    }

    public void rkuphit(View view) {
        //rankUp();
        rkXP++;
    }

    public void generateLevelUpToast() {
        toaster(LEVEL_UP_MESSAGE + level + " with " + lvlXPCap + " experience!");
    }

    public void generateRankUpToast() {
        toaster(RANK_UP_MESSAGE + rank + " with " + rkXPCap + " prestige!");
    }
}
