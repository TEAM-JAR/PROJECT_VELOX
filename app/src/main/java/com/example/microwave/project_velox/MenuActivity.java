package com.example.microwave.project_velox;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MenuActivity extends Activity {

    private int pacesCounter;
    private int pacesLimit;
    private int level;
    public static final String LEVEL_TEXT = "Level ";
    private int rank;
    public static final String RANK_TEXT = "Rank ";

    private TextView _pacesCounter;
    private TextView _pacesLimit;
    private TextView _level;
    private TextView _rank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Fight and Flight");
        getActionBar().setIcon(R.drawable.boot);
    }

}
