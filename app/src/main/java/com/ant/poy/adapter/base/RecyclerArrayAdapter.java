package com.ant.poy.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.widget.selecttable.SelectableTextHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SnowDragon on 2017/7/7.
 */
public abstract class RecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    protected List<T> mObjects;
    private Context mContext;


    protected OnItemClickListener onItemClickListener;

    /**
     * Constructor
     *
     * @param context The current context.
     */
    public RecyclerArrayAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    private void init(Context context, List<T> objects) {
        mContext = context;
        mObjects = objects;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = OncreateViewHolder(parent, viewType);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {


        holder.setData(mObjects.get(position),position);

        //itemView 的点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    /**
     * Lock used to modify the content of {@link #mObjects}. Any write operation
     * performed on the array should be synchronized on this lock.
     */
    private final Object mLock = new Object();

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void addAll(Collection<? extends T> collection) {

        if (collection != null && collection.size() != 0) {
            synchronized (mLock) {
                mObjects.addAll(collection);
            }
        }
    }

    /**
     * 清空所有
     */
    public void clearAll() {
        mObjects.clear();
    }

    /**
     * {@inheritDoc}
     */
    public T getItem(int position) {
        return mObjects.get(position);
    }


    //HeaderView和FooterView的get和set函数

    /**
     * 给指定的TextView添加文字复制功能
     *
     * @param textView
     */
    public void setSelectableTextHelper(TextView textView) {
        SelectableTextHelper mSelectableTextHelper = new SelectableTextHelper.Builder(textView)
                .setSelectedColor(mContext.getResources().getColor(R.color.blue_light_sky))
                .setCursorHandleSizeInDp(20)
                .setCursorHandleColor(mContext.getResources().getColor(R.color.common_bg_tab_select))
                .build();
        /**复制字体选择，监听事件*/
        mSelectableTextHelper.setSelectListener(content -> {
        });
    }


    public abstract BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}
