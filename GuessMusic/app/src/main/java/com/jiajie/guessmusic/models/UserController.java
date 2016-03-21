package com.jiajie.guessmusic.models;

import android.util.Log;

import com.jiajie.guessmusic.GuessMusicApplication;
import com.jiajie.guessmusic.http.ApiAddress;
import com.jiajie.guessmusic.okHttpUtils.OkHttpRequest_;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import static org.androidannotations.annotations.EBean.Scope.Singleton;

/**
 * Created by jiajie on 16/3/18.
 */
@EBean(scope = Singleton)
public class UserController implements Callback {
    public static final String SESSION_FILE = "session";
    private UserSession2 session;
    private Request apiSign;

    @Bean
    ApiAddress apiAddress;

    public UserSession2 getSession() {
        return session;
    }

    public void setSession(UserSession2 session) {
        this.session = session;
    }

    public void signIn(String token) {
        String url = apiAddress.signIn();

        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("token", token);

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        OkHttpRequest_.getInstance_(GuessMusicApplication.applicationContext())
                .makeRequest(request, this);
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Log.v("UserController", e.toString());
    }

    @Override
    public void onResponse(Response response) throws IOException {
        Log.v("UserController", response.toString());
        if (response.isSuccessful()) {

        }
    }
}
