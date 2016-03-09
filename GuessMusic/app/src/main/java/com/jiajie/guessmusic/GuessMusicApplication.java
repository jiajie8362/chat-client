package com.jiajie.guessmusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by jiajie on 16/3/9.
 */
public class GuessMusicApplication extends Application {
    private static final String TAG = GuessMusicApplication.class.getSimpleName();
    public static String serverAddress = BuildConfig.DEFAULT_SERVER_ADDRESS;
    private static GuessMusicApplication instance;

    public static GuessMusicApplication getInstance() {
        return instance;
    }

    public static GuessMusicApplication application() {
        return getInstance();
    }

    public static Context applicationContext() {
        return application().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {

    }
}
