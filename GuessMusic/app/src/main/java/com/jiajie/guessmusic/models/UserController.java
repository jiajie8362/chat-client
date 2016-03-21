package com.jiajie.guessmusic.models;

import com.jiajie.guessmusic.http.ApiRequest;

import org.androidannotations.annotations.EBean;

import static org.androidannotations.annotations.EBean.Scope.Singleton;

/**
 * Created by jiajie on 16/3/18.
 */
@EBean(scope = Singleton)
public class UserController {
    public static final String SESSION_FILE = "session";
    private UserSession2 session;
    private ApiRequest apiSign;

    public UserSession2 getSession() {
        return session;
    }

    public void setSession(UserSession2 session) {
        this.session = session;
    }

    public void signIn(String token) {
        //apiSign = createApiSignIn(token, type, this);
    }
}
