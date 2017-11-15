package com.ant.poy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.PopopWindowChangeFont;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/11
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class MyActivity extends RxBaseActivity implements PopopWindowChangeFont.OnItemClickListener {

    @BindView(R.id.title_bar_name)
    TextView title_tv;

    @BindView(R.id.my_switch_mode)
    CheckBox switch_mode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        title_tv.setText(getResources().getString(R.string.title_my));

        boolean isNight = PoetryPreference.getInstence().geSwidthMode();
        switch_mode.setChecked(isNight);
        switchNightMode(isNight);
        switch_mode.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            switchNightMode(isChecked);
        }));


    }

    @OnClick({R.id.title_bar_back, R.id.my_font_size, R.id.my_about, R.id.my_declaration, R.id.my_clear_cache})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finished();
                break;
            case R.id.my_font_size:
                PopopWindowChangeFont popopWindowChangeFont = new PopopWindowChangeFont(this);
                popopWindowChangeFont.setOnItemClickListener(this);
                popopWindowChangeFont.showAtLocation(title_tv, Gravity.CENTER, 0, 0);

                break;
            case R.id.my_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.my_declaration:
                startActivity(new Intent(this, DeclarationActivity.class));
                break;
            case R.id.my_clear_cache:
                File file = new File(AppUtils.getAppContext().getCacheDir(), "HttpCache");
                if (file.exists()) file.delete();
                ToastUtils.showToast(getString(R.string.clear_cache_ing));
                break;

        }
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode(boolean isChecked) {

        if (isChecked) {
            CommonUtil.setBackgroundAlpha(this, 0.6f, 0);

            // 日间模式
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PoetryPreference.getInstence().putSwitchMode(isChecked);
        } else {
            // 夜间模式
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PoetryPreference.getInstence().putSwitchMode(isChecked);
            CommonUtil.setBackgroundAlpha(this, 1, 0);
        }

        //  recreate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finished();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finished() {
        this.finish();
        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    @Override
    public void OnChangeFontSize(int progress) {

    }
}
