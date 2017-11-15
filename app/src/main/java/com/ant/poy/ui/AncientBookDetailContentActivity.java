package com.ant.poy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.entity.AncientBookDetail;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.CustomHtmlTagHandler;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/17
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientBookDetailContentActivity extends RxBaseActivity {
    private static final String EXT_INFO = "ext_info";


    @BindView(R.id.title_bar_name)
    TextView title;

    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    @BindView(R.id.content_container)
    LinearLayout contentContainer;

    @BindView(R.id.scroll_bottom)
    LinearLayout scrollView_bottom;

    @BindView(R.id.scroll_top)
    ScrollView scrollView_top;

    @BindView(R.id.nameStr)
    TextView nameStrTv;

    @BindView(R.id.cont)
    TextView contTv;

    @BindView(R.id.translate)
    CheckBox translateCbk;

    @BindView(R.id.author)
    TextView authorTv;

    @BindView(R.id.translate_content)
    TextView translateContentTv;


    private AncientBookDetail.AncientInfo ancientInfo;
    private int id;
    private int gwid;


    public static void startIntent(Context context, AncientBookDetail.AncientInfo ancientInfo) {

        Intent intent = new Intent(context, AncientBookDetailContentActivity.class);
        intent.putExtra(EXT_INFO, ancientInfo);

        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ancient_detail_content;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        AncientBookDetail.AncientInfo aninfo = (AncientBookDetail.AncientInfo) getIntent().getSerializableExtra(EXT_INFO);
        if (aninfo == null) {
            initEmptyView("暂无数据");
            return;
        }

        id = aninfo.getId();
        gwid = aninfo.getGwId();

        loadData();

    }

    private void loadData() {
        RetrofitHelper.getPoetryApi().getAncientDetailContent(gwid, id)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ancientBookDetail -> {

                    ancientInfo = ancientBookDetail.getGuwenInfo().get(0);
                    finishTask();

                    hideEmptyView();

                }, throwable -> {
                    initEmptyView("加载失败，请检查网络连接");
                });

    }

    private void finishTask() {
        title.setText(ancientInfo.getNameStr());
        nameStrTv.setText(ancientInfo.getNameStr());

        contTv.setText(Html.fromHtml(ancientInfo.getCont(), null,
                new CustomHtmlTagHandler(this, contTv.getTextColors())));
        authorTv.setText(ancientInfo.getAuthor());

        contTv.setLineSpacing(CommonUtil.dpToPx(8),1);

        if (TextUtils.isEmpty(ancientInfo.getFanyi())) {
            translateCbk.setVisibility(View.GONE);
        } else translateCbk.setVisibility(View.VISIBLE);

        translateContentTv.setText(Html.fromHtml(ancientInfo.getFanyi(), null,
                new CustomHtmlTagHandler(this, translateContentTv.getTextColors())));

        translateContentTv.setLineSpacing(CommonUtil.dpToPx(8),1);

        translateCbk.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                scrollView_bottom.setVisibility(View.VISIBLE);
            } else {
                scrollView_bottom.setVisibility(View.GONE);
            }
        }));

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finished();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.title_bar_back)
    public void onClick(View view) {
        finished();
    }


    private void finished() {
        this.finish();
        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    public void initEmptyView(String string) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        contentContainer.setVisibility(View.GONE);

        if (string == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {


        mCustomEmptyView.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);

    }
}
