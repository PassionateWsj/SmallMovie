package com.sha1607.smallmovie.category.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:08
 * e-mail: PassinateWsj@outlook.com
 * name: 分类界面RecyclerView的Adapter
 * desc: 主要负责界面下方热门文章的加载
 * ****************************************************
 */
public class HotArticleRecyclerAdapter extends RecyclerView.Adapter<HotArticleRecyclerAdapter.HotArticleHolder> {

    private List<CategoryListBean.ArticleListBean> mData;
    private Context mContext;

    public HotArticleRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public HotArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_category_hotatricle_recycler_view, parent, false);
        return new HotArticleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotArticleHolder holder, int position) {
        if (mData != null) {
            holder.mPosition = position;
            holder.mTvArticleTitle.setText(mData.get(position).getTitle());
            holder.mTvArticleSourceName.setText(mData.get(position).getSourceName());
            MyImageLoader.with(mContext, mData.get(position).getImage(),
                    holder.mIvArticlePic, MyImageLoader.LoaderEnum.GLIDE);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<CategoryListBean.ArticleListBean> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData = data;
        notifyDataSetChanged();
    }

    class HotArticleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_article_pic)
        ImageView mIvArticlePic;
        @BindView(R.id.tv_article_title)
        TextView mTvArticleTitle;
        @BindView(R.id.tv_article_source_name)
        TextView mTvArticleSourceName;
        int mPosition = -1;

        public HotArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mData != null) {
                        Intent intent = new Intent(mContext, WebDetailActivity.class);
                        intent.putExtra("articleContentUrl", mData.get(mPosition).getArticleContentUrl());
                        intent.putExtra("title", mData.get(mPosition).getTitle());
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }
}
