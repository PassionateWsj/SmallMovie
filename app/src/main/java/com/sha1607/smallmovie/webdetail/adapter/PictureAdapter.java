package com.sha1607.smallmovie.webdetail.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/27 下午 10:21
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class PictureAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> mList;
    public PictureAdapter(Context context,List<ImageView> imageViews){

        this.mContext=context;
        mList=imageViews;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {



        container.addView(mList.get(position));

        return mList.get(position);
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mList.get(position));
    }
}
