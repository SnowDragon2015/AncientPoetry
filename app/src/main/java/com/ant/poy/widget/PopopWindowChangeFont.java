package com.ant.poy.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.ant.poy.R;
import com.ant.poy.utils.SharedPreference.PoetryPreference;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/28
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PopopWindowChangeFont extends PopupWindow {
    private View mPopView;
    private OnItemClickListener mListener;
    private LinearLayout popup_container;
    private SeekBar mSeekBar;

    public PopopWindowChangeFont(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
        setPopupWindow();

    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.popup_font_layout, null);
        popup_container = (LinearLayout) mPopView.findViewById(R.id.popup_container);
        mSeekBar = (SeekBar) mPopView.findViewById(R.id.seekBar);
        int progress = 50;
        if (PoetryPreference.getInstence().getFontSize() == 14) {
            progress = 0;
        } else if (PoetryPreference.getInstence().getFontSize() == 16) {
            progress = 25;
        } else if (PoetryPreference.getInstence().getFontSize() == 18) {
            progress = 50;
        } else if (PoetryPreference.getInstence().getFontSize() == 20) {
            progress = 75;
        } else if (PoetryPreference.getInstence().getFontSize() == 22) {
            progress = 100;
        }
        mSeekBar.setProgress(progress);

        setmListener();

    }

    private void setmListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = mSeekBar.getProgress();

                if (seekProgress < 13) {
                    mSeekBar.setProgress(0);
                    PoetryPreference.getInstence().putFontSize(14);
                    callOnChange(0);
                } else if (seekProgress >= 13 && seekProgress < 38) {
                    mSeekBar.setProgress(25);
                    PoetryPreference.getInstence().putFontSize(16);
                    callOnChange(25);
                } else if (seekProgress >= 38 && seekProgress < 63) {
                    mSeekBar.setProgress(50);
                    PoetryPreference.getInstence().putFontSize(18);
                    callOnChange(50);
                } else if (seekProgress >= 63 && seekProgress < 88) {
                    mSeekBar.setProgress(75);
                    PoetryPreference.getInstence().putFontSize(20);
                    callOnChange(75);
                } else if (seekProgress >= 88) {
                    mSeekBar.setProgress(100);
                    PoetryPreference.getInstence().putFontSize(22);
                    callOnChange(100);
                }
            }
        });
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.Animation);// 设置动画

        this.setBackgroundDrawable(new ColorDrawable(0x50000000));
        this.setTouchable(true);
        this.setOutsideTouchable(true);

        mPopView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = popup_container.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void OnChangeFontSize(int progress);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private void callOnChange(int progress) {
        if (mListener != null) {
            mListener.OnChangeFontSize(progress);
        }
    }


}
