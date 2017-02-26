package com.sha1607.smallmovie.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.db.DBHleper;
import com.sha1607.smallmovie.db.User;
import com.sha1607.smallmovie.optionactivity.OptionActivity;
import com.sha1607.smallmovie.updateUserActivity.UserUpdateActivity;
import com.sha1607.smallmovie.utils.MyImageLoader;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sha1607.smallmovie.R.id.ll_down;


public class MyFragment extends Fragment {
    public boolean login=false;
    @BindView(R.id.ll_up)
    LinearLayout mLlUp;
    @BindView(R.id.ll_mid)
    LinearLayout mLlMid;
    @BindView(ll_down)
    LinearLayout mLlDown;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    private ImageView mIv_head;


    private Dao<User,Long> mUserDao;
    private LinearLayout mLl_info;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initDataBase() {
        try {
            mUserDao=  DBHleper.getInstance(getActivity()).getUserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            mUserDao.createIfNotExists(new User(18862006218L,"hjjzz","胡佳俊","男","生日、俄 与 寂寞 一起 嗨皮"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initDataBase();
        ButterKnife.bind(this, view);
        initTableData();
        mIv_head = (ImageView) mLlUp.getChildAt(0).findViewById(R.id.iv_head);
        mIv_head.setImageResource(R.drawable.test_bg);
        mLl_info = (LinearLayout) mLlUp.getChildAt(0);
        mLl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login){
                    startActivity(new Intent(getActivity(), UserUpdateActivity.class));
                }else{
                    startActivity(new Intent(getActivity(),RegisterActivity.class));
                }
            }
        });
        TextView username= (TextView) mLl_info.findViewById(R.id.tv_register);
        TextView signal= (TextView) mLl_info.findViewById(R.id.tv_login);
        try {
            List<User> users = mUserDao.queryForAll();
            username.setText(users.get(0).getNickName());
            signal.setText(users.get(0).getSignal());
            if (users.get(0).getNickName().equals("胡佳俊")){
                MyImageLoader.with(getActivity(),R.drawable.pqy,mIv_head);
                login=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LinearLayout ll_collection= (LinearLayout) mLlMid.getChildAt(3);
        ll_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SubcribeActivity.class));
            }
        });

        LinearLayout ll_down=(LinearLayout)mLlDown.getChildAt(1);
        ll_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OptionActivity.class));
            }
        });
        LinearLayout ll= (LinearLayout) mLlUp.getChildAt(0);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });

        //TODO 收藏界面跳转
        return view;

    }

    private void initTableData() {
        String[] tableText = new String[5];
        TextView[] tableTextView=new TextView[5];
        tableText[0]="  我的主页";
        tableText[1]="  我的收藏";
        tableText[2]="  我的订阅";
        tableText[3]="  聊天室";
        tableText[4]="  设置";

        tableTextView[0]=(TextView) mLlMid.getChildAt(1).findViewById(R.id.tv_desc);
        tableTextView[1]=(TextView) mLlMid.getChildAt(2).findViewById(R.id.tv_desc);
        tableTextView[2]=(TextView) mLlMid.getChildAt(3).findViewById(R.id.tv_desc);
        tableTextView[3]=(TextView) mLlMid.getChildAt(4).findViewById(R.id.tv_desc);
        tableTextView[4]=(TextView) mLlDown.getChildAt(1).findViewById(R.id.tv_desc);
        for (int i=0;i<5;i++){
            tableTextView[i].setText(tableText[i]);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
