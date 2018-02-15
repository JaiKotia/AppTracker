package com.self;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hello on 12-02-2018.
 */

public class ScreenService extends BroadcastReceiver {

    public float startTimer, endTimer, screenOnTime;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("[BroadcastReceiver]", "MyReceiver");

        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            startTimer = System.currentTimeMillis();
            Toast.makeText(MainActivity.context, "Broadcast receiver: Screen ON", Toast.LENGTH_LONG).show();
        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            endTimer = System.currentTimeMillis();
            screenOnTime = endTimer - startTimer;
            Toast.makeText(MainActivity.context, "Broadcast receiver: Screen OFF", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.context, "Screen on time is:" + screenOnTime, Toast.LENGTH_LONG).show();
        }

    }
}
