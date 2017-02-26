package com.sha1607.smallmovie.category.view;

import com.sha1607.smallmovie.bean.AllTopicListBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 下午2:02
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface TopicListView {
    void onResultFailure();

    void onResultSuccess(AllTopicListBean data);
}
