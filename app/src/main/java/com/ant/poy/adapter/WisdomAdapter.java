package com.ant.poy.adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseRecycleViewAdapter;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.entity.Wisdom;
import com.ant.poy.ui.PoetryDetailActivity;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.StringUtils;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.TagFlowPopupRelativeLayout;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.comm.util.AdError;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/14
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class WisdomAdapter extends BaseRecycleViewAdapter<Wisdom.MingJu> {

    public static final String TAG = "chyy";

    private NativeExpressADView nativeExpressADView;
    public WisdomAdapter(Context context) {
        super(context);
    }

    private OnClickTagListener onClickTagListener;


    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<Wisdom.MingJu>(viewGroup, R.layout.item_wisdom) {
            @Override
            public void setData(Wisdom.MingJu item, int position) {
                ViewGroup container = holder.getView(R.id.container);
                TextView wisdom = holder.getView(R.id.wisdom_tv);
//                if (position ==5){
//                    wisdom.setVisibility(View.GONE);
//                    container.setVisibility(View.VISIBLE);
//                    ADSize adSize = new ADSize(340, 320); // 不支持MATCH_PARENT or WRAP_CONTENT，必须传入实际的宽高
//                    NativeExpressAD nativeExpressAD =
//                            new NativeExpressAD(mContext, adSize, Constants.APPID, Constants.NativeExpressPosID, new MyNativeExpressADListener(container));
//                    nativeExpressAD.loadAD(1);
//
//
//                }else {
                    wisdom.setVisibility(View.VISIBLE);
                    container.setVisibility(View.GONE);
                    wisdom.setTextSize(TypedValue.COMPLEX_UNIT_SP, PoetryPreference.getInstence().getFontSize());
                    wisdom.setText(item.getNameStr());

                    holder.getView(R.id.wisdom_tv).setOnClickListener(v -> PoetryDetailActivity.startIntent(mContext, item.getShiID(),item.getNameStr()));
              //  }






            }
        };
    }

    public class MyNativeExpressADListener implements NativeExpressAD.NativeExpressADListener{
        private ViewGroup container;
        public MyNativeExpressADListener(ViewGroup container){
            this.container = container;
        }
        @Override
        public void onNoAD(AdError adError) {
            Log.i(TAG, String.format("onNoAD, error code: %d, error msg: %s", adError.getErrorCode(),
                    adError.getErrorMsg()));
        }

        @Override
        public void onADLoaded(List<NativeExpressADView> adList) {
            Log.i(TAG, "onADLoaded: " + adList.size());
            // 释放前一个NativeExpressADView的资源
            if (nativeExpressADView != null) {
                nativeExpressADView.destroy();
            }

            if (container.getVisibility() != View.VISIBLE) {
                container.setVisibility(View.VISIBLE);
            }

            if (container.getChildCount() > 0) {
                container.removeAllViews();
            }

            nativeExpressADView = adList.get(0);
            // 保证View被绘制的时候是可见的，否则将无法产生曝光和收益。
            container.addView(nativeExpressADView);
            nativeExpressADView.render();
        }

        @Override
        public void onRenderFail(NativeExpressADView adView) {
            Log.i(TAG, "onRenderFail");
        }

        @Override
        public void onRenderSuccess(NativeExpressADView adView) {
            Log.i(TAG, "onRenderSuccess");
        }

        @Override
        public void onADExposure(NativeExpressADView adView) {
            Log.i(TAG, "onADExposure");
        }

        @Override
        public void onADClicked(NativeExpressADView adView) {
            Log.i(TAG, "onADClicked");
        }

        @Override
        public void onADClosed(NativeExpressADView adView) {
            Log.i(TAG, "onADClosed");
            // 当广告模板中的关闭按钮被点击时，广告将不再展示。NativeExpressADView也会被Destroy，不再可用。
            if (container != null && container.getChildCount() > 0) {
                container.removeAllViews();
                container.setVisibility(View.GONE);
            }
        }

        @Override
        public void onADLeftApplication(NativeExpressADView adView) {
            Log.i(TAG, "onADLeftApplication");
        }

        @Override
        public void onADOpenOverlay(NativeExpressADView adView) {
            Log.i(TAG, "onADOpenOverlay");
        }
    }

    @Override
    public BaseViewHolder OnCreaeFootViewHolder(ViewGroup viewGroup, View viewType) {
        return new BaseViewHolder(viewGroup, R.layout.item_empty) {
            @Override
            public void setData(Object item) {
                super.setData(item);

                StringBuffer nameHint = new StringBuffer();
                String theme = PoetryPreference.getInstence().getTagTheme();
                String themeClas = PoetryPreference.getInstence().getTagThemeClass();

                if ("不限".equals(theme)) {
                    // nameHint.setLength(0);
                    nameHint.append("暂无数据，请检查网络连接");
                } else {
                    // nameHint.setLength(0);
                    nameHint.append(theme).append(" ");
                    if (!"不限".equals(themeClas))
                        nameHint.append(themeClas).append(" ").append("暂无数据");

                }

                CustomEmptyView customEmptyView = (CustomEmptyView) holder.getView(R.id.custom_empty);
                customEmptyView.setEmptyText(nameHint.toString());
                customEmptyView.setTextColor(R.color.gray);

            }
        };
    }

    private TagFlowPopupRelativeLayout tagFPL_theme, tagFPL_class;

    private TextView line;
    private TextView tag_term_tv;

    @Override
    public BaseViewHolder OnCreaeHeaderViewHolder(ViewGroup viewGroup, View viewType) {

        return new BaseViewHolder(viewGroup, R.layout.item_wisdom_header_adapter) {
            @Override
            public void setData(Object item) {

                tag_term_tv = (TextView) holder.getView(R.id.selected_term);
                line = (TextView) holder.getView(R.id.line);

                tagFPL_theme = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_theme);
                tagFPL_theme.setTagsList(StringUtils.getThemeList());
                tagFPL_theme.setTagsPopuList(StringUtils.getThemeAllList());
                tagFPL_theme.setTagKind(mContext.getString(R.string.wisdom_theme));


                tagFPL_class = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_class);
                tagFPL_class.setTagKind(mContext.getString(R.string.wisdom_theme_kind));

                setThemeClass(PoetryPreference.getInstence().getTagTheme());

                listener();

                setDefauleSelected();
            }
        }

                ;
    }

    private void listener() {
        tagFPL_theme.setOnTagPopupClickListener(tagStr -> {

            PoetryPreference.getInstence().putTagTheme(tagStr);
            PoetryPreference.getInstence().putTagThemeClass("不限");
            setThemeClass(tagStr);
            setDefauleSelected();
            if (onClickTagListener != null) onClickTagListener.OnClick(tagStr);

        });

        tagFPL_class.setOnTagPopupClickListener(tagStr -> {

            PoetryPreference.getInstence().putTagThemeClass(tagStr);
            setDefauleSelected();
            if (onClickTagListener != null) {
                if (tagStr.equals("不限")) {
                    onClickTagListener.OnClick(PoetryPreference.getInstence().getTagTheme());
                } else
                    onClickTagListener.OnClick(tagStr);
            }

        });

    }

    private void setThemeClass(String tagName) {

        if ("不限".equals(tagName)) {
            tagFPL_class.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            PoetryPreference.getInstence().putTagThemeClass(tagName);

        } else {
            tagFPL_class.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);

            tagFPL_class.setTagsList(StringUtils.getThemeClassList().get(tagName));

            if (StringUtils.getThemeAllClassList().get(tagName) == null) {
                tagFPL_class.hideImgMore();
            } else {
                tagFPL_class.setTagsPopuList(StringUtils.getThemeAllClassList().get(tagName));
                tagFPL_class.showImgMore();
            }

        }
    }

    private void setDefauleSelected() {
        tagFPL_theme.setSelectedTag(PoetryPreference.getInstence().getTagTheme());

        tagFPL_class.setSelectedTag(PoetryPreference.getInstence().getTagThemeClass());

        tag_term_tv.setText(StringUtils.getWisdomTagsTerm());
    }

    public void setOnClickTagListener(OnClickTagListener onClickTagListener) {
        this.onClickTagListener = onClickTagListener;
    }

    public interface OnClickTagListener {
        void OnClick(String tagName);
    }


}
