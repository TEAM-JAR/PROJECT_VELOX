package com.example.microwave.project_velox;

import android.app.Activity;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Owner on 9/19/2015.
 */
public class VelocityUpdater extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleApiClient client; //client that provides GPS Services
    private static final String TAG = "MainActivity"; //ignore this
    private Location location; //the location!
    private LocationRequest request = new LocationRequest(); //requests a location
    private Location locarr[]= new Location[2]; //array of 2 locations for comparison and distance calculation
    private int i =0; //counter for array
    private double velocity= 0.0; //velocity
    private DecimalFormat df = new DecimalFormat("##.##");//formats decimal
    //builds client


    //call this on initialization
    public void start() {
        setDecimalClipper();
        setLocationRequest();
        buildGoogleApiClient();

    }

    //builds client
    protected synchronized void buildGoogleApiClient(){
        System.out.println("2" + new GoogleApiClient.Builder(this));
        System.out.println("3" + new GoogleApiClient.Builder(this).addConnectionCallbacks(this));
        System.out.println("4" + new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this));
        System.out.println("5" + new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API));
        System.out.println("fuckyou" + new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build());

        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    //edits decimal length
    public void setDecimalClipper() {
        df.setRoundingMode(RoundingMode.DOWN);
    }

    //creates call for location
    public void setLocationRequest(){
        request = LocationRequest.create() //creates location request
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1 * 100)        // .1 seconds, in milliseconds
                .setFastestInterval(1 * 100); // .1 second, in milliseconds

    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection has been suspended");
    }

    public void onConnectionFailed(ConnectionResult b){
        Log.d(TAG, "Connection Failed :(");
    }

    //functions as action when connected
    public void onConnected(Bundle connectionHint) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(client);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
        }
        else {
            update();
        }
    }

    //gets first location
    public void update(){
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
        location = LocationServices.FusedLocationApi.getLastLocation(client);

    }

    //handles the rest of the locations
    private void handleNewLocation(Location location) {

        locarr[i]= location;

        if (i>=1)
        {
            double R = 6371000; // meters
            double lat1 = Math.toRadians(locarr[i-1].getLatitude());
            double lat2 = Math.toRadians(locarr[i].getLatitude());
            double longdiff = Math.toRadians(locarr[i].getLongitude()-locarr[i-1].getLongitude());
            double latdiff = Math.toRadians(locarr[i].getLatitude()-locarr[i-1].getLatitude());

            double a = Math.sin(latdiff/2) * Math.sin(latdiff/2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(longdiff/2) * Math.sin(longdiff/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            double d = R * c;


            /**
             * WTF Rowan
             */
            velocity = (Math.pow(10,6)*3600*d)/(((locarr[i].getElapsedRealtimeNanos())-(locarr[i-1].getElapsedRealtimeNanos())));
            locarr[0]=locarr[1];
            i=0;

        }
        i++;

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
    protected void onPause() {
        super.onPause();
        if (client.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
            client.disconnect();
        }
    }
    //what to do when locations change
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);

    }
    //gets velocity
    public double getVelocity(){
        return velocity;
    }

    //gets coordinates
    public String getLocationCoordinates() {

        return String.valueOf(location.getLatitude()) + ", " +  String.valueOf(location.getLongitude());
    }

}