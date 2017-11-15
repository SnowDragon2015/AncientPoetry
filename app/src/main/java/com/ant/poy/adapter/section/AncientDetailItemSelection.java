package com.ant.poy.adapter.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.entity.AncientBookDetail;
import com.ant.poy.ui.AncientBookDetailContentActivity;
import com.ant.poy.widget.flowlayout.FlowLayout;
import com.ant.poy.widget.flowlayout.TagAdapter;
import com.ant.poy.widget.flowlayout.TagFlowLayout;
import com.ant.poy.widget.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/13
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class AncientDetailItemSelection extends StatelessSection {

    private List<AncientBookDetail.AncientInfo> ancientInfoList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    private List<String> keyList = new ArrayList<String>();
    private HashMap<String, List<AncientBookDetail.AncientInfo>> hashList = new HashMap<String, List<AncientBookDetail.AncientInfo>>();


    public AncientDetailItemSelection(Context context, AncientBookDetail ancientBookDetail) {
        super(R.layout.item_selection_ancient_detail);
        this.ancientInfoList = ancientBookDetail.getGuwenInfo();
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        getKeyList();
    }

    @Override
    public int getContentItemsTotal() {
        return keyList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new TopItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopItemViewHolder viewHolder = (TopItemViewHolder) holder;
        String key = keyList.get(position);
        if ("wu".equals(key)) {
            viewHolder.chapterNameTv.setVisibility(View.GONE);
        } else viewHolder.chapterNameTv.setVisibility(View.VISIBLE);

        viewHolder.chapterNameTv.setText(key);
        List<AncientBookDetail.AncientInfo> infoList = hashList.get(key);

        viewHolder.tagFlowLayout.setAdapter(new TagAdapter<AncientBookDetail.AncientInfo>(infoList) {
            @Override
            public View getView(FlowLayout parent, int position, AncientBookDetail.AncientInfo ancientInfoList) {
                TextView textView = (TextView) layoutInflater.inflate(R.layout.common_tags_ancient_detail, parent, false);
                textView.setText(ancientInfoList.getNameStr());
                return textView;
            }
        });

        viewHolder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                AncientBookDetailContentActivity.startIntent(mContext, infoList.get(position));
            }
        });

    }

    private List<String> getTagList(List<AncientBookDetail.AncientInfo> list) {
        List<String> tagList = new ArrayList<String>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                tagList.add(list.get(i).getNameStr());
            }
        }

        return tagList;
    }

    private void getKeyList() {
        for (int i = 0; i < ancientInfoList.size(); i++) {
            AncientBookDetail.AncientInfo ancientinfo = ancientInfoList.get(i);
            if (TextUtils.isEmpty(ancientinfo.getFenlei())) {
                if (keyList.indexOf("wu") < 0) keyList.add("wu");

            } else {
                if (keyList.indexOf(ancientinfo.getFenlei()) < 0)
                    keyList.add(ancientinfo.getFenlei());
            }
        }


        getItemList();

    }

    private void getItemList() {
        for (int j = 0; j < keyList.size(); j++) {
            String key = keyList.get(j);
            List<AncientBookDetail.AncientInfo> infoList = new ArrayList<AncientBookDetail.AncientInfo>();
            if ("wu".equals(key)) key = "";

            for (int i = 0; i < ancientInfoList.size(); i++) {
                AncientBookDetail.AncientInfo ancientinfo = ancientInfoList.get(i);

                if (key.equals(ancientinfo.getFenlei())) {
                    infoList.add(ancientinfo);
                }
            }
            if ("".equals(key)) key = "wu";
            hashList.put(key, infoList);
        }

    }

    static class TopItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chapter_name)
        TextView chapterNameTv;


        @BindView(R.id.tag_flow_layout)
        TagFlowLayout tagFlowLayout;

        TopItemViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
