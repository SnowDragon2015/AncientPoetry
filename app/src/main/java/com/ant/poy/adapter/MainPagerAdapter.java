package com.ant.poy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ant.poy.R;
import com.ant.poy.ui.mainfragment.MainAncientBooksFragment;
import com.ant.poy.ui.mainfragment.MainAuthorFragment;
import com.ant.poy.ui.mainfragment.MainPoetryFragment;
import com.ant.poy.ui.mainfragment.MainRecommendFragment;
import com.ant.poy.ui.mainfragment.MainWisdomFragment;

/**
 * Created by SnowDragon2015
 *
 * $date 2017/7/14
 *
 * Github ：https://github.com/SnowDragon2015
 *
 */


public class MainPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public  MainPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        TITLES = context.getResources().getStringArray(R.array.tab_select_main);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = MainRecommendFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = MainPoetryFragment.newInstance();
                    break;
                case 2:
                    fragments[position] = MainWisdomFragment.newInstance();
                    break;
                case 3:
                    fragments[position] = MainAuthorFragment.newInstance();
                    break;
                case 4:
                    fragments[position] = MainAncientBooksFragment.newIntance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {

        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position];
    }
}
