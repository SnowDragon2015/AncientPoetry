package com.ant.poy.ui.mainfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.WisdomAdapter;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxLazyFragment;
import com.ant.poy.entity.Wisdom;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.widget.CustomEmptyView;
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
public class MainWisdomFragment extends RxLazyFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    private Wisdom wisdom;
    private boolean isRefresh = true;

    private List<Wisdom.MingJu> mingJusList = new ArrayList<Wisdom.MingJu>();

    private WisdomAdapter wisdomAdapter;

    private static int PAGE_INDEX_WISDOM = 1;


    public static MainWisdomFragment newInstance() {

        return new MainWisdomFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wisdom;
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
        loadData();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {
        setRecyclerView(recyclerView, 0);

        wisdomAdapter = new WisdomAdapter(activity);

        wisdomAdapter.setOnClickTagListener(tagName -> refreshLayout.autoRefresh(1));
        wisdomAdapter.addAll(mingJusList);


        recyclerView.setAdapter(wisdomAdapter);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_wisdom_header_adapter, null);
        wisdomAdapter.addHeaderView(view);

        setSmartRefreshLayout(refreshLayout);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            PAGE_INDEX_WISDOM = 1;
            isRefresh = true;
            loadData();
        });
        refreshLayout.setOnLoadmoreListener(refreshLayout1 -> {
            PAGE_INDEX_WISDOM = PAGE_INDEX_WISDOM + 1;
            isRefresh = false;
            loadData();
            refreshLayout.finishLoadmore(1);
        });

    }

    @Override
    protected void finishTask() {
        hideEmptyView();

    }

    @Override
    protected void loadData() {
        String t = PoetryPreference.getInstence().getTagTheme();
        String c = "";
        if ("不限".equals(t)) {
            t = "";
            c = "";

        } else {
            c = PoetryPreference.getInstence().getTagThemeClass();
            if ("不限".equals(c)) c = "";
        }

        RetrofitHelper.getPoetryApi().getWisdom(PAGE_INDEX_WISDOM, t, c)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wisdom -> {

                    if (wisdom.getMingju().size() > 0) {
                        mingJusList.clear();
                        mingJusList.addAll(wisdom.getMingju());

                        if (isRefresh) wisdomAdapter.clearAll();

                        wisdomAdapter.addAll(mingJusList);
                        wisdomAdapter.removeFooterView();
                        wisdomAdapter.notifyDataSetChanged();
                        finishTask();
                    } else if (wisdom.getMingju().size() == 0 && isRefresh) {

                        wisdomAdapter.clearAll();
                        wisdomAdapter.removeFooterView();
                        wisdomAdapter.addFooterView(foot_view);

                        wisdomAdapter.notifyDataSetChanged();

                    } else hideEmptyView();

                }, throwable1 -> {

                    if (isRefresh) {
                        PAGE_INDEX_WISDOM = 1;
                        wisdomAdapter.clearAll();
                        wisdomAdapter.removeFooterView();
                        wisdomAdapter.addFooterView(foot_view);
                        wisdomAdapter.notifyDataSetChanged();
                    }


                    hideEmptyView();

                });

    }


    public void initEmptyView(String string) {

        refreshLayout.finishRefresh(1);
        mCustomEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        if (string == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {
        refreshLayout.finishRefresh(1);
        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
