package com.sha1607.smallmovie.contentactivity;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 4:40
 * name:
 * desc:
 * step:
 **********************************
 */

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;
import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.contentactivity.adapter.ContentAdapter;
import com.sha1607.smallmovie.contentactivity.adapter.ContentArticleAdapter;
import com.sha1607.smallmovie.contentactivity.adapter.ContentFilmAdapter;
import com.sha1607.smallmovie.contentactivity.presenter.ContentPresenter;
import com.sha1607.smallmovie.contentactivity.view.ContentView;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.Order;
import com.sha1607.smallmovie.utils.MyImageLoader;
import com.sha1607.smallmovie.utils.RxBus;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ContentActivity extends BaseActivity implements ContentView {
    private static final String TAG = "hjjzz";

    @BindView(R.id.iv_header_show)
    ImageView mIvHeaderShow;
    @BindView(R.id.toorbar_layout_content)
    Toolbar mToorbarLayoutContent;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ContentPresenter mContentPresenter;
    private String mType;
    private ContentAdapter mAdapter;
    private ContentFilmAdapter mFilmAdapter;
    private String objectType;
    private View mHeadView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private String url = "http://img.moviebase.cn/img/poster/2016/10/c821f0b3de78407fbda5ca13ec5390cf.jpeg@512w";

    @BindView(R.id.rl_list)
    RecyclerView mRlList;
    private String mImgUrl;
    private ContentArticleAdapter mArticleAdapter;
    private Dao<Order, Long> mOrderDao;
    private String mTitle;
    private int mSubscribeNum;
    private int mArticlesNum;
    private boolean isExist = false;
    private List<Order> mOrders;
    private String shareUrl = "http://www.moviebase.cn/uread/app/topic/shareViewTopic-%s.html";
    private boolean mIsexist;

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = this.getWindow();
            //设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        try {
            mOrderDao = DBHleper.getInstance(this).getOrderDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            mOrders = mOrderDao.queryForAll();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        mTitle = getIntent().getStringExtra("title");
        mSubscribeNum = getIntent().getIntExtra("subscribeNum", 0);
        mArticlesNum = getIntent().getIntExtra("articlesNum", 0);
        Toast.makeText(this, mSubscribeNum + "   " + mArticlesNum + mTitle, Toast.LENGTH_SHORT).show();
        String desc = mArticlesNum + "篇" + "/" + mSubscribeNum + "人订阅";
        mType = getIntent().getStringExtra("id");
        mImgUrl = getIntent().getStringExtra("imgUrl");

        for (int i = 0; i < mOrders.size(); i++) {

            if (mOrders.get(i).getImgUrl().equals(mImgUrl)) {
                isExist = true;
            }
        }

        mContentPresenter = new ContentPresenter(this);
        mContentPresenter.loadData(mType);
        mRlList.setLayoutManager(new LinearLayoutManager(this));
        mHeadView = LayoutInflater.from(this).inflate(R.layout.headview_content, null);

        TextView textView = (TextView) mHeadView.findViewById(R.id.tv_desc_content);
        Button button = (Button) mHeadView.findViewById(R.id.btn_subscription);
        if (isExist) {
            button.setText("已订阅");
            button.setTextColor(this.getResources().getColor(R.color.colorTabText));
            button.setBackground(this.getResources().getDrawable(R.drawable.details_button_selector_true));
        } else {
            button.setText("订阅");
            button.setTextColor(this.getResources().getColor(R.color.colorLightBlue));
            button.setBackground(this.getResources().getDrawable(R.drawable.details_button_selector));
        }
        textView.setText(desc);

        MyImageLoader.with(this, mImgUrl, mIvHeaderShow, MyImageLoader.LoaderEnum.GLIDE);
        mIvHeaderShow.setColorFilter(R.color.colorhalf);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mCollapsingToolbarLayout.setTitle(mTitle);
        setSupportActionBar(mToorbarLayoutContent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsexist = false;
                try {
                    List<Order> orders = mOrderDao.queryForAll();
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getImgUrl().equals(mImgUrl)) {
                            mIsexist = true;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Toast.makeText(ContentActivity.this, isExist + "", Toast.LENGTH_SHORT).show();
                Order order = new Order(mTitle, mImgUrl, mType, mArticlesNum + "", mSubscribeNum + "", true);
                if (!mIsexist) {
                    button.setText("已订阅");
                    button.setTextColor(mActivity.getResources().getColor(R.color.colorTabText));
                    button.setBackground(mActivity.getResources().getDrawable(R.drawable.details_button_selector_true));
                    Toast.makeText(ContentActivity.this, isExist + "", Toast.LENGTH_SHORT).show();
                    try {
                        mOrderDao.createIfNotExists(order);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    mIsexist = true;
                } else {
                    button.setText("订阅");
                    button.setTextColor(mActivity.getResources().getColor(R.color.colorLightBlue));
                    button.setBackground(mActivity.getResources().getDrawable(R.drawable.details_button_selector));
                    try {

                        int j = 0;
                        List<Order> orders1 = mOrderDao.queryForAll();
                        Toast.makeText(ContentActivity.this, "" + orders1.size(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < orders1.size(); i++) {
                            if (orders1.get(i).getImgUrl().equals(order.getImgUrl())) {
                                j = i;
                            }
                        }
                        mOrderDao.delete(orders1.get(j));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    mIsexist = false;
                }
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toorbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toorbar_share:
                showShare();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 三方分享
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mTitle);
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(String.format(shareUrl, mType));
// text是分享文本，所有平台都需要这个字段
        oks.setText(mTitle);
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(mImgUrl);//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(String.format(shareUrl, mType));
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(String.format(shareUrl, mType));
// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void loadData(ContentFilmBean datas) {
        Log.e("999", datas.getCanLoadMore());
        objectType = datas.getArticleList().get(0).getObjectType();
        if (objectType.equals("1")) {
            mAdapter = new ContentAdapter(this);
            mAdapter.setData(datas);

            mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
            mRlList.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
            RecyclerViewUtils.setHeaderView(mRlList, mHeadView);
        } else if (objectType.equals("2")) {
            mFilmAdapter = new ContentFilmAdapter(this);
            mFilmAdapter.setData(datas);

            mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mFilmAdapter);
            mRlList.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
            RecyclerViewUtils.setHeaderView(mRlList, mHeadView);
        } else {
            mArticleAdapter = new ContentArticleAdapter(this);
            mArticleAdapter.setData(datas);

            mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mArticleAdapter);
            mRlList.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
            RecyclerViewUtils.setHeaderView(mRlList, mHeadView);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsexist == isExist) {
            return;
        } else {
            RxBus.getDefault().post(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Order order : mOrderDao) {
            order = null;
        }
        mOrderDao = null;
    }
}
