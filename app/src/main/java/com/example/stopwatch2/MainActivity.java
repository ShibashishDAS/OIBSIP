package com.example.stopwatch2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;
    private final Handler handler = new Handler();
    private boolean isRunning = false;
    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button resetButton = findViewById(R.id.resetButton);
        timeTextView = findViewById(R.id.timeTextView);

        startButton.setOnClickListener(v -> startStopwatch());

        stopButton.setOnClickListener(v -> stopStopwatch());

        resetButton.setOnClickListener(v -> resetStopwatch());
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            updateTimer();
            handler.postDelayed(this, 1000);
        }
    };

    private void startStopwatch() {
        if (!isRunning) {
            handler.post(runnable);
            isRunning = true;
        }
    }

    private void stopStopwatch() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            isRunning = false;
        }
    }

    private void resetStopwatch() {
        seconds = 0;
        updateTimer();
        stopStopwatch();
    }

    private void updateTimer() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timeTextView.setText(time);
    }
}
// this basically a code that is used to display the stopwatch functionality.
// made by Shibashish Das intern at Oasis infobyte.
//Thank you.
//so here this was the basic overview for the code and the preview of the app
//here the format of time used is HH:MM:SS
//thank you for this .