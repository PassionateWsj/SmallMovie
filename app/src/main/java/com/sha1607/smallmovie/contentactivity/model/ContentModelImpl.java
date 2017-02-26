package com.sha1607.smallmovie.contentactivity.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 5:11
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

public class ContentModelImpl implements ContentModel{
    @Override
    public void loadData(String type, OnResultListener listener) {
        String url=String.format(RecommendApis.CONTENT_URL,type);
        RetrofitUtils.create(RecommendApis.HOST,RecommendApis.class).getContentList(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<ContentFilmBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ContentFilmBean contentFilmBean) {
                           listener.onFilmSuccess(contentFilmBean);
                    }
                });

    }
}
