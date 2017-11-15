package com.ant.poy.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.adapter.base.RecyclerArrayAdapter;
import com.ant.poy.entity.Poet;
import com.ant.poy.entity.SearchEntity;
import com.ant.poy.ui.AuthorBriefActivity;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.ToastUtils;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/14
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchAuthorAdapter extends RecyclerArrayAdapter<SearchEntity.ZuoZhe> {
    /**
     * Constructor
     *
     * @param context The current context.
     */
    private String searchStr;

    public SearchAuthorAdapter(Context context, String searchStr) {
        super(context);
        this.searchStr = searchStr;
    }


    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<SearchEntity.ZuoZhe>(viewGroup, R.layout.item_author_adapter) {
            @Override
            public void setData(SearchEntity.ZuoZhe item, int position) {
                TextView authorNameTv = holder.getView(R.id.author_name_tv);

                if (null != CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color))){
                    authorNameTv.setText(CommonUtil.getSpannable(item.getNameStr(),searchStr,mContext.getResources().getColor(R.color.tag_selected_color)));
                }else authorNameTv.setText(item.getNameStr());


                TextView cont = (TextView) holder.getView(R.id.author_brief);

                if (null != item.getCont())
                    cont.setText(Html.fromHtml(item.getCont()).toString());
                /** 设置行间距*/
                cont.setLineSpacing(CommonUtil.dpToPx(8),1);


                // getView(R.id.nameStr).setOnClickListener(v1 -> PoetryDetailActivity.startIntent(mContext));

                /** 给cont添加文字复制功能*/
                setSelectableTextHelper(cont);

                /** 复制内容到剪切板*/
                holder.getView(R.id.copy).setOnClickListener(v -> {
                    CommonUtil.copyToClip(cont.getText().toString());
                    ToastUtils.showToast("拷贝《" + item.getNameStr() + "》成功");
                });

                ImageView mImageView = holder.getView(R.id.img_head);
                if (!TextUtils.isEmpty(item.getPic())) {
                    String url = "http://img.gushiwen.org/authorImg/" + item.getPic() + ".jpg";
                    CommonUtil.getImagerLoader().displayImage(url, mImageView);
                }


                Poet.Author author = new Poet.Author();
                author.setId(item.getId());
                author.setNameStr(item.getNameStr());
                author.setCont("ZuoZhe");
                holder.getView(R.id.img_head).setOnClickListener(v1 -> {
                    AuthorBriefActivity.startIntent(mContext, author);
                });

                holder.getView(R.id.author_name_tv).setOnClickListener(v -> {
                    AuthorBriefActivity.startIntent(mContext, author);
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

    public void setSearchContext(String content) {
        this.searchStr = content;
    }


}
