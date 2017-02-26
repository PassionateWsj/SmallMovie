package com.sha1607.smallmovie.recommend.model;

import com.sha1607.smallmovie.bean.RecommendListBean;

/**
 * Created by DiaoGe on 2016/10/26.
 */

public interface OnListener {
    void onSuccess(RecommendListBean recommendListBean);
    void onFailure();
}
