package com.guessmusic.imooc.imoocmusic.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.guessmusic.imooc.imoocmusic.R;
import com.guessmusic.imooc.imoocmusic.model.IWordButtonClickListener;
import com.guessmusic.imooc.imoocmusic.model.WordButton;
import com.guessmusic.imooc.imoocmusic.myui.MyGridView;

import java.util.ArrayList;

import util.Util;

public class MainActivity extends AppCompatActivity implements IWordButtonClickListener {

    private Animation mPanAnim;
    private LinearInterpolator mPanLin;

    private Animation mBarInAnim;
    private LinearInterpolator mBarInLin;

    private Animation mBarOutAnim;
    private LinearInterpolator mBarOutLin;

    private ImageView mViewPan;
    private ImageView mViewPanBar;

    private ImageButton mBtnPlayStart;

    private boolean mIsRunning = false;

    private ArrayList<WordButton> mAllWords;
    private ArrayList<WordButton> mBtnSelectWords;
    private MyGridView mMyGridView;
    private LinearLayout mViewWordsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyGridView = (MyGridView) findViewById(R.id.gridview);
        mMyGridView.registOnWordButtonClick(this);
        mViewWordsContainer = (LinearLayout) findViewById(R.id.word_select_container);

        mViewPan = (ImageView) findViewById(R.id.imageView1);
        mViewPanBar = (ImageView) findViewById(R.id.imageView2);

        mBtnPlayStart = (ImageButton) findViewById(R.id.btn_play_start);
        mBtnPlayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlayButton();
            }
        });

        mViewPan = (ImageView) findViewById(R.id.imageView1);
        mViewPanBar = (ImageView) findViewById(R.id.imageView2);

        mPanAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mPanLin = new LinearInterpolator();
        mPanAnim.setInterpolator(mPanLin);
        mPanAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewPanBar.startAnimation(mBarOutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBarInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
        mBarInLin = new LinearInterpolator();
        mBarInAnim.setFillAfter(true);
        mBarInAnim.setInterpolator(mBarInLin);
        mBarInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewPan.startAnimation(mPanAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBarOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
        mBarOutLin = new LinearInterpolator();
        mBarOutAnim.setFillAfter(true);
        mBarOutAnim.setInterpolator(mBarOutLin);
        mBarOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
                mBtnPlayStart.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        initCurrentStageData();
    }

    private void handlePlayButton() {
        if (mViewPanBar != null) {
            if (!mIsRunning) {
                mIsRunning = true;
                mViewPanBar.startAnimation(mBarInAnim);
                mBtnPlayStart.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onPause() {
        mViewPan.clearAnimation();
        super.onPause();
    }

    private void initCurrentStageData() {
        mBtnSelectWords = initWordSelect();
        LayoutParams params = new LayoutParams(140, 140);
        for (int i = 0; i < mBtnSelectWords.size(); i++) {
            mViewWordsContainer.addView(
                    mBtnSelectWords.get(i).mViewButton,
                    params
            );
        }
        mAllWords = initAllWord();
        mMyGridView.updateData(mAllWords);
    }

    private ArrayList<WordButton> initAllWord() {
        ArrayList<WordButton> data = new ArrayList<>();

        for (int i = 0; i < MyGridView.COUNTS_WORDS; i++) {
            WordButton button = new WordButton();
            button.mWordString = "好";
            data.add(button);
        }
        return data;
    }

    private ArrayList<WordButton> initWordSelect() {
        ArrayList<WordButton> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            View view = Util.getView(MainActivity.this, R.layout.self_ui_gridview_item);
            WordButton holder = new WordButton();
            holder.mViewButton = (Button) view.findViewById(R.id.item_btn);
            holder.mViewButton.setTextColor(Color.WHITE);
            holder.mViewButton.setText("");
            holder.mIsVisiable = false;
            holder.mViewButton.setBackgroundResource(R.drawable.game_wordblank);
            data.add(holder);
        }
        return data;
    }

    @Override
    public void onWordButtonClick(WordButton wordButton) {
        Toast.makeText(this, wordButton.mIndex + "", Toast.LENGTH_SHORT).show();
    }
}
