package com.jiajie.chat_quickblox;

import android.app.Application;
import android.util.Log;

/**
 * Created by jiajie on 16/3/9.
 */
public class ApplicationSingleton extends Application {
    private static final String TAG = ApplicationSingleton.class.getSimpleName();

    public static final String APP_ID = "36105";
    public static final String AUTH_KEY = "8Mt-m89xcSmgWDJ";
    public static final String AUTH_SECRET = "2N6a3hLEkdNGgq8";
    public static final String ACCOUNT_KEY = "qFxCuC8Tp5PLbgpqUSLX";

    public static final String STICKER_API_KEY = "847b82c49db21ecec88c510e377b452c";

    public static final String USER_LOGIN = "chatusr22";
    public static final String USER_PASSWORD = "chatusr22";

    private static ApplicationSingleton instance;

    public static ApplicationSingleton getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        instance = this;


    }
}
