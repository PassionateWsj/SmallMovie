package com.sha1607.smallmovie.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    @BindView(R.id.tv_regist)
    TextView mTvRegist;
    private RegisterPage mRegisterPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        ImageView iv= (ImageView) findViewById(R.id.iv_xx);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRegisterPage = new RegisterPage();
    }

    @OnClick({R.id.tv_forget_password, R.id.tv_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                //打开注册页面
                mRegisterPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                        }
                    }
                });
                mRegisterPage.show(this);
                break;
            case R.id.tv_regist:
                //打开注册页面
                mRegisterPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                        }
                    }
                });
                mRegisterPage.show(this);
                finish();
                break;
        }
    }
}
