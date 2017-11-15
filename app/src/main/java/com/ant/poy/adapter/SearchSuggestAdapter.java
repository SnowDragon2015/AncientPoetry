package com.ant.poy.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.adapter.base.RecyclerArrayAdapter;
import com.ant.poy.entity.GuWen;
import com.ant.poy.entity.Poet;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.ui.AncientBookDetailActivity;
import com.ant.poy.ui.AuthorBriefActivity;
import com.ant.poy.ui.PoetryDetailActivity;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.MeasureHeightListView;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/22
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchSuggestAdapter extends RecyclerArrayAdapter<Object> {
    private String searchContent;

    /**
     * Constructor
     *
     * @param context The current context.
     */
    private Context mContext;

    public SearchSuggestAdapter(Context context) {
        super(context);
        mContext = context;
    }

    public void setSeachContent(String seachContent) {
        this.searchContent = seachContent;
    }


    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder<List<Object>>(viewGroup, R.layout.item_search_suggest) {
            @Override
            public void setData(List item, int position) {


                if (item.get(0) instanceof SearchEntity.ShiWen) {
                    setRecycleList(holder, item, mContext);
                    holder.setText(R.id.type_name, "诗文");

                } else if (item.get(0) instanceof SearchEntity.MingJu) {

                    setRecycleList(holder, item, mContext);
                    holder.setText(R.id.type_name, "名句");

                } else if (item.get(0) instanceof SearchEntity.ZuoZhe) {

                    setRecycleList(holder, item, mContext);
                    holder.setText(R.id.type_name, "作者");

                } else if (item.get(0) instanceof SearchEntity.GuJi) {

                    setRecycleList(holder, item, mContext);
                    holder.setText(R.id.type_name, "古籍");
                }

            }
        };
    }

    private void setRecycleList(BaseViewHolder<List<Object>> holder, List item, Context mContext) {

        MeasureHeightListView listView = holder.getView(R.id.list_view);

        SIAdapter siAdapter = new SIAdapter(item, mContext);
        listView.setAdapter(siAdapter);


    }


    private class SIAdapter extends BaseAdapter {
        private List item;
        private LayoutInflater layoutInflater;

        public SIAdapter(List item, Context context) {
            this.item = item;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HolderView holderView = null;

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item_search_suggest_item, null);
                holderView = new HolderView();
                holderView.textView = (TextView) convertView.findViewById(R.id.search_suggest_item_tv);
                holderView.line = (TextView) convertView.findViewById(R.id.line);

                convertView.setTag(holderView);
            } else {
                holderView = (HolderView) convertView.getTag();
            }
            Object object = item.get(position);

            if (position == item.size() - 1) holderView.line.setVisibility(View.GONE);
            else holderView.line.setVisibility(View.VISIBLE);

            if (object instanceof SearchEntity.ShiWen) {
                /** 诗文*/
                SearchEntity.ShiWen shiWen = (SearchEntity.ShiWen) object;
                String brief = shiWen.getNameStr();
                String author = shiWen.getAuthor();

                String nameStr = brief + " - " + author;

                int index = nameStr.indexOf(searchContent);
                if (index > -1) {
                    SpannableStringBuilder sBuilder = new SpannableStringBuilder(nameStr);
                    sBuilder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.tag_selected_color)), index, index + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    sBuilder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.gray_light_AE)), brief.length(), nameStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    holderView.textView.setText(sBuilder);
                }

                holderView.textView.setOnClickListener(v -> PoetryDetailActivity.startIntent(mContext, shiWen.getId(),null));

            } else if (object instanceof SearchEntity.MingJu) {
                /** 名句*/
                SearchEntity.MingJu mingJus = (SearchEntity.MingJu) object;

                if (null != CommonUtil.getSpannable(mingJus.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color))){
                    holderView.textView.setText(CommonUtil.getSpannable(mingJus.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else holderView.textView.setText(mingJus.getNameStr());

                holderView.textView.setOnClickListener(v -> PoetryDetailActivity.startIntent(mContext, mingJus.getShiID(),mingJus.getNameStr()));

            } else if (object instanceof SearchEntity.ZuoZhe) {
                /** 作者*/
                SearchEntity.ZuoZhe zuoZhe = (SearchEntity.ZuoZhe) object;

                if (null != CommonUtil.getSpannable(zuoZhe.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color))){
                    holderView.textView.setText(CommonUtil.getSpannable(zuoZhe.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else holderView.textView.setText(zuoZhe.getNameStr());

                Poet.Author author = new Poet.Author();
                author.setId(zuoZhe.getId());
                author.setNameStr(zuoZhe.getNameStr());
                author.setCont("ZuoZhe");

                holderView.textView.setOnClickListener(v -> AuthorBriefActivity.startIntent(mContext, author));

            } else if (object instanceof SearchEntity.GuJi) {
                /** 古籍*/
                SearchEntity.GuJi book = (SearchEntity.GuJi) object;

                if (null != CommonUtil.getSpannable(book.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color))){
                    holderView.textView.setText(CommonUtil.getSpannable(book.getNameStr(),searchContent,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else holderView.textView.setText(book.getNameStr());


                GuWen.Ancient ancient = new GuWen.Ancient();
                ancient.setNameStr(book.getNameStr());
                ancient.setId(book.getId());
                ancient.setCont(book.getCont());
                holderView.textView.setOnClickListener(v -> AncientBookDetailActivity.startIntent(mContext, ancient));
            }


            return convertView;
        }


    }



    public static class HolderView {
        TextView textView;
        TextView line;
    }


}
