package com.sha1607.smallmovie.discover.adatper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 9:54
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class DiscoverAdapter extends FragmentPagerAdapter{


    private List<Fragment> mFragments;

    public DiscoverAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
