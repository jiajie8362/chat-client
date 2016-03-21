package com.jiajie.guessmusic.http;

/**
 * Created by jiajie on 16/3/21.
 */
public class RequestError {

    public static final int NETWORK_ERROR_MIN = 3;
    public static final int NETWORK_ERROR_MAX = 10;
    private int statusCode;
    private String status;

    public RequestError(int statusCode, String status) {
        this.statusCode = statusCode;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode <= 299;
    }

    public boolean isNetworkError() {
        return statusCode == 1 || (statusCode >= NETWORK_ERROR_MIN && statusCode <= NETWORK_ERROR_MAX);
    }

    @Override
    public String toString() {
        return String.valueOf(statusCode) + " " + status;
    }

    public static final RequestError Unknown = new RequestError(0, "Unknown error.");
    public static final RequestError Conversion = new RequestError(2, "Conversion error.");
    public static final RequestError UnknownNetwork = new RequestError(1, "Network error.");
    public static final RequestError UnknownHost = new RequestError(3, "Network error. Unknown host");
    public static final RequestError SocketTimeout = new RequestError(4, "Network error. Socket time out");
}
