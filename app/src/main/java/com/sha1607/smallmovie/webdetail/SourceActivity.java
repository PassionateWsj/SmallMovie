package com.sha1607.smallmovie.webdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.SourceBean;
import com.sha1607.smallmovie.utils.EndlessRecyclerOnScrollListener;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.adapter.SourceAdapter;
import com.sha1607.smallmovie.webdetail.presenter.SourcePresenter;
import com.sha1607.smallmovie.webdetail.view.SourceView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceActivity extends AppCompatActivity implements SourceView {


    @BindView(R.id.rl_list_source)
    RecyclerView mRlListSource;

    private SourceAdapter mAdapter;
    private SourcePresenter mPresenter;
    private LinearLayoutManager mLinearLayoutManager;
    private String mSourceId;
    private int num = 1;
    private String mCanLoadMore;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private View mView;
    private ImageView mIvLogo;
    private TextView mTvNikcenameSourece;
    private TextView mTvDescription;
    private TextView mTvGonghzonghao;
    private ImageView mImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        ButterKnife.bind(this);
        mPresenter = new SourcePresenter(this);
        mView = LayoutInflater.from(this).inflate(R.layout.head_source, null);
        mImageView = (ImageView) mView.findViewById(R.id.iv_background_source);
        mIvLogo= (ImageView) mView.findViewById(R.id.iv_logo);
        mTvDescription = (TextView) mView.findViewById(R.id.tv_description);
        mTvGonghzonghao = (TextView) mView.findViewById(R.id.tv_gonghzonghao);
        mTvNikcenameSourece = (TextView) mView.findViewById(R.id.tv_nikcename_sourece);
        Intent intent = getIntent();
        mSourceId = intent.getStringExtra("sourceId");
        Log.e("!!!!!!!!!!!!!!",mSourceId);
        initData();

    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRlListSource.setLayoutManager(mLinearLayoutManager);
        mAdapter = new SourceAdapter(this);

        mPresenter.loadData(mSourceId, num);

        mRlListSource.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (mCanLoadMore.equals("1")) {
                    mPresenter.loadData(mSourceId, num);
                    num++;
                }
            }
        });

    }


    @Override
    public void loadData(SourceBean datas) {
        Log.e("sdfsdf",datas.getContentList().size()+"");
        mAdapter.setData(datas.getContentList());
        mCanLoadMore = datas.getCanLoadMore();
        if (num==1){
            MyImageLoader.with(this,datas.getSourceInfo().getLogo(),mImageView,MyImageLoader.LoaderEnum.JIAJUN);
            MyImageLoader.with(this,datas.getSourceInfo().getLogo(),mIvLogo,MyImageLoader.LoaderEnum.GLIDEROUND);
            mTvNikcenameSourece.setText(datas.getSourceInfo().getNickname());
            mTvDescription.setText(datas.getSourceInfo().getDescription());
            mTvGonghzonghao.setText(datas.getSourceInfo().getSourceAddress());
            mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
            mRlListSource.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
            RecyclerViewUtils.setHeaderView(mRlListSource, mView);
        }
    }
}
