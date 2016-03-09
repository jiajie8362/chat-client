package com.jiajie.guessmusic.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiajie.guessmusic.MainActivity;
import com.jiajie.guessmusic.R;

import java.util.Timer;
import java.util.TimerTask;


public class LoadingActivity extends AppCompatActivity {
    private final int START_MAIN_ACTIVITY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        init();
    }

    private void init() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startMain();
            }
        };
        timer.schedule(task,1000);
    }

    private void startMain(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

}
