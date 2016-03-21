package com.jiajie.guessmusic.models;

import java.io.Serializable;

/**
 * Created by jiajie on 16/3/18.
 */
public class UserSession2 implements Serializable {
    private String type;
    private String sessonId;
    private SessionKey sessionKey;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSessonId() {
        return sessonId;
    }

    public void setSessonId(String sessonId) {
        this.sessonId = sessonId;
    }

    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }
}
