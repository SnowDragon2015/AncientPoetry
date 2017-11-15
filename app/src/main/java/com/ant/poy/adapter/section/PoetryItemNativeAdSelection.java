package com.ant.poy.adapter.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ant.poy.R;
import com.ant.poy.widget.sectioned.StatelessSection;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.comm.util.AdError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/17
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PoetryItemNativeAdSelection extends StatelessSection  {
    public static final String TAG = "chyy";

    private Context mContext;
    private NativeExpressADView nativeExpressADView;


    public PoetryItemNativeAdSelection(Context context) {
        super(R.layout.item_selection_poetry_native_ad);
        this.mContext = context;

    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemPoetryHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemPoetryHolder viewHolder = (ItemPoetryHolder) holder;
        ADSize adSize = new ADSize(500, 88); // 不支持MATCH_PARENT or WRAP_CONTENT，必须传入实际的宽高
        NativeExpressAD  nativeExpressAD =
                new NativeExpressAD(mContext, adSize, "1106507202", "3060521795970494", new MyNativeExpressADListener(viewHolder.container));
        nativeExpressAD.loadAD(1);

    }




    static class ItemPoetryHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.container)
        FrameLayout container;


        public ItemPoetryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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


}
