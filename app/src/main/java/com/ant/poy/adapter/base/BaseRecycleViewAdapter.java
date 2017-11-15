package com.ant.poy.adapter.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.poy.R;
import com.ant.poy.widget.selecttable.SelectableTextHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/18
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FLAG = 100000;

    private List<T> mDataList;

    private List<View> mHeaderViews = new ArrayList<>();
    private List<View> mFooterViews = new ArrayList<>();

    private Object headObject;

    private List<Integer> mHeaderViewTypes = new ArrayList<>();
    private List<Integer> mFooterViewTypes = new ArrayList<>();


    private Context mContext;


    protected OnItemClickListener onItemClickListener;


    /**
     * Constructor
     *
     * @param context The current context.
     */
    public BaseRecycleViewAdapter(Context context) {
        init(context, new ArrayList<T>());
    }

    private void init(Context context, List<T> objects) {
        mContext = context;
        mDataList = objects;
    }



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViewTypes.contains(viewType)) {
            return OnCreaeHeaderViewHolder(parent, mHeaderViews.get(viewType / TYPE_FLAG));
            // return OnCreaeHeaderViewHolder(parent, viewType);
        }

        if (mFooterViewTypes.contains(viewType)) {
          //  int index = viewType / TYPE_FLAG - mDataList.size() - mHeaderViews.size();
            return  OnCreaeFootViewHolder(parent,null);
        }

        return OncreateViewHolder(parent, viewType);
    }

    public abstract BaseViewHolder OncreateViewHolder(ViewGroup viewGroup, int viewType);

    public abstract BaseViewHolder OnCreaeHeaderViewHolder(ViewGroup viewGroup, View viewType);

    public abstract BaseViewHolder OnCreaeFootViewHolder(ViewGroup viewGroup, View viewType);


    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {

        if (isFooter(position) || isHeader(position)) {
            holder.setData(headObject);
            return;
        }

        final int realPos = getRealPosition(position);

        if (realPos < mDataList.size()) {
            final T data = mDataList.get(realPos);
            holder.setData(data, realPos);
        }


        //itemView 的点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(realPos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + mHeaderViews.size() + mFooterViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderViews.size() > 0 && position < mHeaderViews.size()) {
            //用position作为HeaderView 的   ViewType标记
            //记录每个ViewType标记
            mHeaderViewTypes.add(position * TYPE_FLAG);
            return position * TYPE_FLAG;
        }

        if (mFooterViews.size() > 0 && mFooterViews.size() > 0 && position >= getItemCount() - mFooterViews.size()) {
            //用position作为FooterView 的   ViewType标记
            //记录每个ViewType标记
            mFooterViewTypes.add(position * TYPE_FLAG);
            return position * TYPE_FLAG;
        }
        return TYPE_NORMAL;
    }

    public int getRealPosition(int position) {
        return position - mHeaderViews.size();
    }

    /**
     * 必须在setAdapter之前添加
     *
     * @param view header
     */
    public void addHeaderView(View view) {
        mHeaderViews.add(view);
    }

    public void removeHeader() {
        mHeaderViews.clear();
    }

    /**
     * 必须在setAdapter之前添加
     *
     * @param view footer
     */
    public void addFooterView(View view) {
        mFooterViews.add(view);
    }
    public void removeFooterView() {
        mFooterViews.clear();
    }

    private boolean isHeader(int position) {
        return mHeaderViews.size() > 0 && position < mHeaderViews.size();
    }

    private boolean isFooter(int position) {
        return mFooterViews.size() > 0 && position >= getItemCount() - mFooterViews.size();
    }

    /**
     * 当LayoutManager是GridLayoutManager时，设置header和footer占据的列数
     *
     * @param recyclerView recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isFooter(position) || isHeader(position))
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 当LayoutManager是StaggeredGridLayoutManager时，设置header和footer占据的列数
     *
     * @param holder holder
     */
    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        final ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {

            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            params.setFullSpan(isHeader(holder.getLayoutPosition())
                    || isFooter(holder.getLayoutPosition()));
        }
    }

    /**
     * Lock used to modify the content of {@link #mDataList}. Any write operation
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
                mDataList.addAll(collection);
            }
        }
    }

    public void setHeaderObject(Object object) {
        this.headObject = object;
    }

    /**
     * 清空所有
     */
    public void clearAll() {
        mDataList.clear();
    }

    /**
     * {@inheritDoc}
     */
    public T getItem(int position) {
        return mDataList.get(position);
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