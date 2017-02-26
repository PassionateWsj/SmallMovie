package com.sha1607.smallmovie.contentactivity.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 8:28
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.recommend.VideoDetailActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;

public class ContentFilmAdapter extends RecyclerView.Adapter<ContentFilmAdapter.FilmHolder> {
    private Context mContext;
    private ContentFilmBean mFilmBean;
    public ContentFilmAdapter(Context context){
        this.mContext=context;
    }



    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contetnfilm_list,parent,false);
        FilmHolder holder=new FilmHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FilmHolder holder, int position) {
        ContentFilmBean.ArticleListBean.ObjectBean objectBean = mFilmBean.getArticleList().get(position).getObject();
        holder.mTextView.setText(objectBean.getTitle());
        holder.mTimeTextView.setText(objectBean.getVideoTime());
        MyImageLoader.with(mContext,objectBean.getShareUrl().getImageUrlSrc(),holder.mImageView,MyImageLoader.LoaderEnum.GLIDE);
        holder.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, VideoDetailActivity.class).putExtra("id",objectBean.getId())));
      //  holder.mImageView.setColorFilter(R.color.colordemo);
    }

    @Override
    public int getItemCount() {
        return mFilmBean==null?0:mFilmBean.getArticleList().size();
    }
    public void setData(ContentFilmBean datas){
        mFilmBean=datas;
        notifyDataSetChanged();
    }

    class FilmHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ImageView mImageView;
        TextView mTimeTextView;

        public FilmHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_content_film);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_film_show);
            mTimeTextView = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
