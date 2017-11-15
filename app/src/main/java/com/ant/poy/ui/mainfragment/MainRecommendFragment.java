package com.ant.poy.ui.mainfragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ant.poy.R;
import com.ant.poy.adapter.RecommendAdapter;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxLazyFragment;
import com.ant.poy.entity.Recommend;
import com.ant.poy.utils.CommonUtil;
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
public class MainRecommendFragment extends RxLazyFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    private List<Recommend.TuiJian> tuiJianList = new ArrayList<Recommend.TuiJian>();

    private RecommendAdapter recommendAdapter;

    private static int PAGE_INDEX = 1;


    public static MainRecommendFragment newInstance() {

        return new MainRecommendFragment();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
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


        initRecycleView();
        loadData();
        isPrepared = false;


    }

    private void initRecycleView() {
       setSmartRefreshLayout(refreshLayout);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            PAGE_INDEX = 1;
            loadData();
            refreshLayout.finishRefresh(1);
        });
        refreshLayout.setOnLoadmoreListener(refreshLayout1 -> {
            PAGE_INDEX = PAGE_INDEX + 1;
            loadData();
            refreshLayout.finishLoadmore(1);
        });

       setRecyclerView(recyclerView, CommonUtil.dpToPx(getSupportActivity(),10));

        recommendAdapter = new RecommendAdapter(activity);
        recommendAdapter.addAll(tuiJianList);

        recyclerView.setAdapter(recommendAdapter);


    }

    @Override
    protected void loadData() {

        RetrofitHelper.getPoetryApi().getRecommend()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recommend -> {

                    if (recommend.getTuijie().size() > 0) {
                        if (PAGE_INDEX == 1){
                            recommendAdapter.clearAll();
                            tuiJianList.clear();
                        }
                        tuiJianList.addAll(recommend.getTuijie());

                        recommendAdapter.addAll(tuiJianList);
                        recommendAdapter.notifyDataSetChanged();

                        hideEmptyView();
                    } else {
                        initEmptyView("暂无数据");
                    }


                }, throwable -> {
                    if (PAGE_INDEX > 1)
                        PAGE_INDEX = PAGE_INDEX - 1;
                    if (tuiJianList.size() == 0)
                        initEmptyView(null);

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

        mCustomEmptyView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
