package com.ant.poy.adapter.section;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.entity.Poet;
import com.ant.poy.utils.AppUtils;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.widget.CustomHtmlTagHandler;
import com.ant.poy.widget.sectioned.StatelessSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AuthorDetailItemSelection extends StatelessSection {

    public Map<String, Integer> hashMap = new HashMap<String, Integer>();

    private List<Poet.Author.Introduce> introduceList;
    private final SparseBooleanArray mCollapsedStatus;

    public AuthorDetailItemSelection(List<Poet.Author.Introduce> introduceList) {
        super(R.layout.item_selection_author_story);
        this.introduceList = introduceList;
        mCollapsedStatus = new SparseBooleanArray();
    }

    @Override
    public int getContentItemsTotal() {
        return introduceList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;

        Poet.Author.Introduce introduce = introduceList.get(position);
        viewHolder.nameStrTv.setText(introduce.getNameStr());
        Spanned spanned = (Html.fromHtml(introduce.getCont(), null, new CustomHtmlTagHandler(AppUtils.getAppContext(), viewHolder.contTv.getTextColors())));

        /** 设置行间距*/
        viewHolder.contTv.setLineSpacing(CommonUtil.dpToPx(8),1);

        if (spanned.length() > 240) {
            viewHolder.assitsTv.setVisibility(View.INVISIBLE);
            if (hashMap.get(introduce.getNameStr() + position) == null) {

                viewHolder.lookMoreTv.setText("查看更多");
                viewHolder.contTv.setText(spanned.subSequence(0, 180));
                viewHolder.shapeTv.setVisibility(View.VISIBLE);
                viewHolder.lookMoreTv.setVisibility(View.VISIBLE);

            } else {
                viewHolder.contTv.setText(spanned);
                viewHolder.lookMoreTv.setText("收起");
                viewHolder.shapeTv.setVisibility(View.GONE);
                hashMap.put(introduce.getNameStr() + position, position);
            }


            viewHolder.lookMoreTv.setOnClickListener(v -> {
                /** 展开*/
                if ("查看更多".equals(viewHolder.lookMoreTv.getText())) {
                    viewHolder.contTv.setText(spanned);
                    viewHolder.lookMoreTv.setText("收起");
                    viewHolder.shapeTv.setVisibility(View.GONE);
                    hashMap.put(introduce.getNameStr() + position, position);
                } else {
                    /** 收起*/
                    viewHolder.lookMoreTv.setText("查看更多");
                    viewHolder.contTv.setText(spanned.subSequence(0, 240));

                    viewHolder.shapeTv.setVisibility(View.VISIBLE);
                    viewHolder.lookMoreTv.setVisibility(View.VISIBLE);

                    hashMap.remove(introduce.getNameStr() + position);
                }

            });

        } else {
            viewHolder.contTv.setText(spanned);
            viewHolder.shapeTv.setVisibility(View.GONE);
            viewHolder.lookMoreTv.setVisibility(View.GONE);
            viewHolder.assitsTv.setVisibility(View.GONE);
        }



    }

     class ItemViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.nameStr)
         TextView nameStrTv;

         @BindView(R.id.cont)
         TextView contTv;

         @BindView(R.id.shape)
         TextView shapeTv;

         @BindView(R.id.look_more)
         TextView lookMoreTv;

         @BindView(R.id.id_assist)
         TextView assitsTv;



        public ItemViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
