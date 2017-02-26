package com.sha1607.smallmovie.recommend.widget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.RecommendListBean;
import com.sha1607.smallmovie.recommend.VideoDetailActivity;
import com.sha1607.smallmovie.recommend.adatper.RecommendAdapter;
import com.sha1607.smallmovie.recommend.presenter.RecommendPresenter;
import com.sha1607.smallmovie.recommend.view.RecommendView;
import com.sha1607.smallmovie.utils.ACache;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecommendFragment extends Fragment implements RecommendView{
    RecommendPresenter mPresenter;
    private Banner mBanner;
    private View mHeaderView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecommendAdapter mRecommendAdapter;
    private TextView mTv;
    private boolean flag=false;
    private RecommendListBean mBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public RecommendFragment() {
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBean = (RecommendListBean) ACache.get(getActivity(),"hjjzuishuai").getAsObject("hjj");
        if (mBean !=null){
            flag=true;
        }
        if (!flag){
            mPresenter.loadData();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.loadData();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },3000);


            }
        });
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecommendAdapter = new RecommendAdapter(getActivity());
        if (flag){
//            setBanner(mBean);
            mRecommendAdapter.setData(mBean);

        }
        mHeaderAndFooterRecyclerViewAdapter=new HeaderAndFooterRecyclerViewAdapter(mRecommendAdapter);

        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        setHeader();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    public void setHeader() {
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.header_fragment_recommend, null, false);
        mBanner = (Banner) mHeaderView.findViewById(R.id.banner);
        mTv = (TextView) mHeaderView.findViewById(R.id.tv_greetings);
        RecyclerViewUtils.setHeaderView(mRecyclerView, mHeaderView);
    }

    private String getGreetings() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.i("1607", hour + "小时");
        if (hour >= 6 && hour < 11) {
            return "早上好";
        } else if (hour >= 11 && hour <= 13) {
            return "中午好";
        } else if (hour >= 14 && hour <= 18) {
            return "下午好";
        } else {
            return "晚上好";
        }


    }

    private void setBanner(RecommendListBean recommandListBeen) {
        mBanner.setImageLoader(new GlideImageLoader());
        List<RecommendListBean.ForcusImageListBean> lists = recommandListBeen.getForcusImageList();
        List<String> loadBannerPicData = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            loadBannerPicData.add(lists.get(i).getImageUrl());
        }
        List<String> loadBannerTitleData = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            loadBannerTitleData.add(lists.get(i).getMainTitle());
        }

        mBanner.setImages(loadBannerPicData);
        mBanner.setBannerTitles(loadBannerTitleData);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //下标从1开始
                switch (lists.get(position - 1).getForcusImageType()) {
                    case "0":
                        if (lists != null) {
                            Intent intent = new Intent(getActivity(), WebDetailActivity.class);
                            intent.putExtra("title", lists.get(position - 1).getMainTitle());
                            intent.putExtra("articleContentUrl", String.format("http://www.moviebase.cn/uread/app/viewArt/viewArt-%s.html", lists.get(position - 1).getArticleId()));
                            startActivity(intent);
                        }
                        break;
                    case "4":
                        if (lists != null) {
                            Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                            intent.putExtra("id", lists.get(position - 1).getContentId());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
        mBanner.start();
        mBanner.startAutoPlay();
        mTv.setText(getGreetings());
    }


    @Override
    public void onSuccess(RecommendListBean recommandListBeen) {
         mSwipeRefreshLayout.setRefreshing(false);


        ACache.get(getActivity(),"hjjzuishuai").put("hjj",recommandListBeen,10000);


        mRecommendAdapter.setData(recommandListBeen);
        setBanner(recommandListBeen);
    }

    @Override
    public void onFailure() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
