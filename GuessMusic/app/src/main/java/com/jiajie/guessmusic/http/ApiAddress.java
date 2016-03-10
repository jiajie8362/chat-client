package com.jiajie.guessmusic.http;

import android.net.Uri;

import com.jiajie.guessmusic.preference.GuessMusicPreference_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by jiajie on 16/3/10.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ApiAddress {

    @Pref
    GuessMusicPreference_ guessMusicPreference;

    public String serverHost() {
        return guessMusicPreference.serverAddress().get();
    }

    public String makeUrl(String requestPath) {

        String host = serverHost();
        Uri uri = Uri.parse(host);

        StringBuilder urlBuilder = new StringBuilder(uri.getScheme());
        urlBuilder.append("://")
                .append(uri.getHost());

        if (uri.getPort() != -1) {
            urlBuilder.append(":")
                    .append(uri.getPort());
        }
        urlBuilder.append(requestPath);
        return urlBuilder.toString();
    }

    public String signIn() {
        return makeUrl("/v1/session");
    }

    public String signOut() {
        return makeUrl("/v1/session");
    }

    public String categoryItunes() {
        return makeUrl("/v1/audios/iTunes/categories");
    }

    public String categoryItunes(String itunesCategoryName) {
        return makeUrl("/v1/audios/iTunes/categories/" + itunesCategoryName);
    }
}
