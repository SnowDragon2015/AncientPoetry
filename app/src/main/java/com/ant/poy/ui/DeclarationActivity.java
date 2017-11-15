package com.ant.poy.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.base.RxBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class DeclarationActivity extends RxBaseActivity {
    @BindView(R.id.title_bar_name)
    TextView title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_declaration;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        title.setText(getString(R.string.my_statement));
    }


    @OnClick(R.id.title_bar_back)
    public void onClick(View view){
        finished();
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
}
