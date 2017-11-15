package com.ant.poy.ui.searchfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.SearchPoetryAdapter;
import com.ant.poy.base.RxLazyFragment;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.ui.SearchActivity;
import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/11
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchPoetryFragment extends RxLazyFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private SearchActivity searchActivity;


    private List<SearchEntity.ShiWen> gushiWensList = new ArrayList<SearchEntity.ShiWen>();

    private SearchPoetryAdapter poetryAdapter;

    private static int PAGE_INDEX_POETRY = 1;


    private SearchEntity searchEntity;

    public static SearchPoetryFragment newInstance() {

        return new SearchPoetryFragment();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_poetry;
    }

    @Override
    protected void finishViewCreated(Bundle savedInstanceState) {
        isPrepared = true;
        lazyLoad();
        searchActivity = (SearchActivity) activitySource;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        hideEmptyView();

        initRecyclerView();
        searchEntity = SearchActivity.searchEntity;
        if (searchEntity != null){

            refreshData(searchEntity);
        }else initEmptyView("暂无数据");


        AppUtils.putRefreshListener(getString(R.string.type_title), new RefreshSearchPoetry());
        isPrepared = false;
    }


    public void refreshData(SearchEntity searchEntity) {


        if (searchEntity != null && searchEntity.getShiwen().size() > 0) {

            gushiWensList.clear();
            gushiWensList.addAll(searchEntity.getShiwen());

            poetryAdapter.clearAll();
            poetryAdapter.setSearchContext(SearchActivity.SEARCH_CNTENT);
            poetryAdapter.addAll(gushiWensList);
            poetryAdapter.notifyDataSetChanged();
            finishTask();
        } else {
            loadData();
            initEmptyView("暂无数据");
        }
    }

    private class RefreshSearchPoetry implements SearchActivity.RefreshListener {

        @Override
        public void RefreshData(SearchEntity searchEntity) {
            refreshData(searchEntity);
        }
    }

    @Override
    protected void initRecyclerView() {
        recyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SpaceItemDecoration(20));

        poetryAdapter = new SearchPoetryAdapter(activity,SearchActivity.SEARCH_CNTENT);

        poetryAdapter.setOnClickTagListener(tagName -> refreshLayout.autoRefresh(1));
        poetryAdapter.addAll(gushiWensList);


        recyclerView.setAdapter(poetryAdapter);

        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setHeaderHeight(CommonUtil.dpToPx(getSupportActivity(), 35));//Header标准高度（显示下拉高度>=标准高度 触发刷新）


        //refreshLayout.setOnRefreshListener(refreshLayout1 -> loadData());

        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);

    }

    @Override
    protected void finishTask() {
        hideEmptyView();
        refreshLayout.finishRefresh(1);
    }

    @Override
    protected void loadData() {
//        RetrofitHelper.getPoetryGuAPI()
//                .getNameSearchFenLei(getString(R.string.type_title), 1, SearchActivity.SEARCH_CNTENT)
//                .compose(bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(searchEntityNew -> {
//                    this.searchEntity = searchEntityNew;
//
//                    if (searchEntity.getGushiwens().size() > 0) {
//                        gushiWensList.clear();
//                        gushiWensList.addAll(searchEntityNew.getGushiwens());
//
//                        poetryAdapter.clearAll();
//                        poetryAdapter.addAll(gushiWensList);
//                        poetryAdapter.notifyDataSetChanged();
//                        finishTask();
//                    } else initEmptyView("暂无数据");
//
//
//                }, throwable -> {
//                    initEmptyView(null);
//                });

    }

    public void initEmptyView(String string) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        if (string == null)
            mCustomEmptyView.setEmptyText(getString(R.string.loading_error));
        else mCustomEmptyView.setEmptyText(string);
    }

    public void hideEmptyView() {

        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
