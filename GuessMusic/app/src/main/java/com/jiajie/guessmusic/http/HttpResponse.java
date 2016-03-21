package com.jiajie.guessmusic.http;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by jiajie on 16/3/10.
 */
public class HttpResponse implements Callback{
    private Response response;
    private RequestError error;
    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {

    }
    public boolean isSuccessful() {
        return getError().isSuccessful();
    }

    public RequestError getError() {
        return error;
    }
}
