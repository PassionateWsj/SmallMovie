package com.sha1607.smallmovie.category.widget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.category.activity.SearchActivity;
import com.sha1607.smallmovie.category.activity.TopicListActivity;
import com.sha1607.smallmovie.category.adatper.HotArticleRecyclerAdapter;
import com.sha1607.smallmovie.category.presenter.CategoryPresenter;
import com.sha1607.smallmovie.category.view.CategoryView;
import com.sha1607.smallmovie.contentactivity.ContentActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 上午09:54
 * e-mail: PassinateWsj@outlook.com
 * name: 分类界面的Fragment
 * desc: 拥有专题、热门文章两个模块，是通过RecyclerView添加头部实现两个模块的拼接
 * ****************************************************
 */

public class CategoryFragment extends Fragment implements CategoryView {

    private static final String TAG = "hjjzz";
    @BindView(R.id.category_fragment_recycler_view)
    RecyclerView mCategoryFragmentRecyclerView;
    @BindView(R.id.rl_category_search)
    RelativeLayout mRlCategorySearch;
    private HotArticleRecyclerAdapter mHotArticleRecyclerAdapter;
    private CategoryPresenter mCategoryPresenter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private View mHeaderView;
    private TopicGridViewAdapter mGridViewAdapter;
    private GridView mGvTopic;
    private TextView mTvTopicAll;
    private int mFailureCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryPresenter = new CategoryPresenter(this);
        mCategoryPresenter.loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSearchListener();    // 设置搜索按钮的点击事件
        mHotArticleRecyclerAdapter = new HotArticleRecyclerAdapter(getActivity());
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mHotArticleRecyclerAdapter);
        mCategoryFragmentRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        setHeader();    // 设置RecyclerView的头部
        mCategoryFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * 设置搜索按钮的点击事件
     */
    private void setSearchListener() {
        mRlCategorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    /**
     * 设置RecyclerView的头部
     */
    private void setHeader() {
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.category_fragment_recycler_view_header, null, false);
        mTvTopicAll = (TextView) mHeaderView.findViewById(R.id.tv_topic_all);
        mTvTopicAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TopicListActivity.class));
            }
        });
        mGvTopic = (GridView) mHeaderView.findViewById(R.id.gv_topic);
        mGridViewAdapter = new TopicGridViewAdapter();
        mGvTopic.setAdapter(mGridViewAdapter);
    }

    /**
     * 请求数据出错时回调的方法
     */
    @Override
    public void onResultFailure() {
        // 处理错误的逻辑
        mFailureCount++;
        Handler handler = new Handler();
        if (mFailureCount < 5) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCategoryPresenter.loadData();
                    Log.i(TAG, "onResultFailure:::" + mFailureCount);
                }
            }, 5000);
        }
    }

    /**
     * 请求数据成功时回调的方法
     *
     * @param data
     */
    @Override
    public void onResultSuccess(CategoryListBean data) {
        RecyclerViewUtils.setHeaderView(mCategoryFragmentRecyclerView, mHeaderView);
        mGridViewAdapter.setData(data.getTopicRecommendList());
        mHotArticleRecyclerAdapter.setData(data.getArticleList());
    }

    /**
     * 头部中GridView的Adapter
     */
    class TopicGridViewAdapter extends BaseAdapter {
        private List<CategoryListBean.TopicRecommendListBean> mData;

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData == null ? null : mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_category_topic, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mPosition = position;
            holder.mTvTopicItemTitle.setText(mData.get(position).getTitle());
            holder.mTvTopicItemSubscribeNum.setText(mData.get(position).getArticlesNum() + "篇/" + mData.get(position).getSubscribeNum() + "人订阅");
            MyImageLoader.with(getActivity(), mData.get(position).getImgUrl(),
                    holder.mIvTopicItemPic, MyImageLoader.LoaderEnum.GLIDE);
            return convertView;
        }

        public void setData(List<CategoryListBean.TopicRecommendListBean> data) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData = data;
            notifyDataSetChanged();
        }

        class ViewHolder {
            @BindView(R.id.iv_topic_item_pic)
            ImageView mIvTopicItemPic;
            @BindView(R.id.tv_topic_item_title)
            TextView mTvTopicItemTitle;
            @BindView(R.id.tv_topic_item_subscribeNum)
            TextView mTvTopicItemSubscribeNum;
            int mPosition = -1;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData != null) {
                            Intent intent = new Intent(getActivity(), ContentActivity.class);
                            intent.putExtra("id", mData.get(mPosition).getId());
                            intent.putExtra("title", mData.get(mPosition).getTitle());
                            intent.putExtra("imgUrl", mData.get(mPosition).getImgUrl());
                            intent.putExtra("articlesNum", mData.get(mPosition).getArticlesNum());
                            intent.putExtra("subscribeNum", mData.get(mPosition).getSubscribeNum());
//                            intent.putExtra("topicRecommendList", mData.get(mPosition));
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    }
}
