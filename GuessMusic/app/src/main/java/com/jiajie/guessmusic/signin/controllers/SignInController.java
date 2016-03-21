package com.jiajie.guessmusic.signin.controllers;

import com.jiajie.guessmusic.models.UserController_;
import com.jiajie.guessmusic.models.UserSession2;

import static com.jiajie.guessmusic.GuessMusicApplication.applicationContext;

/**
 * Created by jiajie on 16/3/17.
 */
public class SignInController {
    public static boolean isSignIn() {
        if (!FacebookSignInController.isSignIn()) {
            return false;
        }

        UserSession2 session = UserController_.getInstance_(applicationContext()).getSession();
        if (session == null) {
            return false;
        }

        return true;
    }
}
