package com.sha1607.smallmovie.moviedetail.presenter;

import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.bean.MovieDetailBean;
import com.sha1607.smallmovie.moviedetail.model.MovieDetailModelImpl;
import com.sha1607.smallmovie.moviedetail.model.OnMovieCommentsResultListener;
import com.sha1607.smallmovie.moviedetail.model.OnMovieDetailResultListener;
import com.sha1607.smallmovie.moviedetail.view.MovieDetailView;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 下午4:28
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class MovieDetailPresenter {

    private MovieDetailView mMovieDetailView;
    private final MovieDetailModelImpl mMovieDetailModelImpl;

    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        mMovieDetailView = movieDetailView;
        mMovieDetailModelImpl = new MovieDetailModelImpl();
    }

    public void loadMovieDetailData(String id) {
        mMovieDetailModelImpl.loadMovieDetailData(id, new OnMovieDetailResultListener() {
            @Override
            public void onMovieDetailResultFailure() {
                mMovieDetailView.onMovieDetailBeanResultFailure();
            }

            @Override
            public void onMovieDetailResultSuccess(MovieDetailBean movieDetailBean) {
                mMovieDetailView.onMovieDetailBeanResultSuccess(movieDetailBean);
            }
        });
    }

    public void loadMovieCommentsData(int pageContext, String id) {
        mMovieDetailModelImpl.loadMovieCommentsData(pageContext, id, new OnMovieCommentsResultListener() {
            @Override
            public void onMovieCommentsResultFailure() {
                mMovieDetailView.onMovieCommentsBeanResultFailure();
            }

            @Override
            public void onMovieCommentsResultSuccess(MovieCommentsBean movieCommentsBean) {
                mMovieDetailView.onMovieCommentsBeanResultSuccess(movieCommentsBean);
            }
        });
    }

}
