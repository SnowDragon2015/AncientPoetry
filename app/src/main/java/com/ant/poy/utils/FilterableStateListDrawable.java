package com.ant.poy.utils;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.SparseArray;

/**
 * Created by SnowDragon2015
 * <p>
 * 2017/10/11
 * <p>
 * Github ：https://github.com/SnowDragon2015
 */
public class FilterableStateListDrawable extends StateListDrawable{
    private int currIdx = -1;
    private int childrenCount = 0;
    private SparseArray<ColorFilter> filterMap;

    public FilterableStateListDrawable() {
        super();
        filterMap = new SparseArray<>();
    }

    @Override
    public void addState(int[] stateSet, Drawable drawable) {
        super.addState(stateSet, drawable);
        childrenCount++;
    }

    /**
     * Same as {@link #addState(int[], Drawable)}, but allow to set a colorFilter associated to this Drawable.
     *
     * @param stateSet    - An array of resource Ids to associate with the image.
     *                    Switch to this image by calling setState().
     * @param drawable    -The image to show.
     * @param colorFilter - The {@link ColorFilter} to apply to this state
     */
    public void addState(int[] stateSet, Drawable drawable, ColorFilter colorFilter) {
        if (colorFilter == null) {
            addState(stateSet, drawable);
            return;
        }
        // this is a new custom method, does not exist in parent class
        int currChild = childrenCount;
        addState(stateSet, drawable);
        filterMap.put(currChild, colorFilter);
    }

    @Override
    public boolean selectDrawable(int idx) {

        boolean result = super.selectDrawable(idx);
        // check if the drawable has been actually changed to the one I expect
        if (getCurrent() != null) {
            currIdx = result ? idx : currIdx;
            setColorFilter(getColorFilterForIdx(currIdx));
        } else {
            currIdx = -1;
            setColorFilter(null);
        }
        return result;
    }

    private ColorFilter getColorFilterForIdx(int idx) {
        return filterMap != null ? filterMap.get(idx) : null;
    }

    @Override
    public Drawable.ConstantState getConstantState() {
        return super.getConstantState();
    }
}
