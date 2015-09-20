package com.example.owner.fightnflight;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

/**
 * Created by Owner on 9/20/2015.
 */
public class pebbletester extends Activity {

//use the same UUID as on the watch
private static final UUID APP_UUID = UUID.fromString("fldb3db2-0b0e-47c1-86af-e28d5ac7767c");
private static final int DATA_KEY = 0;
private static final int SELECT_BUTTON_KEY=0;
private static final int UP_BUTTON_KEY=1;
private static final int DOWN_BUTTON_KEY=2;


private void sendStringToPebble(String message){


        PebbleDictionary dictionary = new PebbleDictionary();
        dictionary.addString(DATA_KEY, message);
        PebbleKit.sendDataToPebble(getApplicationContext(),APP_UUID,dictionary);


}


}
