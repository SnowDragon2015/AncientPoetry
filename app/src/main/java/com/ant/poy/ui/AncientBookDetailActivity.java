package com.ant.poy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.section.AncientDetailHaaderSelection;
import com.ant.poy.adapter.section.AncientDetailItemSelection;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.entity.GuWen;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.SpaceItemDecoration;
import com.ant.poy.widget.sectioned.SectionedRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
public class AncientBookDetailActivity extends RxBaseActivity {
    private static final String EXT_INFO_ANCIENT = "ext_info_id";


    @BindView(R.id.title_bar_name)
    TextView title;


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private GuWen.Ancient ancient;
    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;




    public static void startIntent(Context context, GuWen.Ancient ancient) {

        Intent intent = new Intent(context, AncientBookDetailActivity.class);
        intent.putExtra(EXT_INFO_ANCIENT, ancient);

        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ancient_book_detail;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        ancient = (GuWen.Ancient) getIntent().getSerializableExtra(EXT_INFO_ANCIENT);
        if (ancient == null) {
            initEmptyView(null);
            return;
        }

        title.setText(ancient.getNameStr());

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SpaceItemDecoration(2));

        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);

        mSectionedRecyclerViewAdapter.addSection(new AncientDetailHaaderSelection(ancient));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);

        loadData();

    }

    private void loadData() {
        RetrofitHelper.getPoetryApi().getAncientDetail(ancient.getId())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ancientBookDetail -> {
                    mSectionedRecyclerViewAdapter.addSection(new AncientDetailItemSelection(this,ancientBookDetail));
                    hideEmptyView();

                }, throwable -> {
                    initEmptyView(null);
                });

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
        recyclerView.setVisibility(View.GONE);

        if (string == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {
        refreshLayout.finishRefresh(1);
        refreshLayout.finishLoadmore(1);

        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

    }
}
