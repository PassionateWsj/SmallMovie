package com.sha1607.smallmovie.webdetail.presenter;

import com.sha1607.smallmovie.webdetail.view.WebDetailView;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/26 19:56
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class WebDetailPresenter {
    private WebDetailView mView;

    public WebDetailPresenter(WebDetailView view) {
        this.mView = view;
    }

    public void onSetWebViewData(String url) {
        mView.onSetWebViewData(url);
    }

    public void onSetToolBarTile(String title) {
        mView.onSetToolBarTile(title);
    }
}
