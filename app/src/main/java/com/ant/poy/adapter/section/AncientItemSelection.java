package com.ant.poy.adapter.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.entity.GuWen;
import com.ant.poy.ui.AncientBookDetailActivity;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/17
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientItemSelection extends StatelessSection {

    private List<GuWen.Ancient> ancientList = new ArrayList<GuWen.Ancient>();
    private Context mContext;

    public AncientItemSelection(Context context, List<GuWen.Ancient> ancients) {
        super(R.layout.item_ancient_book);
        this.ancientList = ancients;
        this.mContext = context;
    }

    @Override
    public int getContentItemsTotal() {
        return ancientList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemAncientHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemAncientHolder viewHolder = (ItemAncientHolder) holder;
        GuWen.Ancient item = ancientList.get(position);

        viewHolder.ancientBookNameTv.setText(item.getNameStr());

        viewHolder.bookBriefTv.setText(Html.fromHtml(item.getCont()));

        /** 设置行间距*/
        viewHolder.bookBriefTv.setLineSpacing(CommonUtil.dpToPx(8),1);

        // getView(R.id.nameStr).setOnClickListener(v1 -> PoetryDetailActivity.startIntent(mContext));

        /** 给cont添加文字复制功能*/
        setSelectableTextHelper(viewHolder.ancientBookNameTv);

        /** 复制内容到剪切板*/
        viewHolder.copyCkb.setOnClickListener(v -> {
            CommonUtil.copyToClip(viewHolder.ancientBookNameTv.getText().toString());
            ToastUtils.showToast("拷贝《" + item.getNameStr() + "》成功");
        });

        viewHolder.ancientBookNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Log.i("chyy","ancientBookNameTv 点击了我");
            }
        });
        viewHolder.ancientBookNameTv.setOnClickListener(viewStart -> {
            AncientBookDetailActivity.startIntent(mContext, item);
        });

        viewHolder.likeCkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.likeCkb.isChecked()) {

                    ToastUtils.showToast("收藏成功");
                    
                } else ToastUtils.showToast("取消收藏成功");

            }

        });
        viewHolder.praiseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("赞 +1");
            }
        });


    }

    static class ItemAncientHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ancient_book_name)
        TextView ancientBookNameTv;

        @BindView(R.id.ancient_book_brief)
        TextView bookBriefTv;

        @BindView(R.id.copy)
        CheckBox copyCkb;

        @BindView(R.id.like)
        CheckBox likeCkb;

        @BindView(R.id.praise)
        TextView praiseTv;

        public ItemAncientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
