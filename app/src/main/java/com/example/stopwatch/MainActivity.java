package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tmCount;
    private Button btStarter;
    private Button btLap;
    private Button btStp;
    private EditText swLaps;
    private int lapCount = 1;

    private Context sCon;
    private Stopwatch sWatch;
    private Thread watchThread;
    private ScrollView lapScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sCon = this;

        swLaps = findViewById(R.id.ed_txt);
        tmCount = findViewById(R.id.time_count);
        btStarter = findViewById(R.id.Start_Reset);
        btLap = findViewById(R.id.lapp_button);
        btStp = findViewById(R.id.Stop_butt);
        lapScroll = findViewById(R.id.lap_view);
        swLaps.setEnabled(false);


        btStarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button is clicked
                if (sWatch == null){
                    sWatch = new Stopwatch(sCon);
                    watchThread = new Thread(sWatch);
                    watchThread.start();
                    sWatch.start();
                    lapCount = 1;
                    swLaps.setText("");
                }
            }
        });
        btStp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sWatch!= null){
                    sWatch.stop();
                    watchThread.interrupt();
                    watchThread = null;
                    sWatch = null;
                }
            }
        });
        btLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sWatch == null ){
                    return;
                }

                swLaps.append("LAP " + (lapCount) + " " + (tmCount.getText()) + "\n");
                lapCount++;

                lapScroll.post(new Runnable() {
                    @Override
                    public void run() {
                        lapScroll.smoothScrollTo(0, swLaps.getBottom());
                    }
                });
            }

        });
    }
    public void newTview (final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tmCount.setText(time);
            }
        });
    }
}

