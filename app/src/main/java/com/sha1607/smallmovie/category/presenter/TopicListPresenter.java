package com.sha1607.smallmovie.category.presenter;

import com.sha1607.smallmovie.bean.AllTopicListBean;
import com.sha1607.smallmovie.category.model.OnTopicListDataResultListener;
import com.sha1607.smallmovie.category.model.TopicListModelImpl;
import com.sha1607.smallmovie.category.view.TopicListView;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:03
 * e-mail: PassinateWsj@outlook.com
 * name: Category的Presenter
 * desc:
 * ****************************************************
 */

public class TopicListPresenter {
    private TopicListView mTopicListView;
    private TopicListModelImpl mTopicListModel;

    public TopicListPresenter(TopicListView topicListView) {
        this.mTopicListView = topicListView;
        mTopicListModel = new TopicListModelImpl();
    }

    /**
     * 向TopicListModel发出加载数据指令，并回调结果
     */
    public void loadData(int pageContext) {
        mTopicListModel.loadData(pageContext, new OnTopicListDataResultListener() {
            @Override
            public void onTopicListDataResultFailure() {
                // 处理错误的逻辑
            }

            @Override
            public void onTopicListDataResultSuccess(AllTopicListBean data) {
                mTopicListView.onResultSuccess(data);
            }
        });
    }
}
