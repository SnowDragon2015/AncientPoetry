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
import com.ant.poy.adapter.section.PoetryDetailBottomSelection;
import com.ant.poy.adapter.section.PoetryDetailHeaderSelection;
import com.ant.poy.adapter.section.PoetryDetailItemSelection;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.entity.PoetryDetail;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.SpaceItemDecoration;
import com.ant.poy.widget.sectioned.SectionedRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PoetryDetailActivity extends RxBaseActivity {

    private static final String EXT_INFO = "ext_info";
    private static final String EXT_SELECTED_STR = "ext_select";

    @BindView(R.id.title_bar_name)
    TextView title_bar_name;

    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;

    private int id;
    private String selectedStr;


    public static void startIntent(Context context, int id,String extStr) {
        Intent intent = new Intent(context, PoetryDetailActivity.class);
        intent.putExtra(EXT_INFO, id);
        intent.putExtra(EXT_SELECTED_STR,extStr);
        context.startActivity(intent);


    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_poetry_detail;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        id = getIntent().getIntExtra(EXT_INFO, 0);
        selectedStr = getIntent().getStringExtra(EXT_SELECTED_STR);

        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SpaceItemDecoration(CommonUtil.dpToPx(this,10)));

        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);

        loadData();


    }

    private void loadData() {
        RetrofitHelper.getPoetryApi().getPoetryDetail(id)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(poetryDetail -> {
                    hideEmptyView();
                    if (poetryDetail != null && poetryDetail.getShiwens() != null && poetryDetail.getShiwens().size() > 0) {
                        PoetryDetail.Poetry poetry = poetryDetail.getShiwens().get(0);
                        title_bar_name.setText(poetry.getNameStr());

                        mSectionedRecyclerViewAdapter.addSection(new PoetryDetailHeaderSelection(this, poetry,selectedStr));

                        if (poetry.getInfo()!=null && poetry.getInfo().size()>0){
                            mSectionedRecyclerViewAdapter.addSection(new PoetryDetailItemSelection(poetry.getInfo()));
                        }

                        if (poetry.getInfo_author() != null){
                            mSectionedRecyclerViewAdapter.addSection(new PoetryDetailBottomSelection(poetry.getInfo_author()));
                        }

                    } else initEmptyView("暂无数据");

                }, throwable -> {
                    initEmptyView("加载失败，请检查网络连接");
                });

    }


    @OnClick(R.id.title_bar_back)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finished();
                break;
        }
    }

    public void initEmptyView(String string) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        if (string == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {


        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

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
