package com.ant.poy.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.poy.R;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SnowDragon on 2017/7/4.
 */
public abstract class RxLazyFragment extends RxFragment {

    private View parentView;
    private Unbinder unbinder;
    protected FragmentActivity activity;
    public Activity activitySource;


    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;

    //标志位 fragment是否可见
    protected boolean isVisible;

    public View foot_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        activity = getSupportActivity();
        return parentView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        finishViewCreated(savedInstanceState);

        foot_view = LayoutInflater.from(getSupportActivity()).inflate(R.layout.item_empty, null);

    }

    protected abstract int getLayoutId();

    protected abstract void finishViewCreated(Bundle savedInstanceState);


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activitySource = activity;
        this.activity = (FragmentActivity) activity;
    }

    public FragmentActivity getSupportActivity() {

        return super.getActivity();
    }

    public android.app.ActionBar getSupportActionBar() {

        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext() {

        return this.activity == null ? (getActivity() == null ? null :
                getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {

        lazyLoad();
    }

    protected void lazyLoad() {
    }

    protected void onInvisible() {
    }

    protected void loadData() {
    }


    protected void initRecyclerView() {
    }

    protected void finishTask() {
    }
    protected void setSmartRefreshLayout(SmartRefreshLayout refreshLayout){

        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setFooterMaxDragRate(1.5f);
        // refreshLayout.setHeaderHeight(CommonUtil.dpToPx(getSupportActivity(), 35));//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setHeaderHeightPx(CommonUtil.dpToPx(getSupportActivity(),60));

    }

    protected void setRecyclerView(RecyclerView recyclerView,int deivder){
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new SpaceItemDecoration(deivder));
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {

        return (T) parentView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }
}
