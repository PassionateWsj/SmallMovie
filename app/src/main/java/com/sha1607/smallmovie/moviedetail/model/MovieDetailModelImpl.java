package com.sha1607.smallmovie.moviedetail.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.bean.MovieDetailBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 下午4:29
 * e-mail: PassinateWsj@outlook.com
 * name: 加载Movie数据的类
 * desc:
 * ****************************************************
 */
public class MovieDetailModelImpl implements MovieDetailMedel {
    @Override
    public void loadMovieDetailData(String id, OnMovieDetailResultListener onMovieDetailResultListener) {
        String url = String.format(RecommendApis.MOVIE_DETAIL_URL, id);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class)
                .getMovieDetailBean(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<MovieDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onMovieDetailResultListener.onMovieDetailResultFailure();
                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        onMovieDetailResultListener.onMovieDetailResultSuccess(movieDetailBean);
                    }
                });
    }

    @Override
    public void loadMovieCommentsData(int pageContext,String id,  OnMovieCommentsResultListener onMovieCommentsResultListener) {
        String url = String.format(RecommendApis.MOVIE_COMMENTS_URL, pageContext, id);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class)
                .getMovieCommentsBean(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<MovieCommentsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onMovieCommentsResultListener.onMovieCommentsResultFailure();
                    }

                    @Override
                    public void onNext(MovieCommentsBean movieCommentsBean) {
                        onMovieCommentsResultListener.onMovieCommentsResultSuccess(movieCommentsBean);
                    }
                });
    }
}
