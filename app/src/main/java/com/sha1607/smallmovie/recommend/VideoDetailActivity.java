package com.sha1607.smallmovie.recommend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;
import com.sha1607.smallmovie.recommend.adatper.RelateVideoAdapter;
import com.sha1607.smallmovie.recommend.adatper.VideoDetailAdapter;
import com.sha1607.smallmovie.recommend.presenter.VideoPresenter;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.webdetail.SourceActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 11:16
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class VideoDetailActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, com.sha1607.smallmovie.recommend.view.VideoView {

    @BindView(R.id.vdieo_conmment_recycler)
    RecyclerView mVdieoConmmentRecycler;
    @BindView(R.id.ll)
    FrameLayout mLl;
    private VideoView mVv;
    private VideoPresenter mVideoPresenter;
    private List<RecommandVideoCommentBean.UserCommentListBean> datas = new ArrayList<RecommandVideoCommentBean.UserCommentListBean>();
    private String mId, videoUrl;
    private VideoDetailAdapter mAdapter;
    private RelateVideoAdapter mRelateVideoAdapter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private View mHeaderView;
    private ImageView mVideodetailStar;
    private ImageView mVdieoHeaderShare;
    private TextView mVdieoHeaderTitle;
    private TextView mVdieoHeaderContent;
    private TextView mVideoSourceNickname;
    private LinearLayout mLlSource;
    private ImageView mVideoSourceImg;
    private RecyclerView mHeaderRecycler;
    private RelativeLayout mRlrelate;
    private RecommandVideoBean.VideoInfoBean.ShareUrlBean mShareUrlBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
    }

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        mAdapter = new VideoDetailAdapter(this);
        mId = getIntent().getStringExtra("id");

        mVideoPresenter = new VideoPresenter(this);
        mVideoPresenter.loadVideoData(mId);

        //添加头部
        mHeaderView = LayoutInflater.from(this).inflate(R.layout.video_detail_header, null);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mVdieoConmmentRecycler.setLayoutManager(new LinearLayoutManager(this));
        mVdieoConmmentRecycler.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        RecyclerViewUtils.setHeaderView(mVdieoConmmentRecycler, mHeaderView);

        mVideodetailStar = (ImageView) mHeaderView.findViewById(R.id.videodetail_star);
        mVdieoHeaderShare = (ImageView) mHeaderView.findViewById(R.id.vdieo_header_share);
        mVdieoHeaderTitle = (TextView) mHeaderView.findViewById(R.id.vdieo_header_title);
        mVdieoHeaderContent = (TextView) mHeaderView.findViewById(R.id.vdieo_header_content);
        mVideoSourceNickname = (TextView) mHeaderView.findViewById(R.id.video_source_nickname);
        mLlSource = (LinearLayout) mHeaderView.findViewById(R.id.ll_source);
        mVideoSourceImg = (ImageView) mHeaderView.findViewById(R.id.video_source_img);
        mHeaderRecycler = (RecyclerView) mHeaderView.findViewById(R.id.header_recycler);
        mRlrelate = (RelativeLayout) mHeaderView.findViewById(R.id.rl_relate);

        mVideodetailStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "请先登录", Toast.LENGTH_SHORT).show();
            }
        });

        mVdieoHeaderShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
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

    @Override
    protected void initViews() {
        super.initViews();
        mVv = (VideoView) findViewById(R.id.vv_video_detail);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(this, "播放完成", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
//        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
//        Toast.makeText(this, "准备好了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadVideoData(RecommandVideoBean bean) {
        mShareUrlBean = bean.getVideoInfo().getShareUrl();

        String requestId = bean.getRequestId();
//        Log.e("--------",requestId);
        String title = bean.getVideoInfo().getTitle();
        mVdieoHeaderTitle.setText(title);

        String description = bean.getVideoInfo().getDescription();
        mVdieoHeaderContent.setText(description);

        try {
            MyImageLoader.with(this, bean.getVideoInfo().getArticleSource().getHeadImgUrl(), mVideoSourceImg, MyImageLoader.LoaderEnum.GLIDE);
            String nickname = bean.getVideoInfo().getArticleSource().getNickname();
            mVideoSourceNickname.setText(nickname);
            mLlSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(VideoDetailActivity.this, SourceActivity.class);
                    intent.putExtra("sourceId", bean.getVideoInfo().getArticleSource().getId());
                    startActivity(intent);
                }
            });
            mRelateVideoAdapter = new RelateVideoAdapter(this);
            mRelateVideoAdapter.setDatas(bean.getVideoInfo().getVideoRelationList());
            LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            mHeaderRecycler.setLayoutManager(layout);
            mHeaderRecycler.setAdapter(mRelateVideoAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
            mRlrelate.setVisibility(View.GONE);
        }


        initVideoData(bean);
    }

    private void initVideoData(RecommandVideoBean bean) {
        videoUrl = bean.getVideoInfo().getVideoUrl();
        mVv.setVideoURI(Uri.parse(videoUrl));
        MediaController mc = new MediaController(this, true, mLl);
        mVv.setMediaController(mc);
        mc.setVisibility(View.GONE);//此操作是为了解决打开视频的时候控制条不走动，需要点击下视频才走动的问题。这样默认情况下用户看不到进度条，当点击视频的时候就可以看到正在走动的进度条了。
        //设置监听
        mVv.setOnPreparedListener(this);
        mVv.setOnErrorListener(this);
        mVv.setOnCompletionListener(this);
    }

    @Override
    public void loadVideoCommentData(RecommandVideoCommentBean bean) {
        for (int i = 0; i < bean.getUserCommentList().size(); i++) {
            datas.add(bean.getUserCommentList().get(i));
        }
        mAdapter.setVideoDatas(datas);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
