package com.sha1607.smallmovie.moviedetail.view;

import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.bean.MovieDetailBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 下午2:57
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public interface MovieDetailView {

    void onMovieDetailBeanResultSuccess(MovieDetailBean movieDetailBean);

    void onMovieDetailBeanResultFailure();

    void onMovieCommentsBeanResultSuccess(MovieCommentsBean movieCommentsBean);

    void onMovieCommentsBeanResultFailure();
}
