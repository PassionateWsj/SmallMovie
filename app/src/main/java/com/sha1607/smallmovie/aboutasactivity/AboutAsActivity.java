package com.sha1607.smallmovie.aboutasactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;

public class AboutAsActivity extends AppCompatActivity {

    private LinearLayout mLl_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setBackButton();
        setTableData();
       
    }

    private void setTableData() {
        setVersion();
        LinearLayout ll_user= (LinearLayout) mLl_table.getChildAt(1);
        TextView tv= (TextView) ll_user.findViewById(R.id.tv_desc);
        tv.setText("用户协议");
    }

    private void setVersion() {
        mLl_table = (LinearLayout) findViewById(R.id.ll_table);
        mLl_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutAsActivity.this, "无需更新,已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBackButton() {
        ImageView iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
