package com.sha1607.smallmovie.recommend.adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 15:15
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class VideoDetailAdapter extends RecyclerView.Adapter<VideoDetailAdapter.MyViewHolder> {

    private HashMap<Integer, Boolean> isChosen;
    private List<RecommandVideoCommentBean.UserCommentListBean> datas;
    private Context mContext;

    public VideoDetailAdapter(Context context) {
        datas = new ArrayList<RecommandVideoCommentBean.UserCommentListBean>();
        this.mContext = context;
//        Log.e("---",datas.size()+"");
        isChosen = new HashMap<>();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_comment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MyImageLoader.with(mContext, datas.get(position).getUserInfo().getHeadImgUrl(), holder.mCircleView, MyImageLoader.LoaderEnum.GLIDECIRCLE);
        holder.mTvNickName.setText(datas.get(position).getUserInfo().getNickname());
        holder.mTvContent.setText(datas.get(position).getCommentinfo().getContent());
        holder.pos = position;
        holder.mTvCommentDate.setText(TimeUtils.getStrTime(datas.get(position).getCommentinfo().getCreateDate()));

        if (isChosen.get(position)) {
            holder.mLove.setImageResource(R.drawable.zan_press_new);
        } else {
            holder.mLove.setImageResource(R.drawable.zan_new_white);

        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCircleView;
        private TextView mTvNickName;
        private TextView mTvContent;
        private ImageView mLove;
        private TextView mTvCommentDate;
        private int pos;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCircleView = (ImageView) itemView.findViewById(R.id.iv_item);
            mTvNickName = (TextView) itemView.findViewById(R.id.tv_nickname_item);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content_item);
            mLove = (ImageView) itemView.findViewById(R.id.comment_love);
            mTvCommentDate = (TextView) itemView.findViewById(R.id.tv_comment_date);

            mLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isChosen.put(pos, !isChosen.get(pos));
                    notifyDataSetChanged();
//                Log.e("---",datas.size()+"");
                }
            });

        }
    }

    public void setVideoDatas(List<RecommandVideoCommentBean.UserCommentListBean> data) {
        datas = data;
        for (int i = 0; i < datas.size(); i++) {
            isChosen.put(i, false);
        }
        notifyDataSetChanged();

    }
}
