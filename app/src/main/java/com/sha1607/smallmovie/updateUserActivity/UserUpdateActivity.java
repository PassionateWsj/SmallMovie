package com.sha1607.smallmovie.updateUserActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.User;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserUpdateActivity extends AppCompatActivity {
    @BindView(R.id.ll_table)
    LinearLayout mLlTable;
    private Dao<User, Long> mUserDao;
    private String signature;
    private User mUser;
    private ImageView mIv_back;
    private TextView mTv_save;
    private LinearLayout mNickname;
    private LinearLayout mSex;
    private TextView mTv_nickname;
    private TextView mNickname_detail;
    private TextView mTv_sex;
    private TextView mSex_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        ButterKnife.bind(this);
        initViewData();
        initTableData();
        setListener();

    }

    private void initViewData() {
        mNickname = (LinearLayout) mLlTable.getChildAt(0);
        mSex = (LinearLayout) mLlTable.getChildAt(2);
        mTv_nickname = (TextView) mNickname.findViewById(R.id.tv_desc);
        mNickname_detail = (TextView) mNickname.findViewById(R.id.tv_data);
        mTv_sex = (TextView) mSex.findViewById(R.id.tv_desc);
        mSex_detail = (TextView) mSex.findViewById(R.id.tv_data);
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_save = (TextView) findViewById(R.id.tv_save);

    }

    private void setListener() {
        setBackListener();
        setSaveListener();
        setNicknameListener();
        setSexListener();
    }

    private void setSexListener() {
        mSex_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserUpdateActivity.this);
                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请选择性别");
                final String[] sex = {"男", "女", "保密"};
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
                builder.setSingleChoiceItems(sex, 2, null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mSex_detail.setText(sex[which]);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
    }

    private void setNicknameListener() {
        mNickname_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputServer = new EditText(UserUpdateActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserUpdateActivity.this);
                builder.setTitle("设置昵称").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        mNickname_detail.setText(inputServer.getText().toString());
                    }
                });
                builder.show();
            }
        });
    }

    private void setSaveListener() {
        mTv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void setBackListener() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });
    }

    private void initTableData() {
        mTv_nickname.setText("昵称");
        mNickname_detail.setText("无");
        mNickname_detail.setTextColor(getResources().getColor(R.color.colorUpdateText));
        mTv_sex.setText("性别");
        mSex_detail.setText("保密");
        try {
            mUserDao = DBHleper.getInstance(this).getUserDao();
            List<User> users = mUserDao.queryForAll();
            mUser = new User();
            mUser = users.get(0);
            mNickname_detail.setText(mUser.getNickName());
            mSex_detail.setText(mUser.getSex());
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
            mUser.setNickName(mNickname_detail.getText().toString());
            mUser.setSex(mSex_detail.getText().toString());
            mUserDao.update(mUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(UserUpdateActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
