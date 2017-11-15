package com.ant.poy.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import com.ant.poy.ui.SearchActivity;

import java.util.HashMap;

/**
 * Created by SnowDragon on 2017/7/3.
 */
public class AppUtils {


    private static Context mContext;
    private static Thread mUiThread;

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static HashMap<String, SearchActivity.RefreshListener> refreshHashMap = new HashMap<String, SearchActivity.RefreshListener>();

    public static void init(Context context) {
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isUIThread() {
        return Thread.currentThread() == mUiThread;
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunnable(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }

    public static void putRefreshListener(String str, SearchActivity.RefreshListener refreshListener) {
        refreshHashMap.put(str, refreshListener);
    }
    public static SearchActivity.RefreshListener getRefreshListner(String strType){
      return   refreshHashMap.get(strType);
    }
}
