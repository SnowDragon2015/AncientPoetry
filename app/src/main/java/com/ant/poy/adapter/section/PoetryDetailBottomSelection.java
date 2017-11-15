package com.ant.poy.adapter.section;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.entity.PoetryDetail;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.sectioned.StatelessSection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class PoetryDetailBottomSelection extends StatelessSection {

    private PoetryDetail.Poetry.Author author;

    public PoetryDetailBottomSelection(PoetryDetail.Poetry.Author author) {
        super(R.layout.item_author_adapter);
        this.author = author;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new TopItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopItemViewHolder viewHolder = (TopItemViewHolder) holder;
        viewHolder.authorNameTv.setText(author.getNameStr());

        viewHolder.authorBriefTv.setText(Html.fromHtml(author.getCont()));

        /** 设置行间距*/
        viewHolder.authorBriefTv.setLineSpacing(CommonUtil.dpToPx(8), 1);

        if (!TextUtils.isEmpty(author.getPic())) {
            String url = "http://img.gushiwen.org/authorImg/" + author.getPic() + ".jpg";
            CommonUtil.getImagerLoader().displayImage(url, viewHolder.headerImg);
        }

        /** 给cont添加文字复制功能*/
        setSelectableTextHelper(viewHolder.authorBriefTv);

        /** 复制内容到剪切板*/
        viewHolder.copyCkb.setOnClickListener(v -> {
            CommonUtil.copyToClip(author.getCont());
            ToastUtils.showToast("拷贝《" + author.getNameStr() + "》成功");
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

    static class TopItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.author_name_tv)
        TextView authorNameTv;

        @BindView(R.id.img_head)
        ImageView headerImg;

        @BindView(R.id.copy)
        CheckBox copyCkb;


        @BindView(R.id.like)
        CheckBox likeCkb;

        @BindView(R.id.praise)
        TextView praiseTv;


        @BindView(R.id.author_brief)
        TextView authorBriefTv;

        TopItemViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
