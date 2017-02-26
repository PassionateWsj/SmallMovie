package com.sha1607.smallmovie.webdetail.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 -1:32
 * name:
 * desc:
 * step:
 **********************************
 */

import android.util.Log;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.SourceBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

public class SourceModelImpl implements SourceModel {
    @Override
    public void loadData(String type, int num, OnSourceSuccessListener listener) {
        String url = String.format(RecommendApis.SOURCE_LIST, num, type);
        Log.e("qqqqqqq",url);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class).getSorurceList(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<SourceBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SourceBean sourceBean) {
                          listener.SourceSuccess(sourceBean);
                    }
                });
    }
}
