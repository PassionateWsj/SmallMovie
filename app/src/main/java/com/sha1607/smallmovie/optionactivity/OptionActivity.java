package com.sha1607.smallmovie.optionactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.utils.ACache;

import java.io.File;

public class OptionActivity extends AppCompatActivity {


    private TextView mTvAbout;
    private TextView mTvShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        ImageView iv_back= (ImageView) findViewById(R.id.iv_back);
        LinearLayout ll= (LinearLayout) findViewById(R.id.ll_cache);
        LinearLayout ll_cache= (LinearLayout) ll.getChildAt(0);
        TextView tv_cache= (TextView) ll.findViewById(R.id.tv_data);
        ll_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACache.get(OptionActivity.this,"hjjzuishuai").clear();
                File file=new File(OptionActivity.this.getCacheDir(),"hjjzuishuai");
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initTableData();
    }

    private void initTableData() {
        LinearLayout ll= (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout mLlAbout= (LinearLayout) ll.getChildAt(0);
        LinearLayout mLlShare= (LinearLayout) ll.getChildAt(1);
        //关于我们,分享APP跳转
        mTvAbout = (TextView) mLlAbout.findViewById(R.id.tv_desc);
        mTvShare = (TextView) mLlShare.findViewById(R.id.tv_desc);
        mTvAbout.setText("  关于我们");
        mTvShare.setText("  分享APP");
    }
}
