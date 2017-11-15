package com.ant.poy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/22
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class MeasureHeightListView extends ListView {

    public MeasureHeightListView(Context context) {
        super(context);
    }

    public MeasureHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);


    }
}
