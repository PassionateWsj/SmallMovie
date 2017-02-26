package com.sha1607.smallmovie.category.model;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 下午1:37
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface SearchModel {
    void loadData(String type, int startIndex, String searchContent, OnSearchResultListener onSearchResultListener);
}
