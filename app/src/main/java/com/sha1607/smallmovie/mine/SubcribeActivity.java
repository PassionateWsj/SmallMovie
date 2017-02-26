package com.sha1607.smallmovie.mine;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -2:41
 * name:
 * desc:
 * step:
 **********************************
 */

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.Order;
import com.sha1607.smallmovie.mine.adapter.SubcribeAdapter;
import com.sha1607.smallmovie.mine.presenter.SubcribePrestener;
import com.sha1607.smallmovie.mine.view.SubcribeView;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubcribeActivity extends BaseActivity implements SubcribeView {
    @BindView(R.id.toolbar_topic_activity)
    Toolbar mToolbarTopicActivity;
    @BindView(R.id.topic_activity_recycler_view)
    RecyclerView mTopicActivityRecyclerView;
    private SubcribePrestener mSubcribePrestener;
    private Dao<Order,Long> mDao;
    private SubcribeAdapter mAdapter;


    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_subcribe);
        ButterKnife.bind(this);
        mAdapter=new SubcribeAdapter(this);
        mToolbarTopicActivity.setTitle("我的订阅");
    }

    @Override
    protected void initData() {
        try {
            mDao= DBHleper.getInstance(this).getOrderDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSubcribePrestener = new SubcribePrestener(this);
        mSubcribePrestener.loadData(mDao);

        mTopicActivityRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mTopicActivityRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void loadData(List<Order> datas) {
        Log.e("0000000000000",datas.size()+"");
         mAdapter.setData(datas);
    }

}
