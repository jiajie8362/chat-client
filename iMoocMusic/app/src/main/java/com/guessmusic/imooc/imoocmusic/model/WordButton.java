package com.guessmusic.imooc.imoocmusic.model;

import android.widget.Button;

/**
 * Created by jiajie on 16/3/3.
 */
public class WordButton {
    public int mIndex;
    public boolean mIsVisiable;
    public String mWordString;
    public Button mViewButton;

    public WordButton() {
        mIsVisiable = true;
        mWordString = "";
    }
}
