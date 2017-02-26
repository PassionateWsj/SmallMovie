package com.sha1607.smallmovie.recommend.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.RecommendListBean;
import com.sha1607.smallmovie.contentactivity.ContentActivity;
import com.sha1607.smallmovie.recommend.VideoDetailActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.utils.TimeUtils;
import com.sha1607.smallmovie.webdetail.SourceActivity;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DiaoGe on 2016/10/26.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyHolder> {
    LayoutInflater inflater;
    private List<RecommendListBean.InfoListBean> mInfoListBeans = new ArrayList<>();
    private Context mContext;

    public RecommendAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case 1:
                itemView = inflater.inflate(R.layout.item_recommend_tdp, parent, false);
                break;
            case 2:
                itemView = inflater.inflate(R.layout.item_recommend_video, parent, false);
                break;
            case 4:
                itemView = inflater.inflate(R.layout.item_recommend_flash, parent, false);
                break;
            case 5:
                itemView = inflater.inflate(R.layout.item_recommend_dissertation, parent, false);
                break;

        }
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 1://tdp
                TextView tv_title = (TextView) holder.getView(R.id.tv_title);
                TextView tv_source_date = (TextView) holder.getView(R.id.tv_source_date);
                TextView tv_tips = (TextView) holder.getView(R.id.tv_tips);
                ImageView iv_pic = (ImageView) holder.getView(R.id.iv_pic);
                holder.mPosition = position;
                tv_source_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mInfoListBeans != null) {
                            Intent intent = new Intent(mContext, SourceActivity.class);
                            intent.putExtra("sourceId", mInfoListBeans.get(position).getObject().getArticleSource().getId());
                            mContext.startActivity(intent);
                        }

                    }
                });
                String recommendDate = mInfoListBeans.get(position).getObject().getRecommendDate();
                tv_title.setText(mInfoListBeans.get(position).getObject().getTitle());
                tv_source_date.setText(mInfoListBeans.get(position).getObject().getArticleSource().getNickname() +
                        "    " + TimeUtils.getStandardDate(recommendDate));
                MyImageLoader.with(mContext, mInfoListBeans.get(position).getObject().getImgUrl(), iv_pic, MyImageLoader.LoaderEnum.GLIDE);
                break;
            case 2://video
                TextView tv_desc = (TextView) holder.getView(R.id.tv_desc);
                ImageView iv_video_pic = (ImageView) holder.getView(R.id.iv_pic);
                TextView tv_video_time = (TextView) holder.getView(R.id.tv_videotime);
                holder.mPosition = position;
                tv_desc.setText(mInfoListBeans.get(position).getObject().getTitle().toString());
                tv_video_time.setText(mInfoListBeans.get(position).getObject().getVideoTime());
                MyImageLoader.with(mContext, mInfoListBeans.get(position).getObject().getCoverUrl(), iv_video_pic, MyImageLoader.LoaderEnum.GLIDE);
                iv_video_pic.setColorFilter(R.color.colorhalf);
                break;
            case 4:
                holder.mPosition = position;
                LinearLayout ll_content = (LinearLayout) holder.getView(R.id.ll_content);
                TextView[] titles = new TextView[3];
                TextView[] descs = new TextView[3];
                for (int i = 0; i < 3; i++) {
                    titles[i] = (TextView) ll_content.getChildAt(i).findViewById(R.id.tv_title);
                    titles[i].setText(mInfoListBeans.get(position).getObject().getInfos().get(i).getTitle());
                    descs[i] = (TextView) ll_content.getChildAt(i).findViewById(R.id.tv_desc);
                    descs[i].setText(mInfoListBeans.get(position).getObject().getInfos().get(i).getDescription());
                }
                TextView tv = (TextView) holder.getView(R.id.tv_date);
                tv.setText(mInfoListBeans.get(position).getObject().getToday() + " 电影快讯");
                break;
            case 5:
                TextView tv_diss_title = (TextView) holder.getView(R.id.tv_title);
                ImageView iv_diss_pic = (ImageView) holder.getView(R.id.iv_pic);
                TextView tv_diss_subtitle = (TextView) holder.getView(R.id.tv_subtitle);
                holder.mPosition = position;
                tv_diss_title.setText(mInfoListBeans.get(position).getObject().getTitle());
                tv_diss_subtitle.setText(mInfoListBeans.get(position).getObject().getArticlesNum() + "篇 / " + mInfoListBeans.get(position).getObject().getSubscribeNum() + " 订阅");
                MyImageLoader.with(mContext, mInfoListBeans.get(position).getObject().getImgUrl(), iv_diss_pic, MyImageLoader.LoaderEnum.GLIDE);
                iv_diss_pic.setColorFilter(R.color.colorhalf);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mInfoListBeans == null ? 0 : mInfoListBeans.size();
    }

    public void setData(RecommendListBean recommendListBean) {
        mInfoListBeans = recommendListBean.getInfoList();
        notifyDataSetChanged();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        private Map<Integer, View> mCacheView;
        int mPosition = 0;

        public MyHolder(View itemView) {
            super(itemView);
            mCacheView = new HashMap<>();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (Integer.parseInt(mInfoListBeans.get(mPosition).getObjectType())) {
                        case 1:
                            if (mInfoListBeans != null) {
                                Intent intent = new Intent(mContext, WebDetailActivity.class);
                                intent.putExtra("articleContentUrl", mInfoListBeans.get(mPosition).getObject().getArticleContentUrl());
                                intent.putExtra("title", mInfoListBeans.get(mPosition).getObject().getTitle());
                                mContext.startActivity(intent);
                            }
                            break;
                        case 2:
                            if (mInfoListBeans != null) {
                                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                                intent.putExtra("id", mInfoListBeans.get(mPosition).getObject().getId());
                                Log.i("kisaten", mInfoListBeans.get(mPosition).getObject().getId());
                                mContext.startActivity(intent);
                            }
                            break;
                        case 4:
                            if (mInfoListBeans != null) {
                                Intent intent = new Intent(mContext, WebDetailActivity.class);
                                intent.putExtra("articleContentUrl", mInfoListBeans.get(mPosition).getObject().getArticleContentUrl());
                                intent.putExtra("title", mInfoListBeans.get(mPosition).getObject().getTitle());
                                mContext.startActivity(intent);
                            }

                            break;
                        case 5:
                            if (mInfoListBeans != null) {
                                Intent intent = new Intent(mContext, ContentActivity.class);
                                intent.putExtra("id", mInfoListBeans.get(mPosition).getObject().getId());
                                intent.putExtra("title", mInfoListBeans.get(mPosition).getObject().getTitle());
                                intent.putExtra("imgUrl", mInfoListBeans.get(mPosition).getObject().getImgUrl());
                                intent.putExtra("articlesNum", mInfoListBeans.get(mPosition).getObject().getArticlesNum());
                                intent.putExtra("subscribeNum", mInfoListBeans.get(mPosition).getObject().getSubscribeNum());
                                mContext.startActivity(intent);
                                Toast.makeText(mContext, mInfoListBeans.get(mPosition).getObject().getArticlesNum() + "   " + mInfoListBeans.get(mPosition).getObject().getSubscribeNum(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }

                }
            });
        }

        public View getView(int resId) {
            View view;
            if (mCacheView.containsKey(resId)) {
                view = mCacheView.get(resId);
            } else {
                view = itemView.findViewById(resId);
                mCacheView.put(resId, view);
            }
            return view;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(mInfoListBeans.get(position).getObjectType());
    }

}
