package com.example.microwave.project_velox;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Microwave on 9/19/2015.
 */
public class Helper {
    public static void toaster(Context context, String message, boolean isShort) {
        if (isShort) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
