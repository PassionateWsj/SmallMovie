package com.sha1607.smallmovie.category.adatper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.SearchResultBean;
import com.sha1607.smallmovie.moviedetail.MovieDetailActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.WebDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 上午11:00
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "hjjzz";

    private Context mContext;
    private List<SearchResultBean.HitsBean> mData;
    private SearchResultArticleViewHolder mArticleViewHolder;
    private SearchResultMovieViewHolder mMovieViewHolder;
    private List<SearchResultBean.HitsBean.SourceBean.Directors> mDirectors;
    private List<SearchResultBean.HitsBean.SourceBean.CastsBean> mCasts;

    public SearchResultRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public static final int TYPE_MOVIE = 0;
    public static final int TYPE_ARTICLE = 1;

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).get_type().equals("articletype")) {
            return TYPE_ARTICLE;
        }
        return TYPE_MOVIE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ARTICLE) {
            View articleView = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_article, parent, false);
            mArticleViewHolder = new SearchResultArticleViewHolder(articleView);
            return mArticleViewHolder;
        }
        if (viewType == TYPE_MOVIE) {
            View movieView = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_movie, parent, false);
            mMovieViewHolder = new SearchResultMovieViewHolder(movieView);
            return mMovieViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchResultArticleViewHolder) {
            ((SearchResultArticleViewHolder) holder).mPosition = position;
            String date = mData.get(position).get_source().getPubDate();
            date = date.substring(0, date.indexOf("T"));
            ((SearchResultArticleViewHolder) holder).mTvSearchArticleTitle.setText(mData.get(position).get_source().getTitle());
            ((SearchResultArticleViewHolder) holder).mTvSearchArticleContent.setText(mData.get(position).get_source().getContentText());
            ((SearchResultArticleViewHolder) holder).mTvSearchArticleDate.setText(date);
        }
        if (holder instanceof SearchResultMovieViewHolder) {
            ((SearchResultMovieViewHolder) holder).mPosition = position;
            ((SearchResultMovieViewHolder) holder).mTvSearchResultMovieTitle.setText(mData.get(position).get_source().getTitle());
            StringBuilder directorsBuilder = setDirectors(position);
            StringBuilder castsBuilder = setCasts(position);
            ((SearchResultMovieViewHolder) holder).mTvSearchResultMovieDirectors.setText("导演:" + directorsBuilder);
            ((SearchResultMovieViewHolder) holder).mTvSearchResultMovieCasts.setText("演员:" + castsBuilder);
            MyImageLoader.with(mContext, mData.get(position).get_source().getImages().getLarge(),
                    ((SearchResultMovieViewHolder) holder).mIvSearchResultMoviePic, MyImageLoader.LoaderEnum.GLIDE);
        }
    }

    @NonNull
    private StringBuilder setDirectors(int position) {
        mDirectors = mData.get(position).get_source().getDirectors();
        StringBuilder directorsBuilder = new StringBuilder();
        for (int i = 0; i < mDirectors.size(); i++) {
            if (i != mDirectors.size() - 1) {
                directorsBuilder.append(mDirectors.get(i).getName() + "/");
            } else {
                directorsBuilder.append(mDirectors.get(i).getName());
            }
        }
        return directorsBuilder;
    }

    @NonNull
    private StringBuilder setCasts(int position) {
        mCasts = mData.get(position).get_source().getCasts();
        StringBuilder castsBuilder = new StringBuilder();
        for (int i = 0; i < mCasts.size(); i++) {
            if (i != mCasts.size() - 1) {
                castsBuilder.append(mCasts.get(i).getName() + "/");
            } else {
                castsBuilder.append(mCasts.get(i).getName());
            }
        }
        return castsBuilder;
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class SearchResultArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_search_article_title)
        TextView mTvSearchArticleTitle;
        @BindView(R.id.tv_search_article_content)
        TextView mTvSearchArticleContent;
        @BindView(R.id.tv_search_article_date)
        TextView mTvSearchArticleDate;
        int mPosition = -1;
        private String articleContentUrl = "http://www.moviebase.cn/uread/app/viewArt/viewArt-%s.html";

        public SearchResultArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "ArticleItem:::" + mPosition);
                    Intent intent = new Intent(mContext, WebDetailActivity.class);
                    articleContentUrl = String.format(articleContentUrl, mData.get(mPosition).get_id());
                    intent.putExtra("articleContentUrl", articleContentUrl);
                    intent.putExtra("title", mData.get(mPosition).get_source().getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }


    class SearchResultMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_search_result_movie_pic)
        ImageView mIvSearchResultMoviePic;
        @BindView(R.id.tv_search_result_movie_title)
        TextView mTvSearchResultMovieTitle;
        @BindView(R.id.tv_search_result_movie_directors)
        TextView mTvSearchResultMovieDirectors;
        @BindView(R.id.tv_search_result_movie_casts)
        TextView mTvSearchResultMovieCasts;
        int mPosition = -1;

        public SearchResultMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "MovieItem:::" + mPosition);
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("id", mData.get(mPosition).get_source().getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * 设置Data
     *
     * @param data
     */
    public void setData(List<SearchResultBean.HitsBean> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 清空Data
     */
    public void cleanData() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        notifyDataSetChanged();
    }
}
