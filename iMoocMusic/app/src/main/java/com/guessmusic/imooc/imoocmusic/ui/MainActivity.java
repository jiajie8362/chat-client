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
import android.widget.TextView;

import com.guessmusic.imooc.imoocmusic.R;
import com.guessmusic.imooc.imoocmusic.data.Const;
import com.guessmusic.imooc.imoocmusic.model.IWordButtonClickListener;
import com.guessmusic.imooc.imoocmusic.model.Song;
import com.guessmusic.imooc.imoocmusic.model.WordButton;
import com.guessmusic.imooc.imoocmusic.myui.MyGridView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import util.Util;

public class MainActivity extends AppCompatActivity implements IWordButtonClickListener {
    public final static int STATUS_ANSWER_RIGHT = 1;
    // 过关界面
    private View mPassView;
    private TextView mViewTxtMainCoins;
    /**
     * 答案状态 —— 错误
     */
    public final static int STATUS_ANSWER_WRONG = 2;

    /**
     * 答案状态 —— 不完整
     */
    public final static int STATUS_ANSWER_LACK = 3;

    // 闪烁次数
    public final static int SPASH_TIMES = 6;

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

    private int mCurrentCoins = Const.TOTAL_COINS;
    private TextView mViewCurrentCoins;

    private TextView mCurrentStagePassView;

    private TextView mCurrentStageView;
    private TextView mCurrentSongNamePassView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyGridView = (MyGridView) findViewById(R.id.gridview);
        mMyGridView.registOnWordButtonClick(this);

        mViewCurrentCoins = (TextView) findViewById(R.id.txt_bar_coins);
        mViewCurrentCoins.setText(mCurrentCoins + "");

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

        handleDeleteWord();

