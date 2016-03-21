package com.jiajie.guessmusic.http;

import com.jiajie.guessmusic.GuessMusicApplication;
import com.jiajie.guessmusic.models.Auth;

/**
 * Created by jiajie on 16/3/10.
 */
public class ApiFactory {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String MAX_AGE = "max-stale=";

    public static ApiRequest createApiSignIn(String token, HttpRequest.HttpCallback callback) {
        ApiRequest apiRequest = signIn(token);
        apiRequest.callback(callback);
        return null;
    }
//
//    public static ApiRequest createApiSignOut(HttpCallback callback) {
//        ApiRequest apiRequest = signIn();
//        apiRequest.callback(callback);
//        return apiRequest;
//    }

    public static ApiRequest signIn(String token) {
        Auth auth = Auth.createAuth(token);
        ApiRequest apiPost = new ApiRequest();

        String url = ApiAddress_.getInstance_(GuessMusicApplication.application()).signIn();
        return apiPost;
    }
}
