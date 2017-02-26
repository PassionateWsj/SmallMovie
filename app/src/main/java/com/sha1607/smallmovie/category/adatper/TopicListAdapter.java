package com.sha1607.smallmovie.category.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.contentactivity.ContentActivity;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.Order;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 下午2:05
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicListViewHolder> {
    private Context mContext;
    private List<CategoryListBean.TopicRecommendListBean> mData;
    private List<Boolean> mIsChecked;
    private Dao<Order, Long> mDao;
    private List<Order> mOrders;


    public TopicListAdapter(Context context) {
        this.mContext = context;
        try {
            mDao = DBHleper.getInstance(mContext).getOrderDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TopicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_all_topic, parent, false);
        return new TopicListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopicListViewHolder holder, int position) {
        if (mData != null) {
            holder.mPosition = position;
            holder.mTvAllTopicItemTitle.setText(mData.get(position).getTitle());
            holder.mTvAllTopicItemSubscribeNum.setText(mData.get(position).getArticlesNum() + "篇/" + mData.get(position).getSubscribeNum() + "人订阅");
            if (mIsChecked.get(position)) {
                holder.mBtnAllSubscription.setText("已订阅");
                holder.mBtnAllSubscription.setTextColor(mContext.getResources().getColor(R.color.colorTabText));
                holder.mBtnAllSubscription.setBackground(mContext.getResources().getDrawable(R.drawable.details_button_selector_true));
            } else {
                holder.mBtnAllSubscription.setText("订阅");
                holder.mBtnAllSubscription.setTextColor(mContext.getResources().getColor(R.color.colorLightBlue));
                holder.mBtnAllSubscription.setBackground(mContext.getResources().getDrawable(R.drawable.details_button_selector));
            }
            MyImageLoader.with(mContext, mData.get(position).getImgUrl(),
                    holder.mIvAllTopicItemPic, MyImageLoader.LoaderEnum.GLIDE);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class TopicListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_all_topic_item_pic)
        ImageView mIvAllTopicItemPic;
        @BindView(R.id.tv_all_topic_item_title)
        TextView mTvAllTopicItemTitle;
        @BindView(R.id.tv_all_topic_item_subscribeNum)
        TextView mTvAllTopicItemSubscribeNum;
        @BindView(R.id.btn_all_subscription)
        Button mBtnAllSubscription;
        int mPosition = -1;

        public TopicListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData != null) {
                        Intent intent = new Intent(mContext, ContentActivity.class);
                        intent.putExtra("id", mData.get(mPosition).getId());
                        intent.putExtra("title", mData.get(mPosition).getTitle());
                        intent.putExtra("imgUrl", mData.get(mPosition).getImgUrl());
                        intent.putExtra("articlesNum", mData.get(mPosition).getArticlesNum());
                        intent.putExtra("subscribeNum", mData.get(mPosition).getSubscribeNum());
//                        intent.putExtra("topicRecommendList", mData.get(mPosition));
                        mContext.startActivity(intent);
                    }
                }
            });
            mBtnAllSubscription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryListBean.TopicRecommendListBean bean = mData.get(mPosition);
                    Order order = new Order(bean.getTitle(), bean.getImgUrl(), bean.getId(), bean.getArticlesNum() + "", bean.getSubscribeNum() + "", true);
                    List<Order> orders = null;
                    int j = 0;
                    try {
                        orders = mDao.queryForAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (mIsChecked.get(mPosition)) {
                        mIsChecked.set(mPosition, false);
                        for (int i = 0; i < orders.size(); i++) {
                            if (orders.get(i).getImgUrl().equals(order.getImgUrl())) {
                                j = i;
                            }
                        }
                        try {
                            mDao.delete(orders.get(j));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(mContext, "取消订阅:" + mData.get(mPosition).getTitle(), Toast.LENGTH_SHORT).show();
                    } else {
                        mIsChecked.set(mPosition, true);
                        Toast.makeText(mContext, "订阅了:" + mData.get(mPosition).getTitle(), Toast.LENGTH_SHORT).show();
                        try {
                            mDao.createIfNotExists(order);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setData(List<CategoryListBean.TopicRecommendListBean> data, List<Boolean> isChecked) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        if (mIsChecked == null) {
            mIsChecked = new ArrayList<>();
        }
        mIsChecked.addAll(isChecked);
        mData.addAll(data);
        try {
            mOrders = mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.e("size", mOrders.size() + "");
        for (int i = 0; i < mOrders.size(); i++) {
            String imgUrl = mOrders.get(i).getImgUrl();
            for (int j = 0; j < mData.size(); j++) {
                if (imgUrl.equals(mData.get(j).getImgUrl())) {
                    mIsChecked.set(j, true);
                }
            }
        }
        for (int i = 0; i < mIsChecked.size(); i++) {
            Log.e("flag", mIsChecked.get(i) + "");
        }
        notifyDataSetChanged();
    }

    public void setData() {
        try {
            mOrders = mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mIsChecked.size(); i++) {
            mIsChecked.set(i,false);
        }
        Log.e("size", mOrders.size() + "");
        for (int i = 0; i < mOrders.size(); i++) {
            String imgUrl = mOrders.get(i).getImgUrl();
            for (int j = 0; j < mData.size(); j++) {
                if (imgUrl.equals(mData.get(j).getImgUrl())) {
                    mIsChecked.set(j, true);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void closeDao() {
        for (Order order : mDao) {
            order = null;
        }
        mDao = null;
    }

}
