package com.sha1607.smallmovie.category.presenter;

import com.sha1607.smallmovie.bean.SearchResultBean;
import com.sha1607.smallmovie.category.model.OnSearchResultListener;
import com.sha1607.smallmovie.category.model.SearchModelImpl;
import com.sha1607.smallmovie.category.view.SearchFragmentView;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 下午1:34
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class SearchPresenter {
    private SearchModelImpl mSearchModel;
    private SearchFragmentView mSearchFragmentView;

    public SearchPresenter(SearchFragmentView searchFragmentView) {
        this.mSearchFragmentView = searchFragmentView;
        mSearchModel = new SearchModelImpl();
    }

    public void loadData(String type, int startIndex, String searchContent) {
        mSearchModel.loadData(type, startIndex, searchContent, new OnSearchResultListener() {
            @Override
            public void onSearchResultFailure() {
                mSearchFragmentView.onSearchResultFailure();
            }

            @Override
            public void onSearchResultSuccess(SearchResultBean data) {
                mSearchFragmentView.onSearchResultSuccess(data);
            }
        });
    }
}
