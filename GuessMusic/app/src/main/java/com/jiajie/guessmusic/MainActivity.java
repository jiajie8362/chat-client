package com.jiajie.guessmusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jiajie.guessmusic.http.ApiAddress;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private Animation panAnim;
    private LinearInterpolator panLin;

    private Animation barInAnim;
    private LinearInterpolator barInLin;

    private Animation barOutAnim;
    private LinearInterpolator barOutLin;

    @Bean
    ApiAddress apiAddress;

    @ViewById
    ImageButton btnPlayStart;
    @ViewById
    ImageView viewPan;
    @ViewById
    ImageView viewPanBar;

    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void init() {
        btnPlayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlayButton();
            }
        });

        panAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        panLin = new LinearInterpolator();
        panAnim.setInterpolator(panLin);
        panAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewPanBar.startAnimation(barOutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        barInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
        barInLin = new LinearInterpolator();
        barInAnim.setFillAfter(true);
        barInAnim.setInterpolator(barInLin);
        barInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewPan.startAnimation(panAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        barOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
        barOutLin = new LinearInterpolator();
        barOutAnim.setFillAfter(true);
        barOutAnim.setInterpolator(barOutLin);
        barOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isRunning = false;
                btnPlayStart.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void handlePlayButton() {
        if (!isRunning) {
            isRunning = true;
            btnPlayStart.setVisibility(View.INVISIBLE);
            viewPanBar.startAnimation(barInAnim);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        viewPan.clearAnimation();
    }
}
