package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.Layout;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicLong;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    LinearLayout buttons;
    LinearLayout chronometerContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.button);
        buttons = findViewById(R.id.buttonContainer);





        addButton.setOnClickListener(v -> {

            AtomicLong timeWhenStopped = new AtomicLong();
            Chronometer chronometer = new Chronometer(this);
            chronometer.setTextSize(24);

            Button bStart = new Button(this);
            bStart.setText("Start");
            bStart.setOnClickListener(v1 -> {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped.get());
                chronometer.start();
            });
            Button bStop = new Button(this);
            bStop.setText("Stop");
            bStop.setOnClickListener(v1 -> {
                chronometer.stop();
                timeWhenStopped.set(chronometer.getBase() - SystemClock.elapsedRealtime());
            });
            Button bReset = new Button(this);
            bReset.setText("Reset");
            bReset.setOnClickListener(v1 -> {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
            });

            LinearLayout chronometerButtons = new LinearLayout(this);
            chronometerButtons.setOrientation(LinearLayout.HORIZONTAL);
            chronometerButtons.addView(bStart);
            chronometerButtons.addView(bStop);
            chronometerButtons.addView(bReset);

            chronometerContainer = new LinearLayout(this);
            chronometerContainer.setOrientation(LinearLayout.VERTICAL);
            chronometerContainer.addView(chronometer);
            chronometerContainer.addView(chronometerButtons);

            buttons.addView(chronometerContainer);
        });

    }



}