package com.ant.poy.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by hcc on 16/9/4 19:44
 * 100332338@qq.com
 * <p>
 * 软键盘工具类
 */
public class KeyBoardUtil {


    public static void openKeybord(EditText mEditText, Context mContext) {

        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);

                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 100);


    }


    public static void closeKeybord(EditText mEditText, Context mContext) {

        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mEditText != null)
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
