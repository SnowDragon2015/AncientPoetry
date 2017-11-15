package com.ant.poy.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ant.poy.R;
import com.ant.poy.adapter.SearchPagerAdapter;
import com.ant.poy.adapter.SearchSuggestAdapter;
import com.ant.poy.api.RetrofitHelper;
import com.ant.poy.base.RxBaseActivity;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.KeyBoardUtil;
import com.ant.poy.widget.CustomEmptyView;
import com.flyco.tablayout.SlidingTabLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/11
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */

public class SearchActivity extends RxBaseActivity {

    private static final String AJAX = "ajax";
    /**
     * 搜索
     */
    @BindView(R.id.search_img)
    ImageView mSearchBtn;

    @BindView(R.id.search_edit)
    EditText mSearchEdit;

    @BindView(R.id.search_text_clear)
    ImageView mSearchTextClear;

    @BindView(R.id.search_layout)
    LinearLayout mSearchLayout;

    @BindView(R.id.recycle_view_suggest)
    RecyclerView recyclerView_suggest;


    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;


    /**
     * 导航栏
     */
    @BindView(R.id.sld_tab_layout)
    SlidingTabLayout slidingTabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    /* 判断查找内容是否已经显示*/
    private boolean showFlag = false;


    private SearchSuggestAdapter searchSuggestAdapter;

    private List<Object> suggestList = new ArrayList<Object>();

    public static String SEARCH_CNTENT;

    private SearchPagerAdapter searchPagerAdapter;

    public static SearchEntity searchEntity;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView(Bundle savedInstancedState) {

        initSearch();

        initSearchSuggest();

        setUpEditText();
        /*EventBus 订阅事件*/


    }


    public SearchEntity geNameSearch() {
        return searchEntity;
    }

    private void initSearch() {
        mSearchLayout.setVisibility(View.GONE);

        searchPagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(searchPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new OnPageChangeListener());


        slidingTabLayout.setViewPager(viewPager);


    }

    private void initSearchSuggest() {
        searchSuggestAdapter = new SearchSuggestAdapter(this);
        searchSuggestAdapter.addAll(suggestList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_suggest.setLayoutManager(layoutManager);


        recyclerView_suggest.setAdapter(searchSuggestAdapter);

    }

    private void setUpEditText() {

        /** 监听编辑框 字符长短变化*/
        RxTextView.textChanges(mSearchEdit)
                .compose(this.bindToLifecycle())
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {

                    if (!TextUtils.isEmpty(s)) {
                        recyclerView_suggest.setVisibility(View.VISIBLE);
                        mSearchTextClear.setVisibility(View.VISIBLE);
                    } else {
                        mSearchTextClear.setVisibility(View.GONE);
                        recyclerView_suggest.setVisibility(View.GONE);

                        mSearchLayout.setVisibility(View.GONE);

                    }

                    SEARCH_CNTENT = s;
                    searchSuggestAdapter.clearAll();
                    searchSuggestAdapter.notifyDataSetChanged();
                    initEmptyView("");

                    showFlag = false;
                    getSearchSuggest();
                });

        /** 清空编辑框*/
        RxView.clicks(mSearchTextClear)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {

                    mSearchEdit.setText("");
                    recyclerView_suggest.setVisibility(View.GONE);
                    mCustomEmptyView.setVisibility(View.GONE);
                    //  initSearch();
                    showFlag = false;
                });


