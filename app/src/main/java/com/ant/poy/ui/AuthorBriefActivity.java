package com.ant.poy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.section.AuthorDetailItemSelection;
import com.ant.poy.adapter.section.AuthorHaaderSelection;
import com.ant.poy.adapter.section.NativeAdSelection;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.entity.Poet;
import com.ant.poy.utils.CommonUtil;
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
 * 2017/10/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AuthorBriefActivity extends RxBaseActivity {
    private static final String AUTHOR_INFO = "author_info";
    private Poet.Author author;


    public static void startIntent(Context context, Poet.Author author) {
        Intent intent = new Intent(context, AuthorBriefActivity.class);
        intent.putExtra(AUTHOR_INFO, author);
        context.startActivity(intent);
    }

    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.title_bar_name)
    TextView title;

    SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;

    private NativeAdSelection nativeAdSelection;
    private String nadSelectionTag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_author_brief;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {
        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mSectionedRecyclerViewAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(CommonUtil.dpToPx(this, 10)));

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);

        author = (Poet.Author) getIntent().getSerializableExtra(AUTHOR_INFO);
        if (author != null && !"ZuoZhe".equals(author.getCont())) {
            hideEmptyView();
            title.setText(author.getNameStr());
            setData();
        } else {
            title.setText(author.getNameStr());
            loadData();
        }


    }

    private void setData() {
        /** 头部*/
        mSectionedRecyclerViewAdapter.addSection(new AuthorHaaderSelection(author));

        /** 广告*/
        nativeAdSelection = new NativeAdSelection();

//        if (CommonUtil.isAvailableNetWork(this))
//            nadSelectionTag = mSectionedRecyclerViewAdapter.addSection(nativeAdSelection);

        nativeAdSelection.setLoadNativeAdListener(error -> {
            if (!TextUtils.isEmpty(nadSelectionTag)) {
                mSectionedRecyclerViewAdapter.removeSection(nadSelectionTag);
                mSectionedRecyclerViewAdapter.notifyDataSetChanged();
            }

        });
        /** 资料*/
        if (author.getZiliaos().size() > 0)
            mSectionedRecyclerViewAdapter.addSection(new AuthorDetailItemSelection(author.getZiliaos()));
    }

    private void loadData() {
        RetrofitHelper.getPoetryApi().getAuthorDetail(author.getId())
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(poet -> {
                    if (poet.getAuthor()!=null&&poet.getAuthor().size()>0){
                        hideEmptyView();
                        author = poet.getAuthor().get(0);
                        setData();
                    }else initEmptyView("暂无数据");

                },throwable -> {
                    initEmptyView("加载失败，请检查网络连接");
                });
    }

    public void initEmptyView(String string) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        if (string == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {


        mCustomEmptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.title_bar_back)
    public void onClick(View view) {
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
