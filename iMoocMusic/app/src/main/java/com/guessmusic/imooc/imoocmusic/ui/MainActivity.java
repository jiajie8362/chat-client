package com.guessmusic.imooc.imoocmusic.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guessmusic.imooc.imoocmusic.R;
import com.guessmusic.imooc.imoocmusic.data.Const;
import com.guessmusic.imooc.imoocmusic.model.IWordButtonClickListener;
import com.guessmusic.imooc.imoocmusic.model.Song;
import com.guessmusic.imooc.imoocmusic.model.WordButton;
import com.guessmusic.imooc.imoocmusic.myui.MyGridView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

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

    private Song mCurrentSong;
    private int mCurrentStageIndex = -1;

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

    private Song loadStageSongInfo(int stageIndex) {
        Song song = new Song();

        String[] stage = Const.SONG_INFO[stageIndex];
        song.setSongFileName(stage[Const.INDEX_FILE_NAME]);
        song.setSongName(stage[Const.INDEX_SONG_NAME]);

        return song;
    }

    private void initCurrentStageData() {
        mCurrentSong = loadStageSongInfo(++mCurrentStageIndex);

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
        String[] words = generateWords();
        for (int i = 0; i < MyGridView.COUNTS_WORDS; i++) {
            WordButton button = new WordButton();
            button.mWordString = words[i];
            data.add(button);
        }
        return data;
    }

    private ArrayList<WordButton> initWordSelect() {
        ArrayList<WordButton> data = new ArrayList<>();
        for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
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

    private String[] generateWords() {
        Random random = new Random();
        String[] words = new String[MyGridView.COUNTS_WORDS];

        for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
            words[i] = mCurrentSong.getNameCharacters()[i] + "";
        }

        for (int i = mCurrentSong.getNameLength(); i < MyGridView.COUNTS_WORDS; i++) {
            words[i] = getRandomChar() + "";
        }

        // 打乱文字顺序：首先从所有元素中随机选取一个与第一个元素进行交换，
        // 然后在第二个之后选择一个元素与第二个交换，知道最后一个元素。
        // 这样能够确保每个元素在每个位置的概率都是1/n。
        for (int i = MyGridView.COUNTS_WORDS - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);

            String buf = words[index];
            words[index] = words[i];
            words[i] = buf;
        }

        return words;
    }

    private char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return str.charAt(0);
    }
}
