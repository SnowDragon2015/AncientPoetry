package com.ant.poy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.widget.flowlayout.FlowLayout;
import com.ant.poy.widget.flowlayout.TagAdapter;
import com.ant.poy.widget.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/15
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class TagFlowPopupAdapter extends TagAdapter<String> {

    private LayoutInflater layoutInflater;
    private TagFlowLayout tagFlowLayout;

    public TagFlowPopupAdapter(Context context, TagFlowLayout tagFlowLayout, List<String> datas) {
        super(datas);

        layoutInflater = LayoutInflater.from(context);
        this.tagFlowLayout = tagFlowLayout;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {

        TextView textView = (TextView) layoutInflater.inflate(R.layout.common_tags_popup_item, tagFlowLayout, false);
        textView.setText(s);

        return textView;
    }
}
