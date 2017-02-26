package com.sha1607.smallmovie.webdetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.webdetail.customview.MyWebView;
import com.sha1607.smallmovie.webdetail.presenter.WebDetailPresenter;
import com.sha1607.smallmovie.webdetail.view.WebDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/26 14:17
 * name:
 * desc:
 * step:
 * ***********************************************************
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebDetailActivity extends AppCompatActivity implements WebDetailView {

    private static final String TAG = "hjjzz";

    @BindView(R.id.WebView_title_tv)
    TextView mWebViewTitleTv;
    @BindView(R.id.WebView_title_back)
    ImageView mWebViewTitleBack;
    @BindView(R.id.WebView_title_share)
    ImageView mWebViewTitleShare;
    @BindView(R.id.WebView_bottem_comment)
    LinearLayout mWebViewBottemComment;
    @BindView(R.id.WebView_bottem_zan)
    LinearLayout mWebViewBottemZan;
    @BindView(R.id.WebView_bottem_more)
    LinearLayout mWebViewBottemMore;
    @BindView(R.id.WebView_bottem)
    LinearLayout mWebViewBottem;
    @BindView(R.id.recommand_web_detail_webView)
    MyWebView mRecommandWebDetailWebView;
    @BindView(R.id.WebView_bottem_zan_iv)
    ImageView mWebViewBottemZanIv;
    @BindView(R.id.WebView_bottem_zan_tv)
    TextView mWebViewBottemZanTv;

    //通过getIntent得到
    private WebDetailPresenter mPresenter;
    private String mTitle;
    private String mArticleContentUrl;
    private boolean flag = true;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_detail);
        ButterKnife.bind(this);

        mArticleContentUrl = getIntent().getStringExtra("articleContentUrl");//TODO 传
        mTitle = getIntent().getStringExtra("title");
        Log.i(TAG, "WebDetailActivity:::getArticleContentUrl:::" + mArticleContentUrl);

        mPresenter = new WebDetailPresenter(this);

        onSetWebViewData(mArticleContentUrl);
        onSetToolBarTile(mTitle);

    }


    @Override
    public void onSetWebViewData(String url) {
        //设置WebView的信息

        mRecommandWebDetailWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //"http://www.moviebase.cn/uread/app/viewArt/viewArt-0ea92217268248888e7239c719ce2952.html?appVersion=1.7.0&amp;osType=2&amp;platform=2"
        mRecommandWebDetailWebView.loadUrl(url);
        mRecommandWebDetailWebView.getSettings().setJavaScriptEnabled(true);

        mRecommandWebDetailWebView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        mRecommandWebDetailWebView.addJavascriptInterface(new JavascriptInterface(this), "textlistener");
        mRecommandWebDetailWebView.addJavascriptInterface(new JavascriptInterface(this),"authorlistener");

        mRecommandWebDetailWebView.setWebViewClient(new MyWebViewClient());


        mRecommandWebDetailWebView.setOnScrollChangedCallback(new MyWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy < 0) {
                    //显示
                    mWebViewBottem.setVisibility(View.VISIBLE);
                } else {
//                    Toast.makeText(WebDetailActivity.this, "HAHAHA", Toast.LENGTH_SHORT).show();
                    mWebViewBottem.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onSetToolBarTile(String title) {
        mWebViewTitleTv.setText(title);
    }

    @OnClick({R.id.WebView_title_back, R.id.WebView_title_share, R.id.WebView_bottem_comment, R.id.WebView_bottem_zan, R.id.WebView_bottem_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.WebView_title_back:
                finish();
                break;
            case R.id.WebView_title_share:
                showShare();
                break;
            case R.id.WebView_bottem_comment:
                Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.WebView_bottem_zan:
//                Toast.makeText(this, "赞", Toast.LENGTH_SHORT).show();
                if (flag) {
                    mWebViewBottemZanIv.setImageResource(R.drawable.article_detail_praise_new_d);
                    mWebViewBottemZanTv.setTextColor(getResources().getColor(R.color.colorBlue));
                    Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else {
                    mWebViewBottemZanIv.setImageResource(R.drawable.article_detail_praise_new);
                    mWebViewBottemZanTv.setTextColor(getResources().getColor(R.color.colorTipGray));
                    Toast.makeText(this, "取消点赞", Toast.LENGTH_SHORT).show();
                    flag = true;
                }
                break;
            case R.id.WebView_bottem_more:
//                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
                window.setWindowAnimations(R.style.mystyle);//添加动画
                dialog.show();
                View inflate = LayoutInflater.from(this).inflate(R.layout.web_dialog, null);
                window.setContentView(inflate);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);

                TextView webDialogCollect;
                RelativeLayout webDialogCancle;

                webDialogCollect = (TextView) inflate.findViewById(R.id.web_dialog_collect);
                webDialogCancle = (RelativeLayout) inflate.findViewById(R.id.web_dialog_cancle);

                webDialogCollect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(WebDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    }
                });

                webDialogCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;
        }
    }

    /**
     * 三方分享
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mTitle);
// titleUrl是标题的网络链接QQ和QQ空间等使用
        oks.setTitleUrl(mArticleContentUrl);
// text是分享文本，所有平台都需要这个字段
        oks.setText(mTitle);
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImageUrl(mArticleContentUrl);//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mArticleContentUrl);
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mArticleContentUrl);
// 启动分享GUI
        oks.show(this);
    }

    public void addAuthorClickListener(){
        mRecommandWebDetailWebView.loadUrl("javascript:(function(){" +
                "var objs=document.getElementsByTagName(\"span\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{"
                + "      objs[i].onclick=function()  " +
                "    {   "
                + "     window.authorlistener.openAuthor(this.getAttribute('data-author-id'));     " +
                " window.textlistener.openText(this.getAttribute('data-source-id')); "+
                "    }  " +
                "}" +
                "})()");
    }
   /* public void addTextClickListener() {
        mRecommandWebDetailWebView.loadUrl("javascript:(function(){" +
                "var objs=document.getElementsByTagName(\"span\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{"
                + "      objs[i].onclick=function()  " +
                "    {   "
                + "     window.textlistener.openText(this.getAttribute('data-source-id'));     " +
                "    }  " +
                "}" +
                "})()");
    }*/
    public void addImageClickListener() {
        mRecommandWebDetailWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "var head=objs[0].src;" +
                "var arr=[];" +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "      arr[i]=objs[i].getAttribute('data-original');" +
                "}" +
                "for(var i=0;i<objs.length-1;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src,arr,head);  " +
                "    }  " +
                "}" +
                "})()");
    }
   /* public void addTextClickListener(){
        mRecommandWebDetailWebView.loadUrl("javascript:(function(){" +


                "var objs = document.getElementsByTagName(\"span\"); " +

                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.textlistener.openText(this.getAttribute('data-source-id'));  " +
                "    }  " +
                "}" +
                "})()");
    }*/


    private class JavascriptInterface {
        private Context mContext;

        public JavascriptInterface(Context context) {
            this.mContext = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img, String[] arr, String head) {
            if (arr[0] == null) {
                for (int i = 0; i < arr.length; i++) {
                    Log.e("ceshi", arr[i] + " " + i);
                }
                String[] imgUrl = new String[arr.length - 2];

                imgUrl[0] = head;
                for (int i = 1; i < imgUrl.length; i++) {
                    imgUrl[i] = arr[i];
                }
                if (img.equals(arr[arr.length - 2])) {
                    // TODO: 2016/10/29


                    return;
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("image", img);
                    intent.putExtra("shuzu", imgUrl);
                    intent.setClass(mContext, PictureShowActivity.class);
                    mContext.startActivity(intent);
                }
            } else {
                for (int i = 0; i < arr.length; i++) {
                    Log.e("ceshi", arr[i] + " " + i);
                }
                String[] imgUrl = new String[arr.length - 2];
                for (int i = 0; i < imgUrl.length; i++) {
                    imgUrl[i] = arr[i];
                }
                if (img.equals(arr[arr.length - 2])) {
                    // TODO: 2016/10/29
                    return;
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("image", img);
                    intent.putExtra("shuzu", imgUrl);
                    intent.setClass(mContext, PictureShowActivity.class);
                    mContext.startActivity(intent);
                }
            }
            // if(!head.equals(arr[0]));
          /*  String[] url=new String[arr.length-1];
            url[0]=head;
            for (int i = 1; i < arr.length-1; i++) {
                url[i]=arr[i];
            }


            String s = arr[0];
            if (img.equals(url[url.length-1])){
                Toast.makeText(mContext, "555", Toast.LENGTH_SHORT).show();
                return ;
            }*/


//            Intent intent = new Intent();
//            intent.putExtra("image", img);
//            intent.putExtra("shuzu",arr);
//            intent.setClass(mContext, PictureShowActivity.class);
//            mContext.startActivity(intent);

        }

        @android.webkit.JavascriptInterface
        public void openText(String is) {
            Log.e("sdasdasdas", is);
          //  Toast.makeText(WebDetailActivity.this, is, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, SourceActivity.class);
            intent.putExtra("sourceId", is);
            mContext.startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public  void openAuthor(String id){
            if (id==null){
                return;
            }
            Toast.makeText(WebDetailActivity.this, id+"555555", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(mContext,AuthorActivity.class);
            intent.putExtra("type",id);
            mContext.startActivity(intent);
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);

            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListener();
            //addTextClickListener();
        //    addTextClickListener();
            addAuthorClickListener();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }

}


