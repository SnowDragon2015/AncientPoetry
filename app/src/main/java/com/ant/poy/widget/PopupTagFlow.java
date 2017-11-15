package com.ant.poy.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.ant.poy.R;
import com.ant.poy.adapter.TagFlowPopupAdapter;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/15
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PopupTagFlow {

    private PopupWindow mPopupWindow;
    private Activity mContext;
    private TagFlowLayout tagFlowLayout;
    private List<String> tagsList = new ArrayList<String>();

    private TagFlowPopupAdapter tagFlowAdapter;

    private View popupView;

    private OnClickPopupTagListener onClickPopupTagListener;

    public PopupTagFlow(Activity context, List<String> list) {
        this.mContext = context;

        tagsList.clear();
        tagsList.addAll(list);
        createPopup();

    }

    public PopupTagFlow(Activity context) {
        this.mContext = context;
        createPopup();

    }

    public void createPopup() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.common_tag_popup, null);

        tagFlowLayout = (TagFlowLayout) popupView.findViewById(R.id.tag_flow_layout);
        tagFlowAdapter = new TagFlowPopupAdapter(mContext, tagFlowLayout, tagsList);
        tagFlowLayout.setAdapter(tagFlowAdapter);

        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);


        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x50000000));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.setOnDismissListener(() -> CommonUtil.setBackgroundAlpha(mContext, 1,0));

        tagFlowLayout.setOnTagClickListener(((view, position, parent) -> {

            if (onClickPopupTagListener != null) {
                onClickPopupTagListener.onClick(tagsList.get(position));
            }
            dismiss();
        }));


    }

    public void dismiss() {
        if (mPopupWindow != null)
            mPopupWindow.dismiss();
    }

    public void showPopupWindow() {

        Animation animation = AnimationUtils
                .loadAnimation(mContext, R.anim.anim_fade_in);
        mPopupWindow.getContentView().startAnimation(animation);
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        CommonUtil.setBackgroundAlpha(mContext, 0.5f,0);//设置屏幕透明度

    }


    public TagFlowLayout getTagFlowLayout() {
        return tagFlowLayout;
    }


/*    *//**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     *//*
    public static void setBackgroundAlpha(Activity activity, float bgAlpha,int colorId) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(colorId));
        activity.getWindow().setAttributes(lp);

    }*/
    public void setSelected(String tagName){
        tagFlowAdapter.setSelectedList(tagsList.indexOf(tagName));
    }


    public void setOnClickPopupTagListener(OnClickPopupTagListener onClickPopupTagListener) {
        this.onClickPopupTagListener = onClickPopupTagListener;
    }

    public interface OnClickPopupTagListener {
        void onClick(String tagName);
    }
}
