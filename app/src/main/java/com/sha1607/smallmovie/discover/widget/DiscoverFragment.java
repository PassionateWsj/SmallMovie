package com.sha1607.smallmovie.discover.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.discover.adatper.DiscoverAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 上午09:54
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class DiscoverFragment extends Fragment {

    @BindView(R.id.iv_discover_write)
    ImageView mIvDiscoverWrite;
    private List<Fragment> mFragments;
    private String[] titles = new String[2];
    private UpdateFragment mUpdateFragment;
    private FollowFragment mFollowFragment;
    @BindView(R.id.tl_container)
    TabLayout mTlContainer;
    @BindView(R.id.vp_container)
    ViewPager mVpContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discoverfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        titles[0] = "最新";
        titles[1] = "关注";
        mFragments = new ArrayList<Fragment>();
        mFollowFragment = new FollowFragment();
        mUpdateFragment = new UpdateFragment();

    }


    private void initData() {
        mFragments.add(mUpdateFragment);
        mFragments.add(mFollowFragment);

        mVpContainer.setAdapter(new DiscoverAdapter(getFragmentManager(), mFragments));
        mTlContainer.setupWithViewPager(mVpContainer);
        for (int i = 0; i < mTlContainer.getTabCount(); i++) {
            TabLayout.Tab tab = mTlContainer.getTabAt(i);
            tab.setText(titles[i]);
        }
    }

    @OnClick(R.id.iv_discover_write)
    public void onClick() {
        Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
    }
}
