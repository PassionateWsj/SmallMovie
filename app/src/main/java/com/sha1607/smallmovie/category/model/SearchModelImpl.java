package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.SearchResultBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 下午1:37
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public class SearchModelImpl implements SearchModel {

    @Override
    public void loadData(String type, int startIndex, String searchContent, OnSearchResultListener onSearchResultListener) {
        String url = String.format(RecommendApis.SEARCH_URL, type, startIndex, searchContent);
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class)
                .getSearchBean(url)
                .compose(RetrofitUtils.rxSchedulerHelper())
                .subscribe(new Subscriber<SearchResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        onSearchResultListener.onSearchResultSuccess(searchResultBean);
                    }
                });
    }
}
