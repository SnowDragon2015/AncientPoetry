package com.ant.poy.adapter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ant.poy.R;
import com.ant.poy.widget.sectioned.StatelessSection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/16
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class NativeAdSelection extends StatelessSection {
    private LoadNativeAdListener loadNativeAdListener;

    public NativeAdSelection() {
        super(R.layout.item_selection_native_ad);
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
//        CommonUtil.getImagerLoader().displayImage("http://img.tuniucdn.com/icons/place_photo/2011-10-141310655037320x240.jpg", viewHolder.nativeImg, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String s, View view) {
//
//            }
//
//            @Override
//            public void onLoadingFailed(String s, View view, FailReason failReason) {
//                loadNativeAdListener.loadState(1);
//            }
//
//            @Override
//            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//
//            }
//
//            @Override
//            public void onLoadingCancelled(String s, View view) {
//
//            }
//        });

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.selection_native_ad_img)
        ImageView nativeImg;


        public ItemViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setLoadNativeAdListener(LoadNativeAdListener loadNativeAdListener) {
        this.loadNativeAdListener = loadNativeAdListener;
    }


    public interface LoadNativeAdListener {
        void loadState(int error);
    }
}
