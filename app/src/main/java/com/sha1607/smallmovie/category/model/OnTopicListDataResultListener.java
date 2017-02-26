package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.bean.AllTopicListBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 下午2:28
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface OnTopicListDataResultListener {
    void onTopicListDataResultFailure();

    void onTopicListDataResultSuccess(AllTopicListBean data);

}
