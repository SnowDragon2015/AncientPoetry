package com.ant.poy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.utils.AppUtils;


/**
 * Created by hcc on 16/8/7 21:18
 * 100332338@qq.com
 * <p>
 * 自定义EmptyView
 */
public class CustomEmptyView extends FrameLayout {

    private ImageView mEmptyImg;

    private TextView mEmptyText;


    public CustomEmptyView(Context context) {

        this(context, null);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }
    public CustomEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_layout, this);
        mEmptyImg = (ImageView) view.findViewById(R.id.empty_img);

        mEmptyImg.setImageResource(R.drawable.ic_close_black);

        mEmptyText = (TextView) view.findViewById(R.id.empty_text);
    }

    public void setEmptyImage(int imgRes) {

        mEmptyImg.setImageResource(imgRes);
    }
    public void setTextColor(int resId){
        mEmptyText.setTextColor(AppUtils.getAppContext().getResources().getColor(resId));
    }

    public void setEmptyText(String text) {

        mEmptyText.setText(text);
    }
}
