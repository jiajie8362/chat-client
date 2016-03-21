package com.jiajie.guessmusic.http;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by jiajie on 16/3/10.
 */
public class HttpRequest implements Callback{
    public static final boolean LOG_ENABLE = false;
    public static final String TAG = HttpRequest.class.getSimpleName();
    public static OkHttpClient defaultHttpClient;

    private OkHttpClient httpClient;

    protected OkHttpClient http() {
        if (httpClient == null) {
            //okHttpClient(defaultOkHttpClient().clone());
        }

        return httpClient;
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {

    }
}
