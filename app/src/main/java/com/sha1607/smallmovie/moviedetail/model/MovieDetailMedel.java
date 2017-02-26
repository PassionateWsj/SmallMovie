package com.sha1607.smallmovie.moviedetail.model;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 下午4:31
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public interface MovieDetailMedel {
    void loadMovieDetailData(String id, OnMovieDetailResultListener onMovieDetailResultListener);

    void loadMovieCommentsData(int pageContext,String id,  OnMovieCommentsResultListener onMovieCommentsResultListener);
}
