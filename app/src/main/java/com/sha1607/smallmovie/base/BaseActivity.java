package com.sha1607.smallmovie.base;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/25 下午 10:01
 * name:
 * desc:
 * step:
 **********************************
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public abstract  class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadXml();
        initViews();
        initData();
        initListener();
        mActivity = this;
    }
    protected abstract void loadXml();

    /**
     * 初始化控件
     */
    protected void initViews() {}

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected void initListener(){}

    /**
     * 展示一个短时长的toast
     */
    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

}
