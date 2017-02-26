package com.sha1607.smallmovie.contentactivity.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/27 下午 2:09
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.util.HashMap;
import java.util.Map;

public class ContentArticleAdapter extends RecyclerView.Adapter<ContentArticleAdapter.ArticleHolder> {
    private Context mContext;
    private ContentFilmBean mFilmBean;
    private boolean flag = true;
    private Map<Integer, Boolean> mMap;

    public ContentArticleAdapter(Context context) {
        this.mContext = context;
        mMap = new HashMap<>();
    }


    @Override
    public ContentArticleAdapter.ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_content_article, parent, false);
        ArticleHolder holder = new ArticleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentArticleAdapter.ArticleHolder holder, int position) {
        ContentFilmBean.ArticleListBean.ObjectBean objectBean = mFilmBean.getArticleList().get(position).getObject();
        holder.pos = position;
        MyImageLoader.with(mContext, objectBean.getImgUrl(), holder.mImageView, MyImageLoader.LoaderEnum.GLIDE);
        holder.mImageView.setColorFilter(R.color.colorhalf);
        holder.mTextView_content.setText(objectBean.getDescription());
        String desc = "#" + objectBean.getSourceAuthor();
        holder.mTextView_name.setText(desc);
        setPariseCount(holder, position, objectBean);
    }

    private void setPariseCount(ArticleHolder holder, int position, ContentFilmBean.ArticleListBean.ObjectBean objectBean) {
        if (holder.praiseCount == 0) {
            holder.praiseCount = Integer.parseInt(objectBean.getPraiseCount());
        }
        if (mMap.get(position)) {
            holder.mImageView_good.setImageResource(R.drawable.zan_press_new);
        } else {
            holder.mImageView_good.setImageResource(R.drawable.zan_new_white);
        }
        holder.mTextView_num.setText(holder.praiseCount+"");
        if (holder.mTextView_num.getText().equals("0")) {
            holder.mTextView_num.setVisibility(View.INVISIBLE);
        } else {
            holder.mTextView_num.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mFilmBean == null ? 0 : mFilmBean.getArticleList().size();
    }

    public void setData(ContentFilmBean datas) {
        mFilmBean = datas;
        for (int i = 0; i < datas.getArticleList().size(); i++) {
            mMap.put(i, false);
        }
        notifyDataSetChanged();
    }

    public void translate(View view) {
        ScaleAnimation sa = new ScaleAnimation(1f, 1.4f, 1f, 1.4f//0倍到2倍
                , Animation.RELATIVE_TO_SELF, 0.5f//中心缩放
                , Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }


    class ArticleHolder extends RecyclerView.ViewHolder {
        private int pos;
        TextView mTextView_content;
        TextView mTextView_name;
        ImageView mImageView;
        ImageView mImageView_talk;
        ImageView mImageView_good;
        TextView mTextView_num;
        int praiseCount;

        public ArticleHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_big_show);
            mTextView_content = (TextView) itemView.findViewById(R.id.tv_article_content);
            mTextView_name = (TextView) itemView.findViewById(R.id.tv_bottom_name);
            mImageView_good = (ImageView) itemView.findViewById(R.id.iv_bottom_good);
            mImageView_talk = (ImageView) itemView.findViewById(R.id.iv_bottom_talk);
            mTextView_num = (TextView) itemView.findViewById(R.id.tv_bottom_num);

            mImageView_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMap.get(pos)) {
                        praiseCount--;
                    } else {
                        praiseCount++;
                    }
                    mMap.put(pos, !mMap.get(pos));
                    notifyDataSetChanged();
                }
            });

        }
    }
}
