package com.example.microwave.project_velox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class BattleActivity extends AppCompatActivity {

    private BattleScript bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Dungeon first = new Dungeon(1,1,((MyApplication) this.getApplication()).getHero(),
                ((MyApplication) this.getApplication()).getPedometer(),((MyApplication) this.getApplication()).getVU(), (ProgressBar) findViewById(R.id.Action_Bar));
        first.start();

        Intent activityChangeIntent = new Intent(BattleActivity.this, MenuActivity.class);

        // currentContext.startActivity(activityChangeIntent);
        bs = first.newBattle;

        startActivity(activityChangeIntent);
    }

    public void attackClicked(View view) {
        bs.setBattleAction(BattleAction.ATTACK);
    }

    public void defendClicked(View view) {
        bs.setBattleAction(BattleAction.DEFEND);
    }

    public void magicClicked(View view) {
        bs.setBattleAction(BattleAction.MAGIC);
    }

    public void healClicked(View view) {
        bs.setBattleAction(BattleAction.HEAL);
    }

    public void escapeClicked(View view) {
        bs.setBattleAction(BattleAction.ESCAPE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_battle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
