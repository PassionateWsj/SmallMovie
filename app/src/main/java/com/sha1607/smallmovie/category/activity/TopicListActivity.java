package com.sha1607.smallmovie.category.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.bean.AllTopicListBean;
import com.sha1607.smallmovie.category.adatper.TopicListAdapter;
import com.sha1607.smallmovie.category.presenter.TopicListPresenter;
import com.sha1607.smallmovie.category.view.TopicListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 上午11:58
 * e-mail: PassinateWsj@outlook.com
 * name: 分类界面，查看全部主题的Activity
 * desc:
 * ****************************************************
 */

public class TopicListActivity extends BaseActivity implements TopicListView {

    @BindView(R.id.topic_activity_recycler_view)
    RecyclerView mTopicActivityRecyclerView;
    @BindView(R.id.toolbar_topic_activity)
    Toolbar mToolbarTopicActivity;
    private TopicListPresenter mTopicListPresenter;
    private TopicListAdapter mTopicListAdapter;
    private int pageContext = 1;
    private List<Boolean> mIsChecked;

    /**
     * 初始化布局
     */
    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_topic_list);
        ButterKnife.bind(this);
        mTopicListPresenter = new TopicListPresenter(this);
        // 设置自定义ToolBar
        setSupportActionBar(mToolbarTopicActivity);
        getSupportActionBar().setTitle(R.string.text_all_topic);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mTopicListPresenter.loadData(pageContext); //加载数据
        mTopicListAdapter = new TopicListAdapter(this);
        mTopicActivityRecyclerView.setAdapter(mTopicListAdapter);
        mTopicActivityRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mTopicActivityRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    /**
     * RecyclerView滚动的监听
     */
    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            if (pageContext < 2) {
                pageContext++;
                mTopicListPresenter.loadData(pageContext);
            }
        }
    };

    /**
     * 下载数据失败回调的方法
     */
    @Override
    public void onResultFailure() {
        //处理错误的逻辑
    }

    /**
     * 下载数据成功回调的方法
     *
     * @param data
     */
    @Override
    public void onResultSuccess(AllTopicListBean data) {
        mIsChecked = new ArrayList<>();
        for (int i = 0; i < data.getTopicList().size(); i++) {
            mIsChecked.add(false);
        }
        mTopicListAdapter.setData(data.getTopicList(), mIsChecked);
    }

    /**
     * 设置自定义ToolBar点击按钮的响应事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mTopicListAdapter.setData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopicListAdapter.closeDao();
    }
}
