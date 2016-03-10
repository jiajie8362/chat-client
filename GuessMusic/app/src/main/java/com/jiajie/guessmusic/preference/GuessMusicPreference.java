package com.jiajie.guessmusic.preference;

import com.jiajie.guessmusic.BuildConfig;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

import static org.androidannotations.annotations.sharedpreferences.SharedPref.Scope.UNIQUE;

/**
 * Created by jiajie on 16/3/10.
 */
@SharedPref(UNIQUE)
public interface GuessMusicPreference {

    @DefaultString(value = BuildConfig.DEFAULT_SERVER_ADDRESS)
    String serverAddress();

    String signInType();
}
