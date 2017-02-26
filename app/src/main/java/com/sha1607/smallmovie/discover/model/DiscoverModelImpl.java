package com.sha1607.smallmovie.discover.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.DiscoverListBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 10:44
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class DiscoverModelImpl implements DiscoverModel {
    @Override
    public void loadDiscoverData(int id, OnResultListener listener) {
        String url = String.format(RecommendApis.DISCOVE_LIST, id);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class).getDiscoverList(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<DiscoverListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DiscoverListBean discoverListBean) {
                              listener.onDiscoverSuccess(discoverListBean);
                    }
                });
    }
}
