package com.ant.poy.ui.searchfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.SearchAuthorAdapter;
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
public class SearchAuthorFragment extends RxLazyFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    private List<SearchEntity.ZuoZhe> gushiWensList = new ArrayList<SearchEntity.ZuoZhe>();

    private SearchAuthorAdapter searchAuthorAdapter;

    private static int PAGE_INDEX_POETRY = 1;


    private SearchEntity searchEntity;

    private SearchActivity searchActivity;


    public static SearchAuthorFragment newInstance() {

        return new SearchAuthorFragment();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_author;
    }

    @Override
    protected void finishViewCreated(Bundle savedInstanceState) {
        isPrepared = true;
        searchActivity = (SearchActivity) activitySource;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;


        initRecyclerView();
        searchEntity = SearchActivity.searchEntity;
        if (searchEntity != null){
            refreshData(searchEntity);
        }else initEmptyView("暂无数据");

        AppUtils.putRefreshListener(getString(R.string.type_author), new RefreshSearchAuthor());
        isPrepared = false;
    }


    public void refreshData(SearchEntity searchEntity) {

        if (searchEntity != null &&searchEntity.getAuthor()!=null&& searchEntity.getAuthor().size() > 0) {

            gushiWensList.clear();
            gushiWensList.addAll(searchEntity.getAuthor());
            //searchAuthorAdapter.setHeaderObject(searchEntity.getAuthor());

            searchAuthorAdapter.clearAll();
            searchAuthorAdapter.setSearchContext(SearchActivity.SEARCH_CNTENT);
            searchAuthorAdapter.addAll(gushiWensList);
            searchAuthorAdapter.notifyDataSetChanged();
            finishTask();
        } else {
            loadData();
            initEmptyView("暂无数据");
        }
    }

    private class RefreshSearchAuthor implements SearchActivity.RefreshListener {


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

        searchAuthorAdapter = new SearchAuthorAdapter(activity,SearchActivity.SEARCH_CNTENT);

        searchAuthorAdapter.addAll(gushiWensList);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_author_adapter, null);
       // searchAuthorAdapter.addHeaderView(view);


        recyclerView.setAdapter(searchAuthorAdapter);

        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setHeaderHeight(CommonUtil.dpToPx(getSupportActivity(), 35));//Header标准高度（显示下拉高度>=标准高度 触发刷新）

        mCustomEmptyView.setVisibility(View.VISIBLE);

       // refreshLayout.setOnRefreshListener(refreshLayout1 -> loadData());
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
//                .getNameSearchFenLei(getString(R.string.type_author), 1, SearchActivity.SEARCH_CNTENT)
//                .compose(bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(searchEntityNew -> {
//
//                    /** 刷新的时候，作者信息返回为空*/
//                    NameSearch.TbAuthor tbAuthor = null;
//                    if (null != searchEntityNew) {
//                        tbAuthor = searchEntity.getTb_author();
//                    }
//                    this.searchEntity = searchEntityNew;
//                    if (null != tbAuthor)
//                        this.searchEntity.setTb_author(tbAuthor);
//
//
//                    if (null == searchEntityNew.getTb_author().getCont()) {
//                        searchAuthorAdapter.removeHeader();
//                    }
//                    gushiWensList.clear();
//                    if (searchEntityNew.getGushiwens().size() > 0) {
//                        gushiWensList.addAll(searchEntity.getGushiwens());
//
//                        searchAuthorAdapter.clearAll();
//                        searchAuthorAdapter.addAll(gushiWensList);
//                        searchAuthorAdapter.setHeaderObject(searchEntity.getTb_author());
//                        searchAuthorAdapter.notifyDataSetChanged();
//                        finishTask();
//                    } else initEmptyView("暂无数据");
//
//
//                }, throwable -> {
//                    initEmptyView(null);
//                });


    }


    public void initEmptyView(String errStr) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        if (errStr == null)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(errStr);
    }

    public void hideEmptyView() {

        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
