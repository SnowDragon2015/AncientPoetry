package com.ant.poy.adapter.section;

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
public class HeaderTagPoetrySelection extends StatelessSection {
    private OnClickTagListener onClickTagListener;


    public HeaderTagPoetrySelection(OnClickTagListener onClickTagListener) {
        super(R.layout.item_poetry_header_adapter);
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
        tagHolder.tagFPL_kind.setTagsList(StringUtils.getKindList());
        tagHolder.tagFPL_kind.setTagsPopuList(StringUtils.getAllKindList());
        tagHolder.tagFPL_kind.setTagKind("类型");


        tagHolder.tagFPL_dynasty.setTagsList(StringUtils.getDynatsyList());
        tagHolder.tagFPL_dynasty.setTagsPopuList(StringUtils.getAllDynatsyList());
        tagHolder.tagFPL_dynasty.setTagKind("朝代");

        tagHolder.tagFL_form.setTagsList(StringUtils.getFormList());
        tagHolder.tagFL_form.setTagsPopuList(StringUtils.getFormList());
        tagHolder.tagFL_form.hideImgMore();
        tagHolder.tagFL_form.setTagKind("形式");

        listener(tagHolder.tagFPL_kind,tagHolder.tagFPL_dynasty,tagHolder.tagFL_form,tagHolder.tag_term_tv);
        setDefauleSelected(tagHolder.tagFPL_kind,tagHolder.tagFPL_dynasty,tagHolder.tagFL_form,tagHolder.tag_term_tv);

    }

    private void listener(TagFlowPopupRelativeLayout tagFPL_kind,
                          TagFlowPopupRelativeLayout tagFPL_dynasty,
                          TagFlowPopupRelativeLayout tagFL_form,TextView tag_tem_tv) {
        tagFPL_kind.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsKind(tagStr);
            setDefauleSelected(tagFPL_kind,tagFPL_dynasty,tagFL_form,tag_tem_tv);
            callOnTagClick(tagStr);
        });

        tagFPL_dynasty.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsDynasty(tagStr);
            setDefauleSelected(tagFPL_kind,tagFPL_dynasty,tagFL_form,tag_tem_tv);
            callOnTagClick(tagStr);
        });

        tagFL_form.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsForm(tagStr);
            setDefauleSelected(tagFPL_kind,tagFPL_dynasty,tagFL_form,tag_tem_tv);
            callOnTagClick(tagStr);
        });
    }

    private void setDefauleSelected(TagFlowPopupRelativeLayout tagFPL_kind,
                                    TagFlowPopupRelativeLayout tagFPL_dynasty,
                                    TagFlowPopupRelativeLayout tagFL_form,TextView tag_term_tv) {

        tagFPL_kind.setTagPopupSelected(PoetryPreference.getInstence().getTagsKind());

        tagFPL_dynasty.setTagPopupSelected(PoetryPreference.getInstence().getTagsDynasty());

        tagFL_form.setTagPopupSelected(PoetryPreference.getInstence().getTagsForm());

        tag_term_tv.setText(StringUtils.getPoetryTagsTerm());
    }

    static class PoetryHeaderTagHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tag_fp_kind)
        TagFlowPopupRelativeLayout tagFPL_kind;

        @BindView(R.id.tag_fp_dynasty)
        TagFlowPopupRelativeLayout tagFPL_dynasty;

        @BindView(R.id.tag_fp_form)
        TagFlowPopupRelativeLayout tagFL_form;

        @BindView(R.id.selected_term)
        TextView tag_term_tv;

        public PoetryHeaderTagHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private void callOnTagClick(String tagName){
        if (onClickTagListener != null){
            onClickTagListener.OnClick(tagName);
        }
    }



}
