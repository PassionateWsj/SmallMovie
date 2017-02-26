package com.sha1607.smallmovie.recommend.presenter;

import com.sha1607.smallmovie.bean.RecommendListBean;
import com.sha1607.smallmovie.recommend.model.OnListener;
import com.sha1607.smallmovie.recommend.model.RecommendModelImpl;
import com.sha1607.smallmovie.recommend.widget.RecommendFragment;

/**
 * Created by DiaoGe on 2016/10/26.
 */

public class RecommendPresenter {
    RecommendListBean mRecommendListBean = new RecommendListBean();
    private RecommendModelImpl mRecommendModelImpl;
    private RecommendFragment mRecommendFragment;

    public RecommendPresenter(RecommendFragment recommendFragment) {
        mRecommendFragment = recommendFragment;
        mRecommendModelImpl = new RecommendModelImpl();
    }

    public void loadData() {
        mRecommendModelImpl.getBeanData(new OnListener() {
            @Override
            public void onSuccess(RecommendListBean recommendListBean) {

                mRecommendFragment.onSuccess(recommendListBean);
            }

            @Override
            public void onFailure() {

            }
        });

    }
}