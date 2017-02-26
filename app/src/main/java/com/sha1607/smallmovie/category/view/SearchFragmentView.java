package com.sha1607.smallmovie.category.view;

import com.sha1607.smallmovie.bean.SearchResultBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 下午1:46
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface SearchFragmentView {
    void onSearchResultFailure();

    void onSearchResultSuccess(SearchResultBean data);
}
