package com.ant.poy.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.adapter.base.BaseViewHolder;
import com.ant.poy.adapter.base.RecyclerArrayAdapter;
import com.ant.poy.entity.Recommend;
import com.ant.poy.ui.PoetryDetailActivity;
import com.ant.poy.utils.CommonUtil;
import com.ant.poy.utils.SharedPreference.PoetryPreference;
import com.ant.poy.utils.ToastUtils;
import com.ant.poy.widget.CustomHtmlTagHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class RecommendAdapter extends RecyclerArrayAdapter<Recommend.TuiJian> {
    /**
     * Constructor
     *
     * @param context The current context.
     */
    private Context context;

    private Map<String, String> selectedMap = new HashMap<String, String>();

    public RecommendAdapter(Context context) {
        super(context);

    }

    @Override
    public BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new BaseViewHolder<Recommend.TuiJian>(viewGroup, R.layout.item_recommend) {
            @Override
            public void setData(Recommend.TuiJian item,int position) {

                holder.setText(R.id.nameStr, item.getNameStr());

                TextView cont = holder.getView(R.id.cont);
                cont.setText(Html.fromHtml(item.getCont()).toString());
                TextView line = holder.getView(R.id.shang_line);
                TextView shang = holder.getView(R.id.shang);

                /** 设置行间距*/
                cont.setLineSpacing(CommonUtil.dpToPx(mContext,8),1);

                cont.setTextSize(TypedValue.COMPLEX_UNIT_SP, PoetryPreference.getInstence().getFontSize());
                shang.setTextSize(TypedValue.COMPLEX_UNIT_SP, PoetryPreference.getInstence().getFontSize());

                getView(R.id.nameStr).setOnClickListener(v1 -> PoetryDetailActivity.startIntent(mContext,item.getId(),null));

                /** 给cont添加文字复制功能*/
                setSelectableTextHelper(cont);


                holder.setText(R.id.author, item.getAuthor());
                holder.setText(R.id.dynasty, item.getChaodai());

                holder.getView(R.id.reference_data).setVisibility(View.GONE);

                /** 复制内容到剪切板*/
                holder.getView(R.id.copy).setOnClickListener(v -> {
                    CommonUtil.copyToClip(cont.getText().toString());
                    ToastUtils.showToast("拷贝《" + item.getNameStr() + "》成功");
                });

                /** 译*/
                CheckBox yi_box = holder.getView(R.id.translate);

                if (TextUtils.isEmpty(item.getYi())) yi_box.setVisibility(View.GONE);
                else yi_box.setVisibility(View.VISIBLE);

                if ("true".equals(selectedMap.get(position + "yi")))
                    yi_box.setChecked(true);
                else yi_box.setChecked(false);


                /** 注*/
                CheckBox zhu_box = holder.getView(R.id.annotation);

                if (TextUtils.isEmpty(item.getZhu())) zhu_box.setVisibility(View.GONE);
                else zhu_box.setVisibility(View.VISIBLE);

                if ("true".equals(selectedMap.get(position + "zhu")))
                    zhu_box.setChecked(true);
                else zhu_box.setChecked(false);


                /** 赏*/
                CheckBox shang_box = holder.getView(R.id.appreciate);

                if (TextUtils.isEmpty(item.getShang())) shang_box.setVisibility(View.GONE);
                else shang_box.setVisibility(View.VISIBLE);

                if ("true".equals(selectedMap.get(position + "shang"))) {
                    shang_box.setChecked(true);
                    line.setVisibility(View.VISIBLE);
                    shang.setVisibility(View.VISIBLE);
                    shang.setLineSpacing(CommonUtil.dpToPx(mContext,8),1);
                } else {
                    shang_box.setChecked(false);
                    line.setVisibility(View.GONE);
                    shang.setVisibility(View.GONE);
                }


                yi_box.setOnCheckedChangeListener(((buttonView, isChecked) -> {
                    if (isChecked) {
                        selectedMap.put(position + "yi", "true");
                    } else selectedMap.remove(position + "yi");

                    changeCont(yi_box, zhu_box, shang_box, cont, item, shang, line);
                }));

                zhu_box.setOnCheckedChangeListener(((buttonView, isChecked) -> {

                    if (isChecked) {
                        selectedMap.put(position + "zhu", "true");
                    } else selectedMap.remove(position + "zhu");

                    changeCont(yi_box, zhu_box, shang_box, cont, item, shang, line);
                }));

                shang_box.setOnCheckedChangeListener(((buttonView, isChecked) -> {

                    if (isChecked) {
                        selectedMap.put(position + "shang", "true");
                        shang.setVisibility(View.VISIBLE);
                        line.setVisibility(View.VISIBLE);
                    } else {
                        selectedMap.remove(position + "shang");
                        shang.setVisibility(View.GONE);
                        line.setVisibility(View.GONE);
                    }

                    changeCont(yi_box, zhu_box, shang_box, cont, item, shang, line);
                }));

                /** 诗词标签*/
                if (TextUtils.isEmpty(item.getTag())){
                    holder.getView(R.id.line1).setVisibility(View.GONE);
                    holder.getView(R.id.tag).setVisibility(View.GONE);
                }else {
                    holder.setText(R.id.tag, item.getTag().toString().replace("|", "，"));
                    holder.getView(R.id.line1).setVisibility(View.VISIBLE);

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
    private void changeCont(CheckBox yi_box, CheckBox zhu_box, CheckBox shang_box, TextView textView, Recommend.TuiJian shiWen, TextView shang, TextView line) {

        if (!yi_box.isChecked() && !zhu_box.isChecked() && !shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getCont()));

        } else if (yi_box.isChecked() && !zhu_box.isChecked() && !shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getYi(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (yi_box.isChecked() && zhu_box.isChecked() && !shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getYizhu(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (yi_box.isChecked() && !zhu_box.isChecked() && shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getYi(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));
            shang.setText(Html.fromHtml(shiWen.getYishang().substring(shiWen.getYi().length()), null,
                    new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (yi_box.isChecked() && zhu_box.isChecked() && shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getYizhu(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));
            shang.setText(Html.fromHtml(shiWen.getYizhushang().substring(shiWen.getYizhu().length()), null,
                    new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (!yi_box.isChecked() && zhu_box.isChecked() && !shang_box.isChecked()) {
            textView.setText(Html.fromHtml(shiWen.getZhu(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (!yi_box.isChecked() && zhu_box.isChecked() && shang_box.isChecked()) {

            textView.setText(Html.fromHtml(shiWen.getZhu(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));
            shang.setText(Html.fromHtml(shiWen.getZhushang().substring(shiWen.getZhu().length()), null,
                    new CustomHtmlTagHandler(context, textView.getTextColors())));

        } else if (!yi_box.isChecked() && !zhu_box.isChecked() && shang_box.isChecked()) {

            textView.setText(Html.fromHtml(shiWen.getCont(), null, new CustomHtmlTagHandler(context, textView.getTextColors())));
            shang.setText(Html.fromHtml(shiWen.getShang().substring(shiWen.getCont().length()), null,
                    new CustomHtmlTagHandler(context, textView.getTextColors())));


        }


    }


}
