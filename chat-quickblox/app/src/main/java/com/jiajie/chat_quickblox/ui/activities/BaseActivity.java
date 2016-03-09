package com.jiajie.chat_quickblox.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jiajie on 16/3/8.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private static final String USER_LOGIN_KEY = "USER_LOGIN_KEY";
    private static final String USER_PASSWORD_KEY = "USER_PASSWORD_KEY";

    private boolean sessionActive = false;
    private boolean needToRecreateSession = false;

    private ProgressDialog progressDialog;
    private final Handler handler = new Handler();

    public boolean isSessionActive() {
        return sessionActive;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        if (savedInstanceState != null) {
            needToRecreateSession = true;
        } else {
            sessionActive = true;
        }
    }

    private void showProgressDialog() {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(BaseActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Restoring chat session");
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        }
        progressDialog.show();
    }
}
