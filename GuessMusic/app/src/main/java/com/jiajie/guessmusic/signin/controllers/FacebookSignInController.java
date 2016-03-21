package com.jiajie.guessmusic.signin.controllers;

import android.app.Activity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import org.androidannotations.annotations.EBean;

/**
 * Created by jiajie on 16/3/17.
 */
@EBean
public class FacebookSignInController {
    private Activity context;

    private CallbackManager callbackManager;

    //    public FacebookSignInController(Activity context, FacebookCallback<LoginResult> callback) {
//        this.context = context;
//        this.callbackManager = CallbackManager.Factory.create();
//
//        LoginManager.getInstance().registerCallback(callbackManager, callback);
//    }
//
//    public FacebookSignInController() {
//
//    }
    public static boolean isSignIn() {
        return getAccessToken() != null && getAccessToken().isExpired();
    }
    public static AccessToken getAccessToken() {
        return AccessToken.getCurrentAccessToken();
    }
}
