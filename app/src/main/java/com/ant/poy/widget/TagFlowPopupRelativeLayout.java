package com.ant.poy.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.TagFlowAdapter;
import com.ant.poy.adapter.TagFlowPopupAdapter;
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
public class TagFlowPopupRelativeLayout extends RelativeLayout {

    private List<String> tagsList = new ArrayList<String>();

    private List<String> tagsPopuList = new ArrayList<String>();

    private Context mContext;

    private View view;

    private TextView tv_kind;
    private ImageView img_more;

    private TagFlowLayout tagFlowLayout;

    private TagFlowAdapter tagFlowAdapter;

    private PopupTagFlow tagPopup;

    private TagFlowLayout tagFlowLayout_popup;

    private TagFlowPopupAdapter tagFlowPopupAdapter;

    private OnTagPopupClickListener onTagPopupClickListener;

    private String selectedTag;

    private int selected = 0;
    private int selected_pop_index = 0;


    public TagFlowPopupRelativeLayout(Context context) {
        this(context, null);
    }


    public TagFlowPopupRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowPopupRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.custom_tab_flow_layout, null);

        tv_kind = (TextView) view.findViewById(R.id.kind);
        img_more = (ImageView) view.findViewById(R.id.more);
        img_more.setVisibility(VISIBLE);

        tagFlowLayout = (TagFlowLayout) view.findViewById(R.id.tag_flowlayout);
        tagFlowLayout.setMaxSelectCount(1);
        tagFlowAdapter = new TagFlowAdapter(mContext, tagFlowLayout, tagsList);
        tagFlowLayout.setAdapter(tagFlowAdapter);

        addView(view);
        listener();


    }

    public void createPopup() {


        tagPopup = new PopupTagFlow((Activity) mContext);

        tagFlowLayout_popup = tagPopup.getTagFlowLayout();

        tagFlowLayout_popup.setMaxSelectCount(1);

        tagFlowPopupAdapter = new TagFlowPopupAdapter(mContext, tagFlowLayout_popup, tagsPopuList);

        tagFlowLayout_popup.setAdapter(tagFlowPopupAdapter);

        tagFlowLayout_popup.setOnTagClickListener(((view1, position, parent) -> {
            if (onTagPopupClickListener != null) {
                onTagPopupClickListener.onTagClick(tagsPopuList.get(position));
            }
            tagFlowAdapter.setNoSelected();

            setSelected(position);
            if (tagsList.indexOf(tagsPopuList.get(position)) == -1) {
                tagFlowAdapter.setNoSelected();
            } else tagFlowAdapter.setSelectedList(position);

            tagPopup.dismiss();
        }));

    }

    /**
     * b标签类型
     *
     * @param tagKind
     */
    public void setTagKind(String tagKind) {
        tv_kind.setText(tagKind);
    }

    public void hideKind() {
        tv_kind.setVisibility(GONE);
    }


    /**
     * 显示更多imageView
     *
     * @param resId
     */
    public void setImageResource(int resId) {
        img_more.setImageResource(resId);
    }

    /**
     * 标签
     *
     * @param list
     */
    public void setTagsList(List<String> list) {


        this.tagsList.clear();
        this.tagsList.addAll(list);

        if (tagFlowAdapter != null)
            tagFlowAdapter.notifyDataChanged();
    }

    /**
     * popup 标签
     *
     * @param list
     */
    public void setTagsPopuList(List<String> list) {


        this.tagsPopuList.clear();
        this.tagsPopuList.addAll(list);
        if (tagFlowPopupAdapter != null)
            tagFlowPopupAdapter.notifyDataChanged();
    }

    public void hideImgMore() {
        if (img_more != null) img_more.setVisibility(GONE);
    }

    public void showImgMore() {
        if (img_more != null) img_more.setVisibility(VISIBLE);
    }

    /**
     * tag点击监听事件
     */
    private void listener() {

        img_more.setOnClickListener(v -> {
            createPopup();
            tagPopup.showPopupWindow();
            tagFlowPopupAdapter.setSelectedList(getSelectedPopupPosition());
        });

        tagFlowLayout.setOnTagClickListener(((view1, position, parent) -> {
            if (onTagPopupClickListener != null) {
                onTagPopupClickListener.onTagClick(tagsList.get(position));
            }
            setSelected(position);
        }));


    }

    private void setSelected(int position) {
        this.selected = position;
    }

    private int getSelectedPosition() {
        return selected;
    }

    private int getSelectedPopupPosition() {
        return selected_pop_index;
    }

    private void setSelectedPopupPosition(int position) {
        this.selected_pop_index = position;
    }

    public void setSelectedTag(String tagStr) {
        if (tagStr == null) return;
        int position = tagsList.indexOf(tagStr);
        tagFlowAdapter.setSelectedList(position);
        setSelected(position);

    }

    public void setTagPopupSelected(String tagStr) {
        if (null == tagStr) return;

        int position = tagsPopuList.indexOf(tagStr);
        if (position < 0) ;

        setSelectedPopupPosition(position);
        setSelectedTag(tagStr);
    }

    public void setNoSelected() {
        tagFlowAdapter.setNoSelected();
    }


    public void setOnTagPopupClickListener(OnTagPopupClickListener onTagPopupClickListener) {
        this.onTagPopupClickListener = onTagPopupClickListener;
    }

    public interface OnTagPopupClickListener {
        void onTagClick(String tagStr);
    }

    public TagFlowAdapter getTagFlowAdapter() {
        return tagFlowAdapter;
    }

    public void setTagFlowAdapter(TagFlowAdapter tagFlowAdapter) {
        this.tagFlowAdapter = tagFlowAdapter;
    }
}
