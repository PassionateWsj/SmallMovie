package com.sha1607.smallmovie.webdetail.adapter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 -1:43
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
import com.sha1607.smallmovie.bean.SourceBean;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.AuthorActivity;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceHolder> {
    private List<SourceBean.ContentListBean> mContentListBeen;
    private Context mContext;
    private String baseUrl = "http://www.moviebase.cn/uread/app/viewArt/viewArt-%s.html";

    public SourceAdapter(Context context) {
        mContext = context;
    }

    @Override
    public SourceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_source_list, parent, false);
        SourceHolder holder = new SourceHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SourceHolder holder, int position) {
        SourceBean.ContentListBean contentListBean = mContentListBeen.get(position);
        MyImageLoader.with(mContext, contentListBean.getObject().getImgUrl(), holder.mImageView, MyImageLoader.LoaderEnum.GLIDE);
        holder.mTextView_content.setText(contentListBean.getObject().getTitle());
        try {
            holder.mTextView_author.setText(contentListBean.getObject().getAuthorInfos().get(0).getAuthorName());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        holder.mLlSourceContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebDetailActivity.class);
                intent.putExtra("articleContentUrl", String.format(baseUrl, contentListBean.getObject().getId()));
                intent.putExtra("title", contentListBean.getObject().getTitle());
                mContext.startActivity(intent);
            }
        });
        holder.mTextView_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AuthorActivity.class);
                String id = contentListBean.getObject().getAuthorInfos().get(0).getId();
                intent.putExtra("type", id);
                mContext.startActivity(intent);
            }
        });


    }

    public void setData(List<SourceBean.ContentListBean> datas) {
        if (mContentListBeen == null) {
            mContentListBeen = new ArrayList<>();
        }
        mContentListBeen.addAll(datas);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mContentListBeen == null ? 0 : mContentListBeen.size();
    }

    class SourceHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView_content;
        TextView mTextView_author;
        LinearLayout mLlSourceContainer;

        public SourceHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_show_source);
            mTextView_content = (TextView) itemView.findViewById(R.id.tv_source_content);
            mTextView_author = (TextView) itemView.findViewById(R.id.tv_source_author);
            mLlSourceContainer = (LinearLayout) itemView.findViewById(R.id.ll_source_container);
        }
    }

}
