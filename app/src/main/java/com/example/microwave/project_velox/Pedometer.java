package com.example.microwave.project_velox;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

/**
 * Created by Microwave on 9/19/2015.
 */
public class Pedometer implements SensorEventListener {
    private Context context;

    private int sensorCount;
    private int steps;
    private boolean isTracking;

    public static final double STEPS_CORRECTION = 0.5;

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mStepDetectorSensor;

    /**
     * Needs context to get system services
     */
    public Pedometer(Context context) {
        steps = 0;

        mSensorManager = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            this.sensorCount = Sensor.TYPE_STEP_COUNTER;
            this.steps++;
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // For test only. Only allowed value is 1.0 i.e. for step taken
            //textView.setText("Step Detector Detected : " + value);
        }
    }

    /**
     * Pedometer shit
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * Pedometer shit
     */
    public void resume() {
        isTracking = true;
        mSensorManager.registerListener(this, mStepCounterSensor,

                SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,

                SensorManager.SENSOR_DELAY_FASTEST);

    }

    /**
     * Pedometer shit
     */
    public void stop() {
        isTracking = false;
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    public int getSensorCount() {
        return sensorCount;
    }

    public void reset() {
        steps = 0;
    }

    public int getSteps() {
        return steps;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public boolean spendSteps(int amount) {
        if (steps - amount >= 0) {
            steps -= amount;
            return true;
        } else {
            return false;
        }
    }
}
