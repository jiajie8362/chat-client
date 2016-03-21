package com.jiajie.guessmusic.loading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.jiajie.guessmusic.MainActivity_;
import com.jiajie.guessmusic.R;
import com.jiajie.guessmusic.signin.SignInActivity_;
import com.jiajie.guessmusic.signin.controllers.SignInController;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_loading)
public class LoadingActivity extends AppCompatActivity {
    private final int START_MAIN_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (SignInController.isSignIn()) {
            MainActivity_.start(this);
        } else {
            SignInActivity_.start(this);
        }
    }

    private void startMain() {
        MainActivity_.intent(this).start();
    }

}
