package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.AllTopicListBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 下午2:02
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public class TopicListModelImpl implements TopicListModel {

    @Override
    public void loadData(int pageContext, OnTopicListDataResultListener onTopicListDataResultListener) {
        String url = String.format(RecommendApis.ALLTOPICLIST_URL, pageContext);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class)
                .getAllTopicList(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<AllTopicListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AllTopicListBean allTopicListBean) {
                        onTopicListDataResultListener.onTopicListDataResultSuccess(allTopicListBean);
                    }
                });
    }
}
