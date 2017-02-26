package com.sha1607.smallmovie.moviedetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/30 下午3:27
 * e-mail: PassinateWsj@outlook.com
 * name: 电影详情界面用户评论ListView的Adapter
 * desc:
 * ****************************************************
 */
public class MovieDetailCommentsAdapter extends BaseAdapter {
    private Context mContext;
    private List<MovieCommentsBean.CommentListBean> mData;
    private List<Boolean> mIsPraise;

    public MovieDetailCommentsAdapter(Context context) {
        this.mContext = context;
    }

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
        MovieDetailCommentsHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_movie_detail_comment, parent, false);
            holder = new MovieDetailCommentsHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MovieDetailCommentsHolder) convertView.getTag();
        }
        holder.mPosition = position;
        holder.mTvMovieCommentUserName.setText(mData.get(position).getUserInfo().getNickname());
        holder.mTvMovieCommentContent.setText(mData.get(position).getCommentinfo().getContent());
        holder.mTvMovieCommentCreateTime.setText(TimeUtils.getStrTime(mData.get(position).getCommentinfo().getCommenttime()));
        setPraise(position, holder);
        MyImageLoader.with(mContext, mData.get(position).getUserInfo().getHeadImgUrl(), holder.mIvMovieCommentUserIcon, MyImageLoader.LoaderEnum.GLIDECIRCLE);
        return convertView;
    }

    /**
     * 设置点赞
     *
     * @param position
     * @param holder
     */
    private void setPraise(int position, MovieDetailCommentsHolder holder) {
        if (holder.mPraiseNum == 0 && mData != null && !mData.get(position).getCommentinfo().getPraisenum().equals("")) {
            holder.mPraiseNum = Integer.parseInt(mData.get(position).getCommentinfo().getPraisenum());
        }
        if (mIsPraise.get(position)) {
            holder.mIvMovieCommentPraise.setImageResource(R.drawable.zan_press_new);
        } else {
            holder.mIvMovieCommentPraise.setImageResource(R.drawable.zan_new_white);
        }
        if (holder.mPraiseNum == 0) {
            holder.mTvMovieCommentPraiseNum.setVisibility(View.INVISIBLE);
        } else {
            holder.mTvMovieCommentPraiseNum.setVisibility(View.VISIBLE);
        }
        holder.mTvMovieCommentPraiseNum.setText(holder.mPraiseNum + "");
    }

    class MovieDetailCommentsHolder {
        @BindView(R.id.iv_movie_comment_user_icon)
        ImageView mIvMovieCommentUserIcon;
        @BindView(R.id.tv_movie_comment_user_name)
        TextView mTvMovieCommentUserName;
        @BindView(R.id.tv_movie_comment_create_time)
        TextView mTvMovieCommentCreateTime;
        @BindView(R.id.tv_movie_comment_content)
        TextView mTvMovieCommentContent;
        @BindView(R.id.iv_movie_comment_praise)
        ImageView mIvMovieCommentPraise;
        @BindView(R.id.tv_movie_comment_praise_num)
        TextView mTvMovieCommentPraiseNum;
        int mPosition;
        int mPraiseNum = 0;

        MovieDetailCommentsHolder(View view) {
            ButterKnife.bind(this, view);
            mIvMovieCommentPraise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsPraise.get(mPosition)) {
                        mIsPraise.set(mPosition, false);
                        mPraiseNum--;
                    } else {
                        mIsPraise.set(mPosition, true);
                        mPraiseNum++;
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 暴露出去获取Data的方法
     *
     * @param data
     */
    public void setData(List<MovieCommentsBean.CommentListBean> data, List<Boolean> isPraise) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        if (mIsPraise == null) {
            mIsPraise = new ArrayList<>();
        }
        mIsPraise.addAll(isPraise);
        mData.addAll(data);
        notifyDataSetChanged();
    }

}