        RxTextView.editorActions(mSearchEdit)
                .filter(integer -> !TextUtils.isEmpty(mSearchEdit.getText().toString().trim()))
                .filter(integer -> integer == EditorInfo.IME_ACTION_SEARCH)
                .flatMap(new Func1<Integer, Observable<String>>() {

                    @Override
                    public Observable<String> call(Integer integer) {

                        return Observable.just(mSearchEdit.getText().toString().trim());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {

                    KeyBoardUtil.closeKeybord(mSearchEdit,
                            SearchActivity.this);

                    SEARCH_CNTENT = s;

                });


        mSearchEdit.requestFocus();
        mSearchEdit.setHint("请输入作者、内容、或者类型");
        mSearchEdit.setText(null);
        KeyBoardUtil.openKeybord(mSearchEdit, this);

        /** 点击搜索按钮*/
        RxView.clicks(mSearchBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .map(aVoid -> mSearchEdit.getText().toString().trim())
                .filter(s -> !TextUtils.isEmpty(s))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {

                    KeyBoardUtil.closeKeybord(mSearchEdit,
                            SearchActivity.this);

                    recyclerView_suggest.setVisibility(View.GONE);
                    SEARCH_CNTENT = s;

                    if (!showFlag) {
                        initEmptyView("努力加载中......");
                    }

                    getSearchData();
                });
    }


    private void getSearchSuggest() {
        //SEARCH_CNTENT = "雪"
        if (TextUtils.isEmpty(SEARCH_CNTENT)) return;

        recyclerView_suggest.setVisibility(View.VISIBLE);
        RetrofitHelper.getPoetryApi()
                .getSearchEntity(SEARCH_CNTENT, AJAX)
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchEntity -> {
                    List<Object> list = CommonUtil.getSuggestSize(searchEntity);


                    mCustomEmptyView.setVisibility(View.GONE);
                    mSearchLayout.setVisibility(View.GONE);

                    suggestList.clear();
                    suggestList.addAll(list);
                    searchSuggestAdapter.clearAll();
                    searchSuggestAdapter.addAll(suggestList);
                    searchSuggestAdapter.setSeachContent(SEARCH_CNTENT);
                    searchSuggestAdapter.notifyDataSetChanged();


                }, throwable -> {
                    Log.i("chyy", "SearchActivity " + throwable.getMessage());
                    initEmptyView("");
                });
    }


    private void getSearchData() {


        RetrofitHelper.getPoetryApi()
                .getSearchEntity(SEARCH_CNTENT, "")
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchEntity -> {
                    this.searchEntity = searchEntity;
                    showFlag = true;

                    finishTask();
                }, throwable -> {
                    mCustomEmptyView.setEmptyText(getString(R.string.loading_error));
                });
    }


    private void finishTask() {

        if (searchEntity == null) {
            return;
        }
        if (searchEntity.getShiwen() != null && searchEntity.getShiwen().size() > 0) {

            if (AppUtils.getRefreshListner(getString(R.string.type_title)) != null)

                AppUtils.getRefreshListner(getString(R.string.type_title)).RefreshData(searchEntity);
            hideEmptyView();
            viewPager.setCurrentItem(0);
            return;

        } else if (searchEntity.getAuthor() != null && searchEntity.getAuthor().size() > 0) {

            if (AppUtils.getRefreshListner(getString(R.string.type_author)) != null)
                AppUtils.getRefreshListner(getString(R.string.type_author)).RefreshData(searchEntity);
            hideEmptyView();
            viewPager.setCurrentItem(1);
            return;

        } else if (searchEntity.getGuwen() != null && searchEntity.getGuwen().size() > 0) {

            if (AppUtils.getRefreshListner(getString(R.string.type_guwen)) != null)
                AppUtils.getRefreshListner(getString(R.string.type_guwen)).RefreshData(searchEntity);

            hideEmptyView();
            viewPager.setCurrentItem(2);
            return;
        } else initEmptyView("暂无数据");

    }

    private class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                if (AppUtils.getRefreshListner(getString(R.string.type_title)) != null)
                    AppUtils.getRefreshListner(getString(R.string.type_title)).RefreshData(searchEntity);

            } else if (position == 1) {
                if (AppUtils.getRefreshListner(getString(R.string.type_author)) != null)
                    AppUtils.getRefreshListner(getString(R.string.type_author)).RefreshData(searchEntity);

            } else if (position == 2) {
                if (AppUtils.getRefreshListner(getString(R.string.type_guwen)) != null)
                    AppUtils.getRefreshListner(getString(R.string.type_guwen)).RefreshData(searchEntity);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    public void initEmptyView(String string) {

        mCustomEmptyView.setVisibility(View.VISIBLE);
        mSearchLayout.setVisibility(View.GONE);

        if (null == string)
            mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        else mCustomEmptyView.setEmptyText(string);

        searchEntity = null;
    }

    public void hideEmptyView() {
        mCustomEmptyView.setVisibility(View.GONE);
        mSearchLayout.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.search_back)
    void OnBack() {
        KeyBoardUtil.closeKeybord(mSearchEdit, this);
        searchEntity = null;
        onBackPressed();
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchEntity = null;
        KeyBoardUtil.closeKeybord(mSearchEdit, this);
    }

    public interface RefreshListener {
        void RefreshData(SearchEntity searchEntity);
    }
}
