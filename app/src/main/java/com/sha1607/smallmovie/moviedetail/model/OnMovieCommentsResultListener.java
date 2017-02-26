package com.sha1607.smallmovie.moviedetail.model;

import com.sha1607.smallmovie.bean.MovieCommentsBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 下午4:34
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface OnMovieCommentsResultListener {

    void onMovieCommentsResultFailure();

    void onMovieCommentsResultSuccess(MovieCommentsBean movieCommentsBean);
}
