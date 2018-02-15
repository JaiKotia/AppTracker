package com.self;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    public static ArrayList<String> appList = new ArrayList<>();
    public static int color_type;

    public static Context context;
    public float startTimer, endTimer, screenOnTime;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

//        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_PACKAGE_ADDED));
//        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_PACKAGE_REMOVED));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(appList);
        recyclerView.setAdapter(adapter);
    }

    public static MainActivity getInstance(){
        return instance;
    }


    public void updateAppStatus(final String appinfo) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                appList.add(appinfo);
                adapter.notifyDataSetChanged();
            }
        });
    }

    BroadcastReceiver mybroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                startTimer = System.currentTimeMillis();
                Toast.makeText(MainActivity.context, "Screen On", Toast.LENGTH_LONG).show();
            }
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                endTimer = System.currentTimeMillis();
                screenOnTime=endTimer-startTimer;
                Toast.makeText(MainActivity.context, "Screen Off", Toast.LENGTH_LONG).show();
            }

        }
    };

}
