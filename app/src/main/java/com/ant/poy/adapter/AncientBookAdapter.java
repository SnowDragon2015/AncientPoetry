package com.ant.poy.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseRecycleViewAdapter;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.entity.AncientBook;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.StringUtils;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.CustomEmptyView;
import com.ant.poy.widget.TagFlowPopupRelativeLayout;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/14
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientBookAdapter extends BaseRecycleViewAdapter<AncientBook.Book> {
    /**
     * Constructor
     *
     * @param context The current context.
     */
    public AncientBookAdapter(Context context) {
        super(context);
    }

    private OnClickTagListener onClickTagListener;


    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<AncientBook.Book>(viewGroup, R.layout.item_ancient_book) {
            @Override
            public void setData(AncientBook.Book item) {

                holder.setText(R.id.ancient_book_name, item.getNameStr());

                TextView cont = holder.getView(R.id.ancient_book_brief);
                cont.setText(Html.fromHtml(item.getCont()).toString());

                // getView(R.id.nameStr).setOnClickListener(v1 -> PoetryDetailActivity.startIntent(mContext));

                /** 给cont添加文字复制功能*/
                setSelectableTextHelper(cont);

                /** 复制内容到剪切板*/
                holder.getView(R.id.copy).setOnClickListener(v -> {
                    CommonUtil.copyToClip(cont.getText().toString());
                    ToastUtils.showToast("拷贝《" + item.getNameStr() + "》成功");
                });

                CheckBox lickCkb = holder.getView(R.id.like);
                lickCkb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lickCkb.isChecked()) {
                            ToastUtils.showToast("收藏成功");
                        } else {

                            ToastUtils.showToast("取消收藏成功");
                        }

                    }
                });

                holder.getView(R.id.praise).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("赞 +1");
                    }
                });


            }
        };
    }

    @Override
    public BaseViewHolder OnCreaeFootViewHolder(ViewGroup viewGroup, View viewType) {
        return new BaseViewHolder(viewGroup, R.layout.item_empty) {
            @Override
            public void setData(Object item) {
                super.setData(item);
                CustomEmptyView customEmptyView = (CustomEmptyView) holder.getView(R.id.custom_empty);
                customEmptyView.setEmptyText(StringUtils.getPoetryTagsTerm() + " 暂无数据");
                customEmptyView.setTextColor(R.color.gray);

            }
        };
    }

    private TagFlowPopupRelativeLayout tagFPL_jingbu, tagFPL_shibu, tagFPL_zibu, tagFPL_jibu;


    private TextView tag_term_tv;

    @Override
    public BaseViewHolder OnCreaeHeaderViewHolder(ViewGroup viewGroup, View viewType) {

        return new BaseViewHolder(viewGroup, R.layout.item_ancient_book_header_adapter) {
            @Override
            public void setData(Object item) {

                tag_term_tv = (TextView) holder.getView(R.id.selected_term);


                tagFPL_jingbu = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_jingbu);
                tagFPL_jingbu.setTagsList(StringUtils.getJIngBuList());
                tagFPL_jingbu.setTagsPopuList(StringUtils.getJIngBuAllList());
                tagFPL_jingbu.setTagKind(mContext.getString(R.string.ancient_jingbu));

                tagFPL_shibu = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_shibu);
                tagFPL_shibu.setTagsList(StringUtils.getShiBuList());
                tagFPL_shibu.setTagsPopuList(StringUtils.getShiBuAllList());
                tagFPL_shibu.setTagKind(mContext.getString(R.string.ancient_shibu));

                tagFPL_zibu = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_zibu);
                tagFPL_zibu.setTagsList(StringUtils.getZiBuList());
                tagFPL_zibu.setTagsPopuList(StringUtils.getZiBuAllList());
                tagFPL_zibu.setTagKind(mContext.getString(R.string.ancient_zibu));

                tagFPL_jibu = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_jibu);
                tagFPL_jibu.setTagsList(StringUtils.getJiBuList());
                tagFPL_jibu.setTagsPopuList(StringUtils.getJiBuAllList());
                tagFPL_jibu.setTagKind(mContext.getString(R.string.ancient_jibu));


                listener();
                setDefauleSelected();


            }
        };
    }

    private void listener() {

        tagFPL_jingbu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });
        tagFPL_shibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });
        tagFPL_zibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });
        tagFPL_jibu.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagAncient(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });

    }

    private void setDefauleSelected() {


        tagFPL_jingbu.setSelectedTag(PoetryPreference.getInstence().getTagAncient());
        tagFPL_shibu.setSelectedTag(PoetryPreference.getInstence().getTagAncient());
        tagFPL_zibu.setSelectedTag(PoetryPreference.getInstence().getTagAncient());
        tagFPL_jibu.setSelectedTag(PoetryPreference.getInstence().getTagAncient());

        tag_term_tv.setText(StringUtils.getAncientBookTagsTerm());
    }

    private void callOnTagClick(String tagName) {
        if (onClickTagListener != null) {
            onClickTagListener.OnClick(tagName);
        }
    }

    public void setOnClickTagListener(OnClickTagListener onClickTagListener) {
        this.onClickTagListener = onClickTagListener;
    }

    public interface OnClickTagListener {
        void OnClick(String tagName);
    }
}
