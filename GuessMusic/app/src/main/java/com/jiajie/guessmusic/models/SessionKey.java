package com.jiajie.guessmusic.models;

import android.util.Base64;

import java.io.Serializable;

/**
 * Created by jiajie on 16/3/18.
 */
public class SessionKey implements Serializable {
    private String type;
    private String algorithm;
    private String key;
    private byte[] sessionKeyBytes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] keyBytes() {
        if (sessionKeyBytes == null) {
            sessionKeyBytes = Base64.decode(key, Base64.NO_WRAP);
        }

        return sessionKeyBytes;
    }
}
