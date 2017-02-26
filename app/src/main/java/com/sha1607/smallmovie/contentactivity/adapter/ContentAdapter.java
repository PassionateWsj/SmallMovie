package com.sha1607.smallmovie.contentactivity.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 7:19
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {

    private Context mContext;
    private ContentFilmBean mContentFilmBean;
    public ContentAdapter(Context context){
        this.mContext=context;
    }


    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_content_list,parent,false);
        ContentHolder holder=new ContentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        ContentFilmBean.ArticleListBean.ObjectBean objectBean = mContentFilmBean.getArticleList().get(position).getObject();
        holder.mTextView.setText(objectBean.getTitle());
        holder.mTv_name.setText(objectBean.getArticleSource().getNickname());
        MyImageLoader.with(mContext,objectBean.getImgUrl(),holder.mImageView,MyImageLoader.LoaderEnum.GLIDE);
        holder.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, WebDetailActivity.class).putExtra("articleContentUrl",objectBean.getArticleContentUrl()).putExtra("title",objectBean.getTitle())));

    }

    @Override
    public int getItemCount() {
        return mContentFilmBean==null?0:mContentFilmBean.getArticleList().size();
    }

    class ContentHolder extends  RecyclerView.ViewHolder{
        TextView mTextView;
        ImageView mImageView;
        TextView mTv_name;


        public ContentHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_show);
            mImageView = (ImageView) itemView.findViewById(R.id.im_show);
            mTv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
    public void setData(ContentFilmBean datas){
        mContentFilmBean=datas;
        Log.e("555",mContentFilmBean.getArticleList().size()+"");
        notifyDataSetChanged();
    }


}
