package com.ant.poy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.adapter.base.RecyclerArrayAdapter;
import com.ant.poy.entity.GuWen;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.ui.AncientBookDetailActivity;
import com.ant.poy.utils.CommonUtil;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchGuWenAdapter extends RecyclerArrayAdapter<SearchEntity.GuJi> {



    /**
     * Constructor
     *
     * @param context The current context.
     */
    private String searchStr;
    public SearchGuWenAdapter(Context context,String searchStr) {
        super(context);
        this.searchStr = searchStr;
    }

    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<SearchEntity.GuJi>(viewGroup, R.layout.item_search_guwen_adapter) {
            @Override
            public void setData(SearchEntity.GuJi item,int position) {
                if (null != item) {

                    holder.setText(R.id.guwen_title_name, item.getNameStr());
                    TextView guwenTitleNameTv = holder.getView(R.id.guwen_title_name);

                    if (null == CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color))){
                        guwenTitleNameTv.setText(item.getNameStr());
                    }else guwenTitleNameTv.setText(CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color)));


                    String tempCont = Html.fromHtml(item.getCont()).toString().trim();
                    int index2 = tempCont.indexOf(searchStr);

                    String cont = null;
                    if (index2 > 8) {
                        cont = tempCont.substring(index2 - 8, tempCont.length());
                    } else cont = tempCont;

                    TextView textView = holder.getView(R.id.guwen_content);
                    /** 设置行间距*/
                    textView.setLineSpacing(CommonUtil.dpToPx(8),1);

                    if (null == CommonUtil.getSpannable(cont,searchStr,mContext.getResources().getColor(R.color.tag_selected_color))){
                        textView.setText(cont);
                    }else textView.setText( CommonUtil.getSpannable(cont,searchStr,mContext.getResources().getColor(R.color.tag_selected_color)));

                    /** 点击，古文详情*/
                    GuWen.Ancient ancient  = new GuWen.Ancient();
                    ancient.setId(item.getId());
                    ancient.setNameStr(item.getNameStr());
                    ancient.setCont(item.getCont());

                    holder.getView(R.id.guwen_title_name).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AncientBookDetailActivity.startIntent(mContext,ancient);
                        }
                    });

                }

            }
        };
    }

    private SpannableStringBuilder changeTextViewColor(String content, String targetStr) {
        if (content == null || content.length() < targetStr.length()) return null;

        int index = content.indexOf(targetStr);
        if (index < 0) return new SpannableStringBuilder(content);
        SpannableStringBuilder sBuilder = new SpannableStringBuilder(content);
        /** 第二个参数，开始位置，第二个参数结束位置*/
        sBuilder.setSpan(new ForegroundColorSpan(Color.RED), index, index + targetStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sBuilder;
    }


    public void setSearchContext(String content) {
        this.searchStr = content;
    }
}
