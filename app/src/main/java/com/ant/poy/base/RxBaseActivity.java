package com.ant.poy.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SnowDragon on 2017/7/4.
 */
public abstract class RxBaseActivity extends RxAppCompatActivity {

    Unbinder unbinder;
    protected Context context;
    protected int statusBarColor = 0;
    protected View statusBarView = null;
    private boolean mNowMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //设置状态栏颜色
//        if (statusBarColor == 0) {
//            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
//        } else if (statusBarColor != -1) {
//            statusBarView = StatusBarCompat.compat(this, statusBarColor);
//        }
//        transparent19and20();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.context = this;

        //初始化黄油刀控件绑定框架
        unbinder = ButterKnife.bind(this);

        //初始化控件
        initView(savedInstanceState);

        mNowMode = PoetryPreference.getInstence().geSwidthMode();


    }

    @Override
    protected void onResume() {
        super.onResume();

       // if (mNowMode != PoetryPreference.getInstence().geSwidthMode()) {
            if (PoetryPreference.getInstence().geSwidthMode()) {
               // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                CommonUtil.setBackgroundAlpha(this, 0.6f, 0);
            } else {
              //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                CommonUtil.setBackgroundAlpha(this, 1, 0);
            }

          //  recreate();
        //}


    }


    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstancedState);

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();


    }
}
