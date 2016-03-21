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
    public static final String LOG_TAG = "ApiLogX";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
    public static final String HEAD = "HEAD";
    public static final String SEARCH = "SEARCH";
    public static final String PATCH = "PATCH";
    public static final String TAG = HttpRequest.class.getSimpleName();
    public static OkHttpClient defaultHttpClient;
    private HttpCallback callback;
    private OkHttpClient httpClient;

    public interface HttpCallback {
        void onResponse(HttpRequest request, HttpResponse response);
    }

    protected OkHttpClient http() {
        if (httpClient == null) {
            //okHttpClient(defaultOkHttpClient().clone());
        }

        return httpClient;
    }

    public HttpRequest callback(HttpCallback callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {

    }
}
