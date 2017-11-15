package com.ant.poy.adapter.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.OnClickTagListener;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.StringUtils;
import com.ant.poy.widget.TagFlowPopupRelativeLayout;
import com.ant.poy.widget.sectioned.StatelessSection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/17
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class HeaderTagAncientBookSelection extends StatelessSection {
    private Context mContext;
    private OnClickTagListener onClickTagListener;


    public HeaderTagAncientBookSelection(Context context, OnClickTagListener onClickTagListener) {
        super(R.layout.item_ancient_book_header_adapter);
        mContext = context;
        this.onClickTagListener = onClickTagListener;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new PoetryHeaderTagHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        PoetryHeaderTagHolder tagHolder = (PoetryHeaderTagHolder) holder;

        tagHolder.tagFPL_jingbu.setTagsList(StringUtils.getJIngBuList());
        tagHolder.tagFPL_jingbu.setTagsPopuList(StringUtils.getJIngBuAllList());
        tagHolder.tagFPL_jingbu.hideKind();

        tagHolder.tagFPL_shibu.setTagsList(StringUtils.getShiBuList());
        tagHolder.tagFPL_shibu.setTagsPopuList(StringUtils.getShiBuAllList());
        tagHolder.tagFPL_shibu.setTagKind(mContext.getString(R.string.ancient_shibu));
        tagHolder.tagFPL_shibu.hideKind();

        tagHolder.tagFPL_zibu.setTagsList(StringUtils.getZiBuList());
        tagHolder.tagFPL_zibu.setTagsPopuList(StringUtils.getZiBuAllList());
        tagHolder.tagFPL_zibu.setTagKind(mContext.getString(R.string.ancient_zibu));
        tagHolder.tagFPL_zibu.hideKind();

        tagHolder.tagFPL_jibu.setTagsList(StringUtils.getJiBuList());
        tagHolder.tagFPL_jibu.setTagsPopuList(StringUtils.getJiBuAllList());
        tagHolder.tagFPL_jibu.setTagKind(mContext.getString(R.string.ancient_jibu));
        tagHolder.tagFPL_jibu.hideKind();


        listener(tagHolder.tagFPL_jingbu, tagHolder.tagFPL_shibu, tagHolder.tagFPL_zibu, tagHolder.tagFPL_jibu, tagHolder.tag_term_tv);
        setDefauleSelected(tagHolder.tagFPL_jingbu, tagHolder.tagFPL_shibu, tagHolder.tagFPL_zibu, tagHolder.tagFPL_jibu, tagHolder.tag_term_tv);

    }

    private void listener(TagFlowPopupRelativeLayout tagFPL_jingbu,
                          TagFlowPopupRelativeLayout tagFPL_shibu,
                          TagFlowPopupRelativeLayout tagFPL_zibu,
                          TagFlowPopupRelativeLayout tagFPL_jibu, TextView tag_tem_tv) {

        tagFPL_jingbu.setOnTagPopupClickListener(tagStr -> {

            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected(tagFPL_jingbu, tagFPL_shibu, tagFPL_zibu, tagFPL_jibu, tag_tem_tv);
            callOnTagClick(tagStr);
        });
        tagFPL_shibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected(tagFPL_jingbu, tagFPL_shibu, tagFPL_zibu, tagFPL_jibu, tag_tem_tv);
            callOnTagClick(tagStr);
        });
        tagFPL_zibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected(tagFPL_jingbu, tagFPL_shibu, tagFPL_zibu, tagFPL_jibu, tag_tem_tv);
            callOnTagClick(tagStr);
        });
        tagFPL_jibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected(tagFPL_jingbu, tagFPL_shibu, tagFPL_zibu, tagFPL_jibu, tag_tem_tv);
            callOnTagClick(tagStr);
        });
    }

    private void setDefauleSelected(TagFlowPopupRelativeLayout tagFPL_jingbu,
                                    TagFlowPopupRelativeLayout tagFPL_shibu,
                                    TagFlowPopupRelativeLayout tagFPL_zibu,
                                    TagFlowPopupRelativeLayout tagFPL_jibu, TextView tag_tem_tv) {

       // tagFPL_jingbu.setSelectedTag(PoetryPreference.getInstence().getTagAncient());

        tagFPL_jingbu.setTagPopupSelected(PoetryPreference.getInstence().getTagAncient());
        tagFPL_shibu.setTagPopupSelected(PoetryPreference.getInstence().getTagAncient());
        tagFPL_zibu.setTagPopupSelected(PoetryPreference.getInstence().getTagAncient());
        tagFPL_jibu.setTagPopupSelected(PoetryPreference.getInstence().getTagAncient());

        tag_tem_tv.setText(StringUtils.getAncientBookTagsTerm());
    }

    static class PoetryHeaderTagHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tag_fp_jingbu)
        TagFlowPopupRelativeLayout tagFPL_jingbu;

        @BindView(R.id.tag_fp_shibu)
        TagFlowPopupRelativeLayout tagFPL_shibu;

        @BindView(R.id.tag_fp_zibu)
        TagFlowPopupRelativeLayout tagFPL_zibu;

        @BindView(R.id.tag_fp_jibu)
        TagFlowPopupRelativeLayout tagFPL_jibu;

        @BindView(R.id.selected_term)
        TextView tag_term_tv;

        public PoetryHeaderTagHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void callOnTagClick(String tagName) {
        if (onClickTagListener != null) {
            onClickTagListener.OnClick(tagName);
        }
    }


}
