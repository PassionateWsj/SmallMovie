package com.sha1607.smallmovie.webdetail.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 8:02
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.AuthorBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

public class AuthorModelImpl implements AuthorModel {
    @Override
    public void loadData(String type, int id, OnAuthorSuccessListener listener) {
        String url = String.format(RecommendApis.AUTHOR_LIST, id, type);
        RetrofitUtils.create(RecommendApis.HOST,RecommendApis.class).getAuthorList(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<AuthorBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AuthorBean authorBean) {
                          listener.loadData(authorBean);
                    }
                });
    }
}
