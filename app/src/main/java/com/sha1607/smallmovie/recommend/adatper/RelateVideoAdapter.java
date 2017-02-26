package com.sha1607.smallmovie.recommend.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.recommend.VideoDetailActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/31 11:30
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class RelateVideoAdapter extends RecyclerView.Adapter<RelateVideoAdapter.MyHolder> {

    private Context mContext;
    private List<RecommandVideoBean.VideoInfoBean.VideoRelationListBean> mDatas;

    public RelateVideoAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.relate_video_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        MyImageLoader.with(mContext, mDatas.get(position).getCoverUrl(), holder.relateImg, MyImageLoader.LoaderEnum.GLIDE);
        holder.relateTime.setText(mDatas.get(position).getVideoTime());
        holder.relateTitle.setText(mDatas.get(position).getTitle());
        holder.relateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoDetailActivity.class);
                intent.putExtra("id", mDatas.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private ImageView relateImg;
        private TextView relateTime, relateTitle;

        public MyHolder(View itemView) {
            super(itemView);
            relateImg = (ImageView) itemView.findViewById(R.id.relate_img);
            relateTime = (TextView) itemView.findViewById(R.id.relate_time);
            relateTitle = (TextView) itemView.findViewById(R.id.relate_title);
        }
    }

    public void setDatas(List<RecommandVideoBean.VideoInfoBean.VideoRelationListBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    ;
}
