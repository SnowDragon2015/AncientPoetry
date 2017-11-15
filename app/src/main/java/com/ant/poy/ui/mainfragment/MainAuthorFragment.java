package com.ant.poy.ui.mainfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.AuthorAdapter;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxLazyFragment;
import com.ant.poy.entity.Poet;
import com.ant.poy.utils.CommonUtil;
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
public class MainAuthorFragment extends RxLazyFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    private List<Poet.Author> authorSList = new ArrayList<Poet.Author>();

    private AuthorAdapter authorAdapter;

    private static int PAGE_INDEX_AUTHOR = 1;
    private boolean isRefresh = true;


    public static MainAuthorFragment newInstance() {

        return new MainAuthorFragment();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_author;
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

        setRecyclerView(recyclerView, CommonUtil.dpToPx(getSupportActivity(), 10));

        authorAdapter = new AuthorAdapter(activity);

        authorAdapter.setOnClickTagListener(tagName -> refreshLayout.autoRefresh(1));
        authorAdapter.addAll(authorSList);

        recyclerView.setAdapter(authorAdapter);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_poetry_header_adapter, null);
        authorAdapter.addHeaderView(view);

        setSmartRefreshLayout(refreshLayout);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            PAGE_INDEX_AUTHOR = 1;
            isRefresh = true;
            loadData();
        });

        refreshLayout.setOnLoadmoreListener(refreshLayout1 -> {
            PAGE_INDEX_AUTHOR = PAGE_INDEX_AUTHOR + 1;
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
        String chaidai = PoetryPreference.getInstence().getTagAuthorDynasty();
        if ("不限".equals(chaidai)) chaidai = "";

        RetrofitHelper.getPoetryApi().getPoet(PAGE_INDEX_AUTHOR, chaidai)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(poet -> {

                    if (poet.getAuthor().size() > 0) {
                        authorSList.clear();
                        authorSList.addAll(poet.getAuthor());

                        if (isRefresh) authorAdapter.clearAll();

                        authorAdapter.addAll(authorSList);
                        authorAdapter.removeFooterView();
                        authorAdapter.notifyDataSetChanged();
                        finishTask();
                    } else if (poet.getAuthor().size() == 0 && isRefresh) {

                        authorAdapter.clearAll();
                        authorAdapter.removeFooterView();
                        authorAdapter.addFooterView(foot_view);

                        authorAdapter.notifyDataSetChanged();

                    } else hideEmptyView();

                }, throwable1 -> {

                    if (isRefresh) {
                        PAGE_INDEX_AUTHOR = 1;
                        authorAdapter.clearAll();
                        authorAdapter.removeFooterView();
                        authorAdapter.addFooterView(foot_view);
                        authorAdapter.notifyDataSetChanged();
                    }

                    hideEmptyView();

                });
    }


    public void initEmptyView(String string) {

        refreshLayout.finishRefresh(1);
        mCustomEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        if (null == string)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {
        refreshLayout.finishRefresh(1);
        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
