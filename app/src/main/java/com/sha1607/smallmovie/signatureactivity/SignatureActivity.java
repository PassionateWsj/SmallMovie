package com.sha1607.smallmovie.signatureactivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.User;

import java.sql.SQLException;
import java.util.List;

public class SignatureActivity extends AppCompatActivity {
    private Dao<User,Long> mUserDao;
    private EditText mEt;
    private TextView mTvNum;
    private TextView mTvSave;
    private ImageView mIvBack;
    private int countLeft;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        initData();

        setListener();


    }

    private void initData() {
        initView();
        initEditTextData();
    }

    private void initView() {
        mEt = (EditText) findViewById(R.id.et_signature);
        mTvNum= (TextView) findViewById(R.id.tv_num);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvSave= (TextView) findViewById(R.id.tv_save);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            alert();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void setListener() {
        setEditTextListener();
        setBackListener();
        setSaveListener();
        final EditText inputServer = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Server").setView(inputServer)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                inputServer.getText().toString();
            }
        });
        builder.show();
    }

    private void setSaveListener() {
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void setBackListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });
    }

    private void setEditTextListener() {
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                countLeft=25-count;
                mTvNum.setTextColor(countLeft<5?getResources().getColor(R.color.colorRed):getResources().getColor(R.color.colorBlack));
                mTvNum.setText((countLeft)+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initEditTextData() {

        try {
            mUserDao= DBHleper.getInstance(this).getUserDao();
            List<User> users = mUserDao.queryForAll();
            mUser = users.get(0);
            mEt.setText(mUser.getSignal());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void alert(){
        new AlertDialog.Builder(this)
                .setTitle("尚未保存修改")
                .setMessage("保存后退出")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save();
                    }
                })
                .setNegativeButton("直接退出", null)
                .show();
    }
    void save(){
        try {
            mUser.setSignal(mEt.getText().toString());
            mUserDao.update(mUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
