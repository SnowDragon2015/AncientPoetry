package com.ant.poy;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.ant.poy.base.CrashHandler;
import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by SnowDragon2015
 * <p/>
 * 2017/9/11
 * <p/>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientPoetryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppUtils.init(this);

        /** 全局异常捕捉*/
        CrashHandler.getInstance().init(this);

        /**初始化下拉刷新和上拉加载*/
        initRefresh();

        initNightMode();
    }

    private void initRefresh(){
        //设置全局的Header构建器
        ClassicsHeader.REFRESH_HEADER_LASTTIME = "上次更新 yyyy-MM-dd HH:mm:ss";
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.common_bg, R.color.black_A);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    protected void initNightMode() {
        boolean isNight = PoetryPreference.getInstence().geSwidthMode();

//        if (isNight) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
    }
}
