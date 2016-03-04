package com.guessmusic.imooc.imoocmusic.myui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.guessmusic.imooc.imoocmusic.R;
import com.guessmusic.imooc.imoocmusic.model.IWordButtonClickListener;
import com.guessmusic.imooc.imoocmusic.model.WordButton;

import java.util.ArrayList;

import util.Util;

/**
 * Created by jiajie on 16/3/3.
 */
public class MyGridView extends GridView {
    public final static String TAG = "MainActivity";


    public final static int COUNTS_WORDS = 24;

    private ArrayList<WordButton> mArrayList = new ArrayList<>();
    private MyGridAdapter mAdapter;
    private Context mContext;
    private Animation mScaleAnimation;
    private IWordButtonClickListener mWordButtonListener;

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAdapter = new MyGridAdapter();
        this.setAdapter(mAdapter);
    }

    public void updateData(ArrayList<WordButton> list) {
        mArrayList = list;
        setAdapter(mAdapter);
    }

    class MyGridAdapter extends BaseAdapter {
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final WordButton holder;
            if (convertView == null) {
                convertView = Util.getView(mContext, R.layout.self_ui_gridview_item);
                holder = mArrayList.get(position);

                mScaleAnimation = AnimationUtils.loadAnimation(mContext, R.anim.scale);
                mScaleAnimation.setStartOffset(position * 100);


                holder.mIndex = position;
                holder.mViewButton = (Button) convertView.findViewById(R.id.item_btn);
                holder.mViewButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWordButtonListener.onWordButtonClick(holder);
                    }
                });
                convertView.setTag(holder);
            } else {
                holder = (WordButton) convertView.getTag();
            }
            holder.mViewButton.setText(holder.mWordString);
            convertView.startAnimation(mScaleAnimation);
            return convertView;
        }
    }

    public void registOnWordButtonClick(IWordButtonClickListener listener) {
        mWordButtonListener = listener;
    }
}
