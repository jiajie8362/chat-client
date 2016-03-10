package com.jiajie.guessmusic.http;

/**
 * Created by jiajie on 16/3/10.
 */
public class ApiFactory {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String MAX_AGE = "max-stale=";

    public static ApiRequest createApiSignIn(String token, CredentialType type, HttpCallback callback) {
        ApiRequest apiRequest = signIn(token, type);
        apiRequest.callback(callback);
        return apiRequest;
    }

    public static ApiRequest createApiSignOut(HttpCallback callback) {
        ApiRequest apiRequest = signIn();
        apiRequest.callback(callback);
        return apiRequest;
    }
}
