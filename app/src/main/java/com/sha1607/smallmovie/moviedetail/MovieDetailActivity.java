package com.sha1607.smallmovie.moviedetail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.bean.MovieDetailBean;
import com.sha1607.smallmovie.moviedetail.adapter.MovieDetailCommentsAdapter;
import com.sha1607.smallmovie.moviedetail.presenter.MovieDetailPresenter;
import com.sha1607.smallmovie.moviedetail.view.MovieDetailView;
import com.sha1607.smallmovie.utils.DisplayUtils;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.PictureShowActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.sha1607.smallmovie.R.id.tv_movie_comment_load_more;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/29 上午09:41
 * e-mail: PassinateWsj@outlook.com
 * name: 电影详情Activity
 * desc:
 * ****************************************************
 */

public class MovieDetailActivity extends BaseActivity implements MovieDetailView {

    @BindView(R.id.tv_movie_title)
    TextView mTvMovieTitle;
    @BindView(R.id.toolbar_movie_activity)
    Toolbar mToolbarMovieActivity;
    @BindView(R.id.iv_movie_detail_background)
    ImageView mIvMovieDetailBackground;
    @BindView(R.id.iv_movie_detail_pic)
    ImageView mIvMovieDetailPic;
    //    @BindView(R.id.tv_movie_synopsis_content)
//    TextView mTvMovieSynopsisContent;
    @BindView(R.id.ll_movie_synopsis_expand)
    LinearLayout mLlMovieSynopsisExpand;
    @BindView(R.id.ll_casts_container)
    LinearLayout mLlCastsContainer;
    @BindView(R.id.ll_prevue_container)
    LinearLayout mLlPrevueContainer;
    @BindView(R.id.ll_stage_photo_container)
    LinearLayout mLlStagePhotoContainer;
    @BindView(R.id.tv_movie_user_comments)
    TextView mTvMovieUserComments;
    @BindView(R.id.lv_movie_user_comments)
    ListView mLvMovieUserComments;
    @BindView(R.id.btn_movie_wanna_see)
    TextView mBtnMovieWannaSee;
    @BindView(R.id.btn_movie_had_saw)
    TextView mBtnMovieHadSaw;
    @BindView(R.id.btn_movie_recommend)
    TextView mBtnMovieRecommend;
    @BindView(R.id.scroll_view_movie_detail)
    NestedScrollView mScrollViewMovieDetail;
    @BindView(R.id.tv_movie_detail_title)
    TextView mTvMovieDetailTitle;
    @BindView(R.id.tv_movie_detail_original_title)
    TextView mTvMovieDetailOriginalTitle;
    @BindView(R.id.tv_movie_detail_countries)
    TextView mTvMovieDetailCountries;
    @BindView(R.id.tv_movie_detail_genres)
    TextView mTvMovieDetailGenres;
    @BindView(R.id.tv_movie_detail_durations)
    TextView mTvMovieDetailDurations;
    @BindView(R.id.tv_movie_detail_pubdates)
    TextView mTvMovieDetailPubdates;
    @BindView(R.id.ll_trailers)
    LinearLayout mLlTrailers;
    //    @BindView(R.id.iv_movie_synopsis_expand_pic)
//    ImageView mIvMovieSynopsisExpandPic;
    @BindView(R.id.ll_casts)
    LinearLayout mLlCasts;
    @BindView(R.id.ll_stage_photo)
    LinearLayout mLlStagePhoto;
    //    @BindView(R.id.expandable_text)
//    TextView mExpandableText;
//    @BindView(R.id.expand_collapse)
//    ImageButton mExpandCollapse;
    @BindView(R.id.expand_movie_synopsis)
    ExpandableTextView mExpandMovieSynopsis;
    @BindView(tv_movie_comment_load_more)
    TextView mTvMovieCommentLoadMore;
    /**
     * 演员的照片
     */
    private ImageView mIvCastsAvatar;
    /**
     * 演员名字
     */
    private TextView mTvCastsName;
    /**
     * 演员类别：导演、演员
     */
    private TextView mTvCastsType;
    /**
     * MovieDetail的Presenter命令者
     */
    private MovieDetailPresenter mMoviePresenter;
    /**
     * 是否能加载更多，0 - false，1 - true
     */
    private String mCanLoadMore;
    /**
     * 用户评论当前的页数
     */
    private int pageContext = 1;
    /**
     * 电影的id
     */
    private String mId;
    /**
     * 电影详情中的海报
     */
    private String mImgUrl;
    /**
     * 电影详情中，国家的集合
     */
    private List<String> mCountries;
    /**
     * 用于拼接电影类型，国家
     */
    private StringBuilder mBuilder;
    /**
     * 电影类型的集合
     */
    private List<String> mGenres;
    /**
     * 显示演员Item的View
     */
    private View mCastItemView;
    /**
     * 显示电影花絮Item的View
     */
    private View mTrailersItemView;
    /**
     * 显示电影花絮图片的ImageView
     */
    private ImageView mIvTrailersPic;
    /**
     * 用户评论ListView的适配器
     */
    private MovieDetailCommentsAdapter mMovieDetailCommentsAdapter;
    /**
     * 用户评论是否点赞的ArrayList集合
     */
    private List<Boolean> mIsPraise;
    /**
     * 用户评论的数量
     */
    private int mCount;
    /**
     * 用户评论的ListView的参数，用于动态设置高度
     */
    private ViewGroup.LayoutParams mParams;
    /**
     * 包含导演、演员图片url的ArrayList
     */
    private List<String> mCastsPicList;
    /**
     * 包含导演、演员图片url的String数组
     */
    private String[] mPicUrlArray;
    /**
     * 包含导演、演员图片url的String数组
     */
    private List<String> mStagePicList;
    /**
     * 当前演员图片url
     */
    private String mCurrentCastUrl;
    /**
     * 判断剧情简介是否展开的标识变量
     */
//    private boolean isExpand = false;
    private boolean isMarkedWannaSee = false;
    private boolean isMarkedHadSaw = false;
    private MovieDetailBean.ShareUrlBean mShareUrlBean;

