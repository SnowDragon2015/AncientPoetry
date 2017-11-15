package com.ant.poy.widget.search;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.poy.R;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/9/20
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class SearchViewLayout extends FrameLayout {

    private boolean mClearingFocus;


    private Context mContext;
    public EditText mSearchSrcTextView;
    //MaterialSearchView.SearchViewListener

    private OnSearchLisener onSearchLisener;

    public ImageView mBackBtn;

    public TextView mSearchBtn;

    public SearchViewLayout(Context context) {
        this(context, null);
    }

    public SearchViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.search_layout, this);

        mSearchSrcTextView = (EditText) findViewById(R.id.pop_search_edit);

        mBackBtn = (ImageView) findViewById(R.id.pop_back_btn);

        mSearchBtn = (TextView) findViewById(R.id.pop_search_tv);


        setListener();

    }

    private void setListener() {

        mSearchSrcTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return true;
            }
        });
        mSearchSrcTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        mSearchSrcTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onSearchLisener != null)
                    onSearchLisener.OnTextChange(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSearchSrcTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showKeyboard(mSearchSrcTextView);
                }
            }
        });

        mSearchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSearchLisener != null) onSearchLisener.OnSearchClick(v);
            }
        });

    }


    public void setTextColor(int color) {
        mSearchSrcTextView.setTextColor(color);
    }

    public void setHintTextColor(int color) {
        mSearchSrcTextView.setHintTextColor(color);
    }

    public void setHint(CharSequence hint) {
        mSearchSrcTextView.setHint(hint);
    }


    public void setBackIcon(Drawable drawable) {
        mBackBtn.setImageDrawable(drawable);

    }

    public void setText(String str) {
        mSearchSrcTextView.setText(str);
    }


    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        // Don't accept focus if in the middle of clearing focus
        if (mClearingFocus) return false;
        // Check if SearchView is focusable.
        if (!isFocusable()) return false;
        return mSearchSrcTextView.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void clearFocus() {
        mClearingFocus = true;
        hideKeyboard(this);
        super.clearFocus();
        mSearchSrcTextView.clearFocus();
        mClearingFocus = false;

    }

    public void closeSearch() {
        mSearchSrcTextView.setText(null);

        clearFocus();

    }

    public void hideKeyboard(View view) {

        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        mSearchSrcTextView.setFocusable(true);

        mSearchSrcTextView.setFocusableInTouchMode(true);

        mSearchSrcTextView.requestFocus();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();

        /**发现只有当界面绘制完毕的时候，调用上述代码软键盘才会显示*/
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, 0);
            }
        }, 100);

    }


    public ImageView getmBackBtn() {
        return mBackBtn;
    }

    public TextView getmSearchBtn() {
        return mSearchBtn;
    }

    public EditText getmSearchSrcTextView() {
        return mSearchSrcTextView;
    }

    public void setOnTextChangeLisener(OnSearchLisener OnSearchLisener) {
        this.onSearchLisener = OnSearchLisener;
    }

    public interface OnSearchLisener {
        void OnTextChange(String s);

        void OnSearchClick(View view);
    }

}
