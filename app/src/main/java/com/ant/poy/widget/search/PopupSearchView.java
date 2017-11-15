package com.ant.poy.widget.search;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.ant.poy.R;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/21
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */

public class PopupSearchView extends PopupWindow {
    private static final String TAG = "PopupSearchView";


    private boolean isSearchOpen = false;

    private SearchViewLayout mSearchViewLayout;


    public PopupSearchView(Activity context) {
        super(context);


        mSearchViewLayout = new SearchViewLayout(context);


        //设置PopupWindow的View
        this.setContentView(mSearchViewLayout);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        listener();
    }

    public void showSearchView(View view) {

        if (isSearchOpen) {
            return;
        }

        mSearchViewLayout.setText(null);
        mSearchViewLayout.getmSearchSrcTextView().requestFocus();


        showAtLocation(view, Gravity.CENTER, 0, 0);

        isSearchOpen = true;
    }

    private void listener() {

        mSearchViewLayout.getmBackBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                isSearchOpen = false;
                mSearchViewLayout.closeSearch();
            }
        });
    }


    public void setOnSearchLisener(SearchViewLayout.OnSearchLisener onSearchLisener) {
        mSearchViewLayout.setOnTextChangeLisener(onSearchLisener);
    }


}
