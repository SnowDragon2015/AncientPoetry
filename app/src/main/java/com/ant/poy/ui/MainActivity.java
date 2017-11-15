package com.ant.poy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.MainPagerAdapter;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.search.PopupSearchView;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends RxBaseActivity {

    private long exitTime;

    @BindView(R.id.sld_tab_layout)
    SlidingTabLayout slidingTabLayout;

    @BindView(R.id.main_viewPage)
    ViewPager viewPager;

    private PopupSearchView popupSearchView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));

        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setCurrentItem(0);

        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.showDot(0);
        slidingTabLayout.getMsgView(0).setVisibility(View.GONE);



    }


    @OnClick({R.id.click_search, R.id.click_my, R.id.click_recommend})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.click_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
               // ToastUtils.showToast("努力开发中....");
                break;
            case R.id.click_my:
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                break;
            case R.id.click_recommend:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
        }

        return true;
    }


    /**
     * 双击退出App
     */
    private void exitApp() {

        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showToast("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
