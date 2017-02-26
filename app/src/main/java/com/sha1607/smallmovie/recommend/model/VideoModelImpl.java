package com.sha1607.smallmovie.recommend.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 14:41
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class VideoModelImpl implements VideoModel {
    @Override
    public void loadData(String id, OnResultListener listener) {
        String url = String.format(RecommendApis.RECOMMAND_VIDEO, id);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class).getRecommandVideo(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<RecommandVideoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommandVideoBean recommandVideoBean) {
                             listener.onVideoSuccess(recommandVideoBean);
                    }
                });

        String url2 = String.format(RecommendApis.VIDEO_COMMENT, id);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class).getVidemoComment(url2)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<RecommandVideoCommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommandVideoCommentBean recommandVideoCommentBean) {
                             listener.onCommentSuccess(recommandVideoCommentBean);
                    }
                });
    }

}
