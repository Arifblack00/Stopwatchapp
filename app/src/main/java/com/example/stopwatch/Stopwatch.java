package com.example.stopwatch;

import android.content.Context;

public class Stopwatch implements Runnable {

    public static final long msec_to_min = 60000;
    public static final long msec_to_hour = 3600000;

    private Context sCon;
    private long tStart;
    private boolean tRunning;

    public Stopwatch(Context context) {

        sCon = context;
    }

    public void start() {
        tStart = System.currentTimeMillis();
        tRunning = true;
    }
    public void stop() {

        tRunning = false;
    }

    @Override
    public void run() {
        while (tRunning){
            long since = System.currentTimeMillis() - tStart;

            int hour = (int) (since / msec_to_hour);
            int min = (int) ((since / msec_to_min) % 60);
            int sec = (int) (since / 1000) % 60;
            int mil = (int)  since % 1000;

            ((MainActivity) sCon).newTview(String.format(
                    "%02d:%02d:%02d:%03d", hour, min, sec, mil
            ));
        }
    }
}
