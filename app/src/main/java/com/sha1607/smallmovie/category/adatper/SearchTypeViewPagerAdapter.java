package com.sha1607.smallmovie.category.adatper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 上午10:26
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public class SearchTypeViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public SearchTypeViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
