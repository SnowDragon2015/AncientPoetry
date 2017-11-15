package com.ant.poy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ant.poy.R;
import com.ant.poy.ui.searchfragment.SearchAncientBooksFragment;
import com.ant.poy.ui.searchfragment.SearchAuthorFragment;
import com.ant.poy.ui.searchfragment.SearchPoetryFragment;

/**
 * Created by SnowDragon2015
 *
 * $date 2017/7/14
 *
 * Github ：https://github.com/SnowDragon2015
 *
 */


public class SearchPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public SearchPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        TITLES = context.getResources().getStringArray(R.array.tab_select_search);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = SearchPoetryFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = SearchAuthorFragment.newInstance();
                    break;
                case 2:
                    fragments[position] = SearchAncientBooksFragment.newInstance();
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
