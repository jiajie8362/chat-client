package com.jiajie.guessmusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {
    private Animation panAnim;
    private LinearInterpolator panLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        panAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        panLin = new LinearInterpolator();
        panAnim.setInterpolator(panLin);
        panAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
