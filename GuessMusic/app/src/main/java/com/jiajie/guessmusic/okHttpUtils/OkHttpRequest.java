package com.jiajie.guessmusic.okHttpUtils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import static org.androidannotations.annotations.EBean.Scope.Singleton;

/**
 * Created by jiajie on 16/3/21.
 */
@EBean(scope = Singleton)
public class OkHttpRequest implements Callback {
    private static OkHttpClient defaultOkHttpClient = new OkHttpClient();

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {

    }

    public void makeRequest(Request request, Callback callback) {
        Call call = defaultOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
