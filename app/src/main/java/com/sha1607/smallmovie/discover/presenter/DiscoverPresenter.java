package com.sha1607.smallmovie.discover.presenter;

import com.sha1607.smallmovie.bean.DiscoverListBean;
import com.sha1607.smallmovie.discover.model.DiscoverModel;
import com.sha1607.smallmovie.discover.model.DiscoverModelImpl;
import com.sha1607.smallmovie.discover.model.OnResultListener;
import com.sha1607.smallmovie.discover.view.DiscoverView;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 10:53
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class DiscoverPresenter {
    private DiscoverModel mModel;
    private DiscoverView mView;

    public DiscoverPresenter(DiscoverView view){
        this.mView = view;
        this.mModel = new DiscoverModelImpl();
    }

    public void loadDiscoverData(int id){
        mModel.loadDiscoverData(id, new OnResultListener() {
            @Override
            public void onDiscoverSuccess(DiscoverListBean bean) {
                mView.loadDiscoverData(bean);
            }
        });
    }
}
