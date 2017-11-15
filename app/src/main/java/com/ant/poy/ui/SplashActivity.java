package com.ant.poy.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.utils.SharedPreference.PoetryPreference;

/**
 * Created by SnowDragon on 2017/7/3.
 */
public class SplashActivity extends Activity   {

   // private SplashAD splashAD;
    private ViewGroup container;
    private TextView skipView;
    private ImageView splashHolder;
    private static final String SKIP_TEXT = "点击跳过 %d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        boolean isFirstOpen = PoetryPreference.getInstence().getFirstOpen();
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 如果是第一次启动，则先进入功能引导页
//        if (!isFirstOpen) {
//            Intent intent = new Intent(this, WelcomeGuideActivity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
//            finish();
//            return;
//        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);

        container = (ViewGroup) this.findViewById(R.id.splash_container);
        skipView = (TextView) findViewById(R.id.skip_view);
        splashHolder = (ImageView) findViewById(R.id.splash_holder);

       /* fetchSplashAD(SplashActivity.this, container, skipView, Constants.APPID, Constants.SplashPosID, SplashActivity.this, 0);*/



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 1000);
    }

//    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer,
//                               String appId, String posId, SplashADListener adListener, int fetchDelay) {
//        splashAD = new SplashAD(activity, adContainer, skipContainer, appId, posId, adListener, fetchDelay);
//    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
        finish();
    }
//    @Override
//    public void onADPresent() {
//        Log.i("AD_DEMO", "SplashADPresent");
//        splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
//    }
//
//    @Override
//    public void onADClicked() {
//        Log.i("AD_DEMO", "SplashADClicked");
//    }
//
//    /**
//     * 倒计时回调，返回广告还将被展示的剩余时间。
//     * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
//     *
//     * @param millisUntilFinished 剩余毫秒数
//     */
//    @Override
//    public void onADTick(long millisUntilFinished) {
//        Log.i("AD_DEMO", "SplashADTick " + millisUntilFinished + "ms");
//        skipView.setText(String.format(SKIP_TEXT, Math.round(millisUntilFinished / 1000f)));
//    }
//
//    @Override
//    public void onADDismissed() {
//        Log.i("AD_DEMO", "SplashADDismissed");
//     enterHomeActivity();
//    }
//
//    @Override
//    public void onNoAD(int errorCode) {
//        Log.i("AD_DEMO", "LoadSplashADFail, eCode=" + errorCode);
//        /** 如果加载广告失败，则直接跳转 */
//       enterHomeActivity();
//    }
}