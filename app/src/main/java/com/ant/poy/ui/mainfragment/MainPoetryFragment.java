package com.ant.poy.ui.mainfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.base.OnClickTagListener;
import com.ant.poy.adapter.section.EmptyItemSelection;
import com.ant.poy.adapter.section.HeaderTagPoetrySelection;
import com.ant.poy.adapter.section.PoetryItemSelection;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxLazyFragment;
import com.ant.poy.entity.Poem;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.StringUtils;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.sectioned.SectionedRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/11
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class MainPoetryFragment extends RxLazyFragment {
    private static final String TAG = MainPoetryFragment.class.getSimpleName();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    private List<Poem.ShiWen> shiWenList = new ArrayList<Poem.ShiWen>();


    private static int PAGE_INDEX_POETRY = 1;

    private boolean isRefresh = true;

    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;


    public static MainPoetryFragment newInstance() {

        return new MainPoetryFragment();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_poetry;
    }

    @Override
    protected void finishViewCreated(Bundle savedInstanceState) {
        isPrepared = true;
        lazyLoad();

    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;


        initRecyclerView();
        initEmptyView("努力加载中....");
        loadData();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {

        setRecyclerView(recyclerView, CommonUtil.dpToPx(getSupportActivity(), 10));

        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);

        setSmartRefreshLayout(refreshLayout);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            PAGE_INDEX_POETRY = 1;
            isRefresh = true;
            loadData();
        });

        refreshLayout.setOnLoadmoreListener(refreshLayout1 -> {
            PAGE_INDEX_POETRY = PAGE_INDEX_POETRY + 1;
            isRefresh = false;
            loadData();
            refreshLayout.finishLoadmore(1);
        });

    }

    private void clearData() {
        mSectionedRecyclerViewAdapter.removeAllSections();
    }

    @Override
    protected void finishTask() {

    }

    @Override
    protected void loadData() {


        String c = PoetryPreference.getInstence().getTagsDynasty();
        if ("不限".equals(c)) c = "";
        String x = PoetryPreference.getInstence().getTagsForm();
        if ("不限".equals(x)) x = "";
        String t = PoetryPreference.getInstence().getTagsKind();
        if ("不限".equals(t)) t = "";

        Log.i(TAG, "MainPoetryFragment loadData");

        RetrofitHelper.getPoetryApi().getPoem(PAGE_INDEX_POETRY, c, x, t)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(poem -> {
                    Log.i(TAG, "MainPoetryFragment loadData Success");


                    /** 刷新数据 或加载更多数据*/
                    if (poem.getShiwens().size() > 0) {
                        if (PAGE_INDEX_POETRY == 1) shiWenList.clear();
                        shiWenList.addAll(poem.getShiwens());
                        finishTask(shiWenList, null);

                    } else if (poem.getShiwens().size() == 0 && PAGE_INDEX_POETRY == 1) {
                        finishTask(poem.getShiwens(), StringUtils.getPoetryTagsTerm() + " 暂无数据");

                    } else {
                        /** 第一次加载数据*/
                        hideEmptyView();
                    }

                }, throwable1 -> {

                    Log.e(TAG, "MainPoetryFragment loadData Error -- " + throwable1.getMessage());
                    if (isRefresh) {
                        PAGE_INDEX_POETRY = 1;
                        finishTask(null, StringUtils.getPoetryTagsTerm() + " 暂无数据");
                    } else if (PAGE_INDEX_POETRY > 1) PAGE_INDEX_POETRY = PAGE_INDEX_POETRY - 1;

                    hideEmptyView();

                });


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

    private void finishTask(List<Poem.ShiWen> list, String errorInfo) {
        clearData();
        hideEmptyView();

        mSectionedRecyclerViewAdapter.addSection(new HeaderTagPoetrySelection(new TagChangeListener()));
        if (list != null && list.size() > 0) {
           // mSectionedRecyclerViewAdapter.addSection(new PoetryItemNativeAdSelection(getSupportActivity()));

            mSectionedRecyclerViewAdapter.addSection(new PoetryItemSelection(getSupportActivity(), shiWenList));

        } else mSectionedRecyclerViewAdapter.addSection(new EmptyItemSelection(errorInfo));

        mSectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    private class TagChangeListener implements OnClickTagListener {

        @Override
        public void OnClick(String tagName) {
            PAGE_INDEX_POETRY = 1;
            refreshLayout.autoRefresh();
        }
    }


}
