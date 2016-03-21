package com.jiajie.guessmusic.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by jiajie on 16/3/17.
 */
public class Loading extends ImageView {
    private Animation animation;

    public Loading(Context context) {
        super(context);

        init();
    }

    public Loading(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        animation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1700);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
    }

    public void start() {
        startAnimation(animation);
    }

    public void stop() {
        clearAnimation();
    }
}
