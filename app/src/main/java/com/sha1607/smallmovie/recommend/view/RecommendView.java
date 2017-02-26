package com.sha1607.smallmovie.recommend.view;

import com.sha1607.smallmovie.bean.RecommendListBean;

/**
 * Name: RecommendView
 * Author: Kisaten
 * Comment: //TODO
 * Date: 2016-10-27 19:19
 */

public interface RecommendView{
    void onSuccess(RecommendListBean recommandListBeen);
    void onFailure();
}
