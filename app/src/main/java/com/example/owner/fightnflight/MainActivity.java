package com.example.owner.fightnflight;

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
import android.widget.Toast;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleApiClient client; //client that provides GPS Services
    private static final String TAG = "MainActivity"; //ignore this
    private Location location; //the location!
    private LocationRequest request = new LocationRequest(); //requests a location
    private Location locarr[]= new Location[2]; //array of 2 locations for comparison and distance calculation
    private int i =0; //counter for array
    private double velocity= 0.0; //velocity
    private DecimalFormat df = new DecimalFormat("##.##");//formats decimal
    private final static UUID APP_UUID = UUID.fromString("EC7EE5C6-8DDF-4089-AA84-C3396A11CC95");
    private static final int DATA_KEY = 0;
    private static final int SELECT_BUTTON_KEY=0;
    private static final int UP_BUTTON_KEY=1;
    private static final int DOWN_BUTTON_KEY=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildGoogleApiClient();
        df.setRoundingMode(RoundingMode.DOWN);
        request = LocationRequest.create() //creates location request
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1 * 100)        // .1 seconds, in milliseconds
                .setFastestInterval(1 * 100); // .1 second, in milliseconds
        PebbleKit.startAppOnPebble(getApplicationContext(), APP_UUID);
        sendStringToPebble("Hello World");

    }

    private void sendStringToPebble(String message){

        boolean isConnected = PebbleKit.isWatchConnected(this);
        Toast.makeText(this, "Pebble " + (isConnected ? "is" : "is not") + " connected!", Toast.LENGTH_LONG).show();
        PebbleDictionary dictionary = new PebbleDictionary();
        dictionary.addString(DATA_KEY, message);
        PebbleKit.sendDataToPebble(getApplicationContext(), APP_UUID, dictionary);

        }

    @Override
    protected void onResume() {
        super.onResume();

        boolean isConnected = PebbleKit.isWatchConnected(this);
        Toast.makeText(this, "Pebble " + (isConnected ? "is" : "is not") + " connected!", Toast.LENGTH_LONG).show();
    }

    //builds client
    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection has been suspended");
    }

    public void onConnectionFailed(ConnectionResult b){
        Log.d(TAG, "Connection Failed :(");
    }

    public void onConnected(Bundle connectionHint) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(client);
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
            }
            else {
                update();
            }
        }


    public void update(){
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
        location = LocationServices.FusedLocationApi.getLastLocation(client);
        TextView t = (TextView)findViewById(R.id.rohan);
        TextView t1 = (TextView)findViewById(R.id.rohan2);
        t.setText(String.valueOf("Latitude: " + location.getLatitude()));
        t1.setText(String.valueOf("Longitude" + location.getLongitude()));


    }
    private void handleNewLocation(Location location) {
        Log.d(TAG,location.toString());
        locarr[i]= location;

        if (i>=1)
        {
            double R = 6371000; // meters
            double φ1 = Math.toRadians(locarr[i-1].getLongitude());
            double φ2 = Math.toRadians(locarr[i].getLongitude());
            double Δφ = Math.toRadians(locarr[i].getLongitude()-locarr[i-1].getLongitude());
            double Δλ = Math.toRadians(locarr[i].getLongitude()-locarr[i-1].getLongitude());

            double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                    Math.cos(φ1) * Math.cos(φ2) *
                            Math.sin(Δλ/2) * Math.sin(Δλ/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            double d = R * c;


            velocity = (Math.pow(10,6)*3600*d)/(((locarr[i].getElapsedRealtimeNanos())-(locarr[i-1].getElapsedRealtimeNanos())));
            locarr[0]=locarr[1];
            i=0;

        }
        i++;
        TextView LatitudeText = (TextView) findViewById(R.id.rohan);
        TextView LongitudeText = (TextView) findViewById(R.id.rohan2);
        LatitudeText.setText("Latitude: " + String.valueOf(location.getLatitude()));
        LongitudeText.setText("Longitude" + String.valueOf(location.getLongitude()));
        TextView VelocityText = (TextView) findViewById(R.id.rohan3);
        VelocityText.setText("Velocity: " + df.format(velocity) + "MPH");

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

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);

    }

    public double getVelocity(){
        return velocity;
    }

    public String getLocationCoordinates() {

        return String.valueOf(location.getLatitude()) + ", " +  String.valueOf(location.getLongitude());
    }





}

