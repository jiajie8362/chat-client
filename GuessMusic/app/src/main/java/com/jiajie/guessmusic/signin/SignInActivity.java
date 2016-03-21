package com.jiajie.guessmusic.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jiajie.guessmusic.MainActivity_;
import com.jiajie.guessmusic.R;
import com.jiajie.guessmusic.utils.ui.Loading;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by jiajie on 16/3/17.
 */
@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends AppCompatActivity {
    @ViewById
    LoginButton facebookButton;
    @ViewById
    LinearLayout loadingLayout;
    @ViewById
    Loading loadingIcon;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @AfterViews
    void init() {
        initUI();
        initData();
    }


    void initUI() {
        facebookButton = (LoginButton)findViewById(R.id.facebook_button);
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getApplicationContext(), loginResult.toString(), Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), "onActivityResult", Toast.LENGTH_SHORT);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        MainActivity_.start(this);
    }

    private void initData() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SignInActivity_.class);
        context.startActivity(intent);
    }

}
