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
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.ui.PoetryDetailActivity;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.StringUtils;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.TagFlowPopupRelativeLayout;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/14
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchPoetryAdapter extends BaseRecycleViewAdapter<SearchEntity.ShiWen> {
    /**
     * Constructor
     *
     * @param context The current context.
     */
    private String searchStr;

    public SearchPoetryAdapter(Context context, String searchStr) {
        super(context);
        this.searchStr = searchStr;
    }

    private OnClickTagListener onClickTagListener;


    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<SearchEntity.ShiWen>(viewGroup, R.layout.item_recommend) {
            @Override
            public void setData(SearchEntity.ShiWen item, int position) {

                /**标题*/
                TextView nameTv = holder.getView(R.id.nameStr);

                if (null != CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color))){
                    nameTv.setText(CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else nameTv.setText(item.getNameStr());

                nameTv.setOnClickListener(v1 -> PoetryDetailActivity.startIntent(mContext, item.getId(),null));

                /** 内容*/
                TextView cont = holder.getView(R.id.cont);
                cont.setText(Html.fromHtml(item.getCont()).toString());

                /** 设置行间距*/
                cont.setLineSpacing(CommonUtil.dpToPx(8),1);


                /** 给cont添加文字复制功能*/
                setSelectableTextHelper(cont);


                holder.setText(R.id.author, item.getAuthor());

                TextView authorTv = holder.getView(R.id.author);
                if (null != CommonUtil.getSpannable(item.getAuthor(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color))){
                    authorTv.setText(CommonUtil.getSpannable(item.getAuthor(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else authorTv.setText(item.getNameStr());




                holder.setText(R.id.dynasty, item.getChaodai());

                holder.getView(R.id.reference_data).setVisibility(View.GONE);

                /** 复制内容到剪切板*/
                holder.getView(R.id.copy).setOnClickListener(v -> {
                    CommonUtil.copyToClip(cont.getText().toString());
                    ToastUtils.showToast("拷贝《" + item.getNameStr() + "》成功");
                });

                /** 译*/
                CheckBox trans = holder.getView(R.id.translate);
                trans.setVisibility(View.GONE);
                trans.setOnCheckedChangeListener(((buttonView, isChecked) -> {

                }));
                /** 注*/
                CheckBox annotation = holder.getView(R.id.annotation);
                annotation.setVisibility(View.GONE);
                annotation.setOnCheckedChangeListener(((buttonView, isChecked) -> {

                }));
                /** 赏*/
                CheckBox appreciate = holder.getView(R.id.appreciate);
                appreciate.setVisibility(View.GONE);
                appreciate.setOnCheckedChangeListener(((buttonView, isChecked) -> {

                }));

                holder.getView(R.id.shang_line).setVisibility(View.GONE);

                if (item.getTag()!=null){
                    holder.setText(R.id.tag,item.getTag().toString().replace("|","，"));
                    holder.getView(R.id.tag).setVisibility(View.VISIBLE);
                    holder.getView(R.id.line1).setVisibility(View.VISIBLE);
                }else {
                    holder.getView(R.id.tag).setVisibility(View.GONE);
                    holder.getView(R.id.line1).setVisibility(View.GONE);
                }


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
        return new BaseViewHolder(viewGroup, null);
    }

    private TagFlowPopupRelativeLayout tagFPL_kind, tagFPL_dynasty, tagFL_form;


    private TextView tag_term_tv;

    @Override
    public BaseViewHolder OnCreaeHeaderViewHolder(ViewGroup viewGroup, View viewType) {

        return new BaseViewHolder(viewGroup, R.layout.item_poetry_header_adapter) {
            @Override
            public void setData(Object item) {

                tag_term_tv = (TextView) holder.getView(R.id.selected_term);

                tagFPL_kind = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_kind);
                tagFPL_kind.setTagsList(StringUtils.getKindList());
                tagFPL_kind.setTagsPopuList(StringUtils.getAllKindList());
                tagFPL_kind.setTagKind("类型");

                tagFPL_dynasty = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_dynasty);

                tagFPL_dynasty.setTagsList(StringUtils.getDynatsyList());
                tagFPL_dynasty.setTagsPopuList(StringUtils.getAllDynatsyList());
                tagFPL_dynasty.setTagKind("朝代");

                tagFL_form = (TagFlowPopupRelativeLayout) holder.getView(R.id.tag_fp_form);
                tagFL_form.setTagsList(StringUtils.getFormList());
                tagFL_form.setTagsPopuList(StringUtils.getFormList());
                tagFL_form.hideImgMore();
                tagFL_form.setTagKind("类型");

                listener();
                setDefauleSelected();


            }
        };
    }

    private void listener() {
        tagFPL_kind.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsKind(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });

        tagFPL_dynasty.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsDynasty(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });

        tagFL_form.setOnTagPopupClickListener(tagStr -> {
            PoetryPreference.getInstence().putTagsForm(tagStr);
            setDefauleSelected();
            callOnTagClick(tagStr);
        });
    }

    private void setDefauleSelected() {

        tagFPL_kind.setTagPopupSelected(PoetryPreference.getInstence().getTagsKind());

        tagFPL_dynasty.setTagPopupSelected(PoetryPreference.getInstence().getTagsDynasty());

        tagFL_form.setTagPopupSelected(PoetryPreference.getInstence().getTagsForm());

        tag_term_tv.setText(StringUtils.getPoetryTagsTerm());
    }

    private void callOnTagClick(String tagName) {
        if (onClickTagListener != null) {
            onClickTagListener.OnClick(tagName);
        }
    }

    public void setSearchContext(String content) {
        this.searchStr = content;
    }

    public void setOnClickTagListener(OnClickTagListener onClickTagListener) {
        this.onClickTagListener = onClickTagListener;
    }

    public interface OnClickTagListener {
        void OnClick(String tagName);
    }
}
