package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.bean.SearchResultBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 下午1:38
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface OnSearchResultListener {
    void onSearchResultFailure();

    void onSearchResultSuccess(SearchResultBean data);
}
