package com.example.microwave.project_velox;

import android.app.Application;
import android.widget.Button;

/**
 * Created by jonathanwu on 9/20/15.
 */
public class MyApplication extends Application {
    private Pedometer p;
    private VelocityUpdater v;
    private Hero hero;

    private void initializePedometer() {
        p = new Pedometer(getBaseContext());
    }
    private void initializeHero(){
        hero=new Hero(1);
    }
    private void initializeVelocityUpdater(){
        v= new VelocityUpdater();
    }

    public Pedometer getPedometer(){
        return this.p;
    }
    public VelocityUpdater getVU(){
        return this.v;
    }
    public Hero getHero(){
        return this.hero;
    }
}
