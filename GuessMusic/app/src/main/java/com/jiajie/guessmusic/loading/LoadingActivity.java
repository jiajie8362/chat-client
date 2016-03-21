package com.jiajie.guessmusic.loading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.jiajie.guessmusic.GuessMusicApplication;
import com.jiajie.guessmusic.MainActivity_;
import com.jiajie.guessmusic.R;
import com.jiajie.guessmusic.models.UserController_;

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
        UserController_.getInstance_(GuessMusicApplication.applicationContext()).signIn("CAACEdEose0cBAB0bE0SqFI4ZB4kJ5CI0VprDyRRuL1eiIDoDMwXdAseIBVeq8mWEbNXJmHQwZC3JQnYHItr3NZB8gzid7UjlZB3ZBqzxCSqCdboOf3ozmY0ZBPJUy7jLevbMgf3CQ998fwJe3QMRqAOgDQOq9Mxs24bYJVw1I1jDtqmbF1Gv3MVq8jpjS3VbzpNqzuJ9IDWQZDZD");

//        if (SignInController.isSignIn()) {
            //MainActivity_.start(this);
//        } else {
//            SignInActivity_.start(this);
//        }
    }

    private void startMain() {
        MainActivity_.intent(this).start();
    }

}
