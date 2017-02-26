package com.sha1607.smallmovie.webdetail.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 8:12
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.AuthorBean;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorHolder> {
    private List<AuthorBean.ArticleListBean> mListBeen;
    private Context mContext;
    private String baseUrl = "http://www.moviebase.cn/uread/app/viewArt/viewArt-%s.html";

    public AuthorAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public AuthorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_author_list, parent, false);
        AuthorHolder authorHolder = new AuthorHolder(view);
        return authorHolder;
    }

    @Override
    public int getItemCount() {
        return mListBeen == null ? 0 : mListBeen.size();
    }

    @Override
    public void onBindViewHolder(AuthorHolder holder, int position) {
        AuthorBean.ArticleListBean articleListBean = mListBeen.get(position);
        MyImageLoader.with(mContext, articleListBean.getImgUrl(), holder.mImageView, MyImageLoader.LoaderEnum.GLIDE);
        holder.mTextView.setText(articleListBean.getTitle());
        holder.mLlAuthorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebDetailActivity.class);
                intent.putExtra("articleContentUrl", String.format(baseUrl, articleListBean.getId()));
                intent.putExtra("title", articleListBean.getTitle());
                mContext.startActivity(intent);
            }
        });

    }

    public void setData(List<AuthorBean.ArticleListBean> datas) {
        if (mListBeen == null) {
            mListBeen = new ArrayList<>();
        }
        mListBeen = datas;
        notifyDataSetChanged();
    }

    class AuthorHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        LinearLayout mLlAuthorItem;

        public AuthorHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_author_show);
            mTextView = (TextView) itemView.findViewById(R.id.tv_author_content);
            mLlAuthorItem = (LinearLayout) itemView.findViewById(R.id.ll_author_item);
        }
    }

}