    /**
     * 初始化视图
     */
    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbarMovieActivity);
        getSupportActionBar().setTitle("");
    }

    /**
     * 初始化监听器
     */
    @Override
    protected void initListener() {
        mToolbarMovieActivity.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    showShare();    // 分享
                }
                return true;
            }
        });
    }

    /**
     * 三方分享
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mShareUrlBean.getTitle());
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mShareUrlBean.getUrl());
// text是分享文本，所有平台都需要这个字段
        oks.setText(mShareUrlBean.getContent());
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(mShareUrlBean.getImageUrlSrc());//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mShareUrlBean.getUrl());
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mShareUrlBean.getUrl());
// 启动分享GUI
        oks.show(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        mId = getIntent().getStringExtra("id");
        mMoviePresenter = new MovieDetailPresenter(this);
        if (mId != null) {
            mMoviePresenter.loadMovieDetailData(mId);
            mMoviePresenter.loadMovieCommentsData(pageContext, mId);
        }
        mMovieDetailCommentsAdapter = new MovieDetailCommentsAdapter(this);
        mLvMovieUserComments.setAdapter(mMovieDetailCommentsAdapter);
    }

    /**
     * 设置分享的menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail_activity, menu);
        return true;
    }

    /**
     * 设置自定义ToolBar点击按钮的响应事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    /**
     * 想看、已看、推荐、剧情简介展开的点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_movie_wanna_see, R.id.btn_movie_had_saw, R.id.btn_movie_recommend, R.id.ll_movie_synopsis_expand, tv_movie_comment_load_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_movie_wanna_see:
                if (isMarkedWannaSee) {
                    setDrawableLeft(R.drawable.movie_btn_jk, mBtnMovieWannaSee);
                    isMarkedWannaSee = false;
                    Toast.makeText(this, "取消想看", Toast.LENGTH_SHORT).show();
                } else {
                    setDrawableLeft(R.drawable.movie_btn_jk_y, mBtnMovieWannaSee);
                    isMarkedWannaSee = true;
                    Toast.makeText(this, "听说你想看", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_movie_had_saw:
                if (isMarkedHadSaw) {
                    setDrawableLeft(R.drawable.movie_btn_new_jk_n, mBtnMovieHadSaw);
                    isMarkedHadSaw = false;
                    Toast.makeText(this, "取消已看", Toast.LENGTH_SHORT).show();
                } else {
                    setDrawableLeft(R.drawable.movie_btn_new_jk_y, mBtnMovieHadSaw);
                    isMarkedHadSaw = true;
                    Toast.makeText(this, "已经看过了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_movie_recommend:
                Toast.makeText(this, "去推荐吧", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_movie_synopsis_expand:
//                if (isExpand) {
//                    mTvMovieSynopsisContent.setMaxLines(5);
//                    mIvMovieSynopsisExpandPic.setImageResource(R.drawable.text_expand);
//                    isExpand = false;
//                } else {
//                    mTvMovieSynopsisContent.setMaxLines(Integer.MAX_VALUE);
//                    mIvMovieSynopsisExpandPic.setImageResource(R.drawable.text_expand_true);
//                    isExpand = true;
//                }
                break;
            case R.id.tv_movie_comment_load_more:
                mMoviePresenter.loadMovieCommentsData(++pageContext, mId);
                break;
        }
    }

    /**
     * 设置TextView的DrawableLeft
     *
     * @param id
     * @param textView
     */
    private void setDrawableLeft(int id, TextView textView) {
        Drawable drawable = getResources()
                .getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 成功下载到电影详情的数据
     *
     * @param movieDetailBean
     */
    @Override
    public void onMovieDetailBeanResultSuccess(MovieDetailBean movieDetailBean) {
        mShareUrlBean = movieDetailBean.getShareUrl();
        initMovieDetailData(movieDetailBean);
    }

    /**
     * 设置电影详情的数据
     *
     * @param movieDetailBean
     */
    private void initMovieDetailData(MovieDetailBean movieDetailBean) {
        mTvMovieTitle.setText(movieDetailBean.getMovieInfo().get_source().getTitle());  //设置界面上方ToolBar的Title
        mImgUrl = movieDetailBean.getMovieInfo().get_source().getImages().getLarge();
        MyImageLoader.with(this, mImgUrl, mIvMovieDetailPic, MyImageLoader.LoaderEnum.GLIDE);
        MyImageLoader.with(this, mImgUrl, mIvMovieDetailBackground, MyImageLoader.LoaderEnum.JIAJUN);
        mTvMovieDetailTitle.setText(movieDetailBean.getMovieInfo().get_source().getTitle());
        mTvMovieDetailOriginalTitle.setText(movieDetailBean.getMovieInfo().get_source().getOriginal_title());
        mCountries = movieDetailBean.getMovieInfo().get_source().getCountries();
        mBuilder = new StringBuilder();
        for (String country : mCountries) {
            mBuilder.append(" " + country);
        }
        mTvMovieDetailCountries.setText("国家:" + mBuilder);
        mGenres = movieDetailBean.getMovieInfo().get_source().getGenres();
        mBuilder.delete(0, mBuilder.length());
        for (String genre : mGenres) {
            mBuilder.append(" " + genre);
        }
        mTvMovieDetailGenres.setText("类型:" + mBuilder);
        try {
            mTvMovieDetailDurations.setText("时长: " + movieDetailBean.getMovieInfo().get_source().getDurations().get(0));
            mTvMovieDetailPubdates.setText(movieDetailBean.getMovieInfo().get_source().getPubdates().get(0));
        } catch (NullPointerException e) {

        }
//        mTvMovieSynopsisContent.setText(movieDetailBean.getMovieInfo().get_source().getSummary());
        mExpandMovieSynopsis.setText(movieDetailBean.getMovieInfo().get_source().getSummary());
        if (movieDetailBean.getMovieInfo().get_source().getCasts() == null || movieDetailBean.getMovieInfo().get_source().getCasts().size() == 0) {
            mLlCasts.setVisibility(View.GONE);
        } else {
            mLlCasts.setVisibility(View.VISIBLE);
            initCastsView(movieDetailBean);  // 实现演员界面的初始化和加载
        }
        if (movieDetailBean.getMovieInfo().get_source().getTrailers() == null || movieDetailBean.getMovieInfo().get_source().getTrailers().size() == 0) {
            mLlTrailers.setVisibility(View.GONE);
        } else {
            mLlTrailers.setVisibility(View.VISIBLE);
            initTrailersView(movieDetailBean);  // 实现电影花絮界面的初始化和加载
        }
        if (movieDetailBean.getMovieInfo().get_source().getStage_photos() == null || movieDetailBean.getMovieInfo().get_source().getStage_photos().size() == 0) {
            mLlStagePhoto.setVisibility(View.GONE);
        } else {
            mLlStagePhoto.setVisibility(View.VISIBLE);
            initStagePhotoView(movieDetailBean);    // 实现舞台照界面的初始化和加载
        }
    }

    /**
     * 实现舞台照界面的初始化和加载
     *
     * @param movieDetailBean
     */
    private void initStagePhotoView(MovieDetailBean movieDetailBean) {
        mStagePicList = new ArrayList<>();
        for (String stagePhotoUrl : movieDetailBean.getMovieInfo().get_source().getStage_photos()) {
            ImageView stagePhotos = new ImageView(this);
            mStagePicList.add(stagePhotoUrl);
            stagePhotos.setScaleType(ImageView.ScaleType.CENTER_CROP);
            stagePhotos.setPadding(DisplayUtils.dp2px(this, 10), 0, DisplayUtils.dp2px(this, 10), 0);
            MyImageLoader.with(this, stagePhotoUrl, stagePhotos, MyImageLoader.LoaderEnum.GLIDE);
            stagePhotos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPicUrlArray = mStagePicList.toArray(new String[mStagePicList.size()]);
                    Intent intent = new Intent(mActivity, PictureShowActivity.class);
                    intent.putExtra("image", stagePhotoUrl);
                    intent.putExtra("shuzu", mPicUrlArray);
                    startActivity(intent);
                }
            });
            mLlStagePhotoContainer.addView(stagePhotos, DisplayUtils.dp2px(this, 130), LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    /**
     * 实现电影花絮界面的初始化和加载
     *
     * @param movieDetailBean
     */
    private void initTrailersView(MovieDetailBean movieDetailBean) {
        for (MovieDetailBean.MovieInfoBean.SourceBean.TrailersBean trailersBean : movieDetailBean.getMovieInfo().get_source().getTrailers()) {
            initTrailersItemView(); // 初始化单个视频花絮的视图
            MyImageLoader.with(this, trailersBean.getMedium(), mIvTrailersPic, MyImageLoader.LoaderEnum.GLIDE);
            mTrailersItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentTrailerActivity(trailersBean.getResource_url());
                    Toast.makeText(mActivity, "电影花絮:trailersBean:" + trailersBean.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            mLlPrevueContainer.addView(mTrailersItemView, DisplayUtils.dp2px(this, 180), LinearLayout.LayoutParams.MATCH_PARENT);
        }
        for (MovieDetailBean.MovieInfoBean.SourceBean.BloopersBean bloopersBean : movieDetailBean.getMovieInfo().get_source().getBloopers()) {
            initTrailersItemView(); // 初始化单个视频花絮的视图
            MyImageLoader.with(this, bloopersBean.getMedium(), mIvTrailersPic, MyImageLoader.LoaderEnum.GLIDE);
            mTrailersItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentTrailerActivity(bloopersBean.getResource_url());
                    Toast.makeText(mActivity, "电影花絮:trailersBean:" + bloopersBean.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            mLlPrevueContainer.addView(mTrailersItemView, DisplayUtils.dp2px(this, 180), LinearLayout.LayoutParams.MATCH_PARENT);

        }
    }

    /**
     * 启动 MovieTrailerActivity
     *
     * @param resource_url
     */
    private void intentTrailerActivity(String resource_url) {
        Intent intent = new Intent(mActivity, MovieTrailerActivity.class);
        intent.putExtra("resource_url", resource_url);
        startActivity(intent);
    }

    /**
     * 初始化单个视频花絮的视图
     */
    private void initTrailersItemView() {
        mTrailersItemView = LayoutInflater.from(this).inflate(R.layout.item_movie_detail_trailers, null, false);
        mIvTrailersPic = (ImageView) mTrailersItemView.findViewById(R.id.iv_trailers_pic);
    }

    /**
     * 实现演员界面的初始化和加载
     *
     * @param movieDetailBean
     */
    private void initCastsView(MovieDetailBean movieDetailBean) {
        mCastsPicList = new ArrayList<>();
        if (movieDetailBean.getMovieInfo().get_source().getDirectors().size() > 0) {
            for (MovieDetailBean.MovieInfoBean.SourceBean.DirectorsBean directorsBean : movieDetailBean.getMovieInfo().get_source().getDirectors()) {
                initCastItemView(); // 初始化单个演员表的视图
                MyImageLoader.with(this, directorsBean.getAvatar().getLarge(),
                        mIvCastsAvatar, MyImageLoader.LoaderEnum.GLIDE);
                mTvCastsName.setText(directorsBean.getName());
                mTvCastsType.setText("导演");
                mCastsPicList.add(directorsBean.getAvatar().getLarge());    // 将每个导演的图片添加到演员图片数组中
                mCastItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPicUrlArray = mCastsPicList.toArray(new String[mCastsPicList.size()]);
                        mCurrentCastUrl = directorsBean.getAvatar().getLarge();
                        Intent intent = new Intent(mActivity, PictureShowActivity.class);
                        intent.putExtra("image", mCurrentCastUrl);
                        intent.putExtra("shuzu", mPicUrlArray);
                        startActivity(intent);
//                        Toast.makeText(mActivity, "mTvCastsName::" + directorsBean.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                mLlCastsContainer.addView(mCastItemView, DisplayUtils.dp2px(this, 90), LinearLayout.LayoutParams.MATCH_PARENT);
            }
        }
        for (MovieDetailBean.MovieInfoBean.SourceBean.CastsBean castsBean : movieDetailBean.getMovieInfo().get_source().getCasts()) {
            initCastItemView(); // 初始单个化演员表的视图
            MyImageLoader.with(this, castsBean.getAvatar().getLarge(), mIvCastsAvatar, MyImageLoader.LoaderEnum.GLIDE);
            mTvCastsName.setText(castsBean.getName());
            mTvCastsType.setText("演员");
            mCastsPicList.add(castsBean.getAvatar().getLarge());    // 将每个演员的图片添加到演员图片数组中
            mCastItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPicUrlArray = mCastsPicList.toArray(new String[mCastsPicList.size()]);
                    mCurrentCastUrl = castsBean.getAvatar().getLarge();
                    Intent intent = new Intent(mActivity, PictureShowActivity.class);
                    intent.putExtra("image", mCurrentCastUrl);
                    intent.putExtra("shuzu", mPicUrlArray);
                    startActivity(intent);
                    Toast.makeText(mActivity, "mTvCastsName::" + castsBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mLlCastsContainer.addView(mCastItemView, DisplayUtils.dp2px(this, 90), LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    /**
     * 初始化单个演员表的视图
     */
    private void initCastItemView() {
        mCastItemView = LayoutInflater.from(this).inflate(R.layout.item_movie_detail_casts, null, false);
        mIvCastsAvatar = (ImageView) mCastItemView.findViewById(R.id.iv_casts_avatar);
        mTvCastsName = (TextView) mCastItemView.findViewById(R.id.tv_casts_name);
        mTvCastsType = (TextView) mCastItemView.findViewById(R.id.tv_casts_type);
    }

    /**
     * 下载电影评论失败
     */
    @Override
    public void onMovieDetailBeanResultFailure() {

    }

    /**
     * 成功下载到电影评论的数据
     *
     * @param movieCommentsBean
     */
    @Override
    public void onMovieCommentsBeanResultSuccess(MovieCommentsBean movieCommentsBean) {
        mCanLoadMore = movieCommentsBean.getCanLoadMore();
        if (mCanLoadMore.equals("0")) {
            mTvMovieCommentLoadMore.setVisibility(View.GONE);
        } else {
            mTvMovieCommentLoadMore.setVisibility(View.VISIBLE);
        }
        mIsPraise = new ArrayList<>();
        for (int i = 0; i < movieCommentsBean.getCommentList().size(); i++) {
            mIsPraise.add(false);
        }
        mMovieDetailCommentsAdapter.setData(movieCommentsBean.getCommentList(), mIsPraise);
        if (mCount == 0) {
            mScrollViewMovieDetail.smoothScrollTo(0, 0); // 如果第一次进入滚动到顶部
        }
        mCount = mMovieDetailCommentsAdapter.getCount();
        mTvMovieUserComments.setText(Html.fromHtml("<font color=\"black\">用户评论</font><font color=\"blue\"> " + mCount + " </font><font color=\"black\">条</font>"));
        mParams = mLvMovieUserComments.getLayoutParams();
        mParams.height = DisplayUtils.dp2px(this, 160 * mCount);
        mLvMovieUserComments.setLayoutParams(mParams);
    }

    /**
     * 下载电影详情数据失败
     */
    @Override
    public void onMovieCommentsBeanResultFailure() {

    }

}