        handleTipAnswer();
    }

    private void handleTipAnswer() {
        ImageButton button = (ImageButton) findViewById
                (R.id.btn_tip_answer);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                tipAnswer();
            }
        });
    }

    private WordButton findIsAnswerWord(int index) {
        WordButton buf = null;

        for (int i = 0; i < MyGridView.COUNTS_WORDS; i++) {
            buf = mAllWords.get(i);

            if (buf.mWordString.equals("" + mCurrentSong.getNameCharacters()[index])) {
                return buf;
            }
        }

        return null;
    }

    private void tipAnswer() {
        boolean tipWord = false;
        for (int i = 0; i < mBtnSelectWords.size(); i++) {
            if (mBtnSelectWords.get(i).mWordString.length() == 0) {
                // 根据当前的答案框条件选择对应的文字并填入
                onWordButtonClick(findIsAnswerWord(i));

                tipWord = true;

                // 减少金币数量
                if (!handleCoins(-getTipCoins())) {
                    // 金币数量不够，显示对话框
                    return;
                }
                break;
            }
        }

        // 没有找到可以填充的答案
        if (!tipWord) {
            // 闪烁文字提示用户
            sparkTheWrods();
        }
    }

    private void handleDeleteWord() {
        ImageButton button = (ImageButton) findViewById(R.id.btn_delete_word);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOneWord();
            }
        });
    }

    private void deleteOneWord() {
        if(!handleCoins(-getDeleteWordCoins())) {
            return;
        }
        setButtonVisiable(findNotAnswerWord(), View.INVISIBLE);
    }

    private WordButton findNotAnswerWord() {
        Random random = new Random();
        WordButton buf = null;

        while(true) {
            int index = random.nextInt(MyGridView.COUNTS_WORDS);

            buf = mAllWords.get(index);

            if (buf.mIsVisiable && !isTheAnswerWord(buf)) {
                return buf;
            }
        }
    }

    private boolean isTheAnswerWord(WordButton word) {
        boolean result = false;

        for (int i = 0; i < mCurrentSong.getNameLength(); i++) {
            if (word.mWordString.equals
                    ("" + mCurrentSong.getNameCharacters()[i])) {
                result = true;

                break;
            }
        }

        return result;
    }

    private int getDeleteWordCoins() {
        return this.getResources().getInteger(R.integer.pay_delete_word);
    }

    private int getTipCoins() {
        return this.getResources().getInteger(R.integer.pay_tip_answer);
    }

    private boolean handleCoins(int data) {
        if (mCurrentCoins + data >= 0) {
            mCurrentCoins += data;

            mViewCurrentCoins.setText(mCurrentCoins + "");
            return true;
        } else {
            return false;
        }
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
            final WordButton holder = new WordButton();
            holder.mViewButton = (Button) view.findViewById(R.id.item_btn);
            holder.mViewButton.setTextColor(Color.WHITE);
            holder.mViewButton.setText("");
            holder.mIsVisiable = false;
            holder.mViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearTheAnswer(holder);
                }
            });
            holder.mViewButton.setBackgroundResource(R.drawable.game_wordblank);
            data.add(holder);
        }
        return data;
    }

    @Override
    public void onWordButtonClick(WordButton wordButton) {
//        Toast.makeText(this, wordButton.mIndex + "", Toast.LENGTH_SHORT).show();
        setSelectWord(wordButton);

        int checkResult = checkTheAnswer();

        if (checkResult == STATUS_ANSWER_RIGHT) {
            handlePassEvent();
        } else if (checkResult == STATUS_ANSWER_WRONG) {
            sparkTheWrods();
            ;
        } else if (checkResult == STATUS_ANSWER_LACK) {
            for (int i = 0; i < mBtnSelectWords.size(); i++) {
                mBtnSelectWords.get(i).mViewButton.setTextColor(Color.WHITE);
            }
        }
    }

    private void handlePassEvent() {
        mPassView = (LinearLayout) this.findViewById(R.id.pass_view);
        mPassView.setVisibility(View.VISIBLE);

        mViewPan.clearAnimation();
        mCurrentStagePassView = (TextView) findViewById(R.id.text_current_stage_pass);
        if (mCurrentStagePassView != null) {
            mCurrentStagePassView.setText((mCurrentStageIndex + 1) + "");
        }

        // ��ʾ��ǰ�ص�����
        mCurrentSongNamePassView = (TextView) findViewById(R.id.text_current_song_name_pass);
        if (mCurrentSongNamePassView != null) {
            mCurrentSongNamePassView.setText(mCurretSong.getSongName());
        }

        mViewTxtMainCoins = (TextView) findViewById(R.id.txt_main_coins);
        if(mViewTxtMainCoins != null){
            mViewTxtMainCoins.setText((mCurrentCoins + 3) + "");
        }
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

    private void clearTheAnswer(WordButton wordButton) {
        wordButton.mViewButton.setText("");
        wordButton.mWordString = "";
        wordButton.mIsVisiable = false;
        setButtonVisiable(mAllWords.get(wordButton.mIndex), View.VISIBLE);
    }

    private void setSelectWord(WordButton wordButton) {
        for (int i = 0; i < mBtnSelectWords.size(); i++) {
            if (mBtnSelectWords.get(i).mWordString.length() == 0) {
                mBtnSelectWords.get(i).mViewButton.setText(wordButton.mWordString);
                mBtnSelectWords.get(i).mIsVisiable = true;
                mBtnSelectWords.get(i).mWordString = wordButton.mWordString;
                mBtnSelectWords.get(i).mIndex = wordButton.mIndex;
                setButtonVisiable(wordButton, View.INVISIBLE);
                break;
            }
        }
    }

    private void setButtonVisiable(WordButton button, int visibility) {
        button.mViewButton.setVisibility(visibility);
        button.mIsVisiable = (visibility == View.VISIBLE) ? true : false;
    }

    private int checkTheAnswer() {
        for (int i = 0; i < mBtnSelectWords.size(); i++) {
            if (mBtnSelectWords.get(i).mWordString.length() == 0) {
                return STATUS_ANSWER_LACK;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mBtnSelectWords.size(); i++) {
            sb.append(mBtnSelectWords.get(i).mWordString);
        }
        return (sb.toString().equals(mCurrentSong.getSongName())) ?
                STATUS_ANSWER_RIGHT : STATUS_ANSWER_WRONG;
    }

    private void sparkTheWrods() {
        TimerTask task = new TimerTask() {
            boolean mChange = false;
            int mSpardTimes = 0;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (++mSpardTimes > SPASH_TIMES) {
                            return;
                        }

                        for (int i = 0; i < mBtnSelectWords.size(); i++) {
                            mBtnSelectWords.get(i).mViewButton.setTextColor(
                                    mChange ? Color.RED : Color.WHITE
                            );
                        }
                        mChange = !mChange;
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1, 150);
    }


}
