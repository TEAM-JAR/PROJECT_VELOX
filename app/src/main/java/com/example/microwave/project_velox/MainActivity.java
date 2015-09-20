package com.example.microwave.project_velox;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private TextView textView;
    private Pedometer pm;
    private Button start;
    private Button stop;
    private Button reset;

    private Handler updateHandler;
    private Runnable updateRunnable;

    public static final double TIMER_UPDATE_RATE = 0.5;
    public static final double MILLISECONDS_TO_SECONDS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.stepsCount_text);
        initializePedometer();
        initializeHandler();
    }

    private void initializePedometer() {
        pm = new Pedometer(getBaseContext());
        pm.stop();
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
    }

    /**
     * Use this to repeat tasks you little shit
     */
    public void initializeHandler() {
        updateHandler = new Handler();
        updateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /* do what you need to do */
                updateTextView();
                /* and here comes the "trick" */
                updateHandler.postDelayed(this, 100);
            }
        }, 100);
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            updateTextView();
        }
    };

    private void updateTextView() {
        textView.setText("" + pm.getSteps());
    }

    private void toaster(String message) {
        Helper.toaster(getBaseContext(), message);
    }

    public void startClicked(View view) {
        pm.resume();
        toaster("Started");
    }

    public void stopClicked(View view) {
        pm.stop();
        toaster("Stopped");
    }

    public void resetClicked(View view) {
        pm.reset();
        toaster("Resetted steps");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
