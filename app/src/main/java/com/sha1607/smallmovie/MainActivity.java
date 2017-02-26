package com.sha1607.smallmovie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.category.widget.CategoryFragment;
import com.sha1607.smallmovie.discover.widget.DiscoverFragment;
import com.sha1607.smallmovie.mine.MyFragment;
import com.sha1607.smallmovie.recommend.widget.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 上午10:28
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_main_tab)
    LinearLayout mLlMainTab;
    private LinearLayout[] mLl_main_tab_content;
    private TextView[] mTab_text;
    private ImageView[] mTab_img;
    private int[] img_unchosen = {
            R.drawable.ic_redeem_grey_24dp,
            R.drawable.ic_list_grey_24dp,
            R.drawable.ic_pageview_grey_24dp,
            R.drawable.ic_perm_identity_grey_24dp
    };
    private int[] img_chosen = {
            R.drawable.ic_redeem_chosen_24dp,
            R.drawable.ic_list_chosen_24dp,
            R.drawable.ic_pageview_chosen_24dp,
            R.drawable.ic_perm_identity_chosen_24dp
    };
    /**
     * 标题文本信息
     */
    private String[] mTabTitle;
    /**
     * Fragment的集合
     */
    private List<Fragment> mFragments;
    private RecommendFragment mRecommendFragment;
    private CategoryFragment mCategoryFragment;
    private DiscoverFragment mDiscoverFragment;
    private MyFragment mMyFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    /**
     * 当前的Fragment
     */
    private Fragment mCurrentFragment;
    private long mExitTime;

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 初始化view
     */
    @Override
    protected void initViews() {
        mTabTitle = getResources().getStringArray(R.array.MainTab);
        mFragments = new ArrayList<>();
        mCategoryFragment = new CategoryFragment();
        mRecommendFragment = new RecommendFragment();
        mDiscoverFragment = new DiscoverFragment();
        mMyFragment = new MyFragment();
        mFragments.add(mRecommendFragment);
        mFragments.add(mCategoryFragment);
        mFragments.add(mDiscoverFragment);
        mFragments.add(mMyFragment);
        mFragmentManager = getSupportFragmentManager();
        setChosenFragment(mFragments.get(0));
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initTabData();
    }

    /**
     * 初始化监听器
     */
    @Override
    protected void initListener() {

    }

    /**
     * 初始化主界面底部的tab数据
     */
    private void initTabData() {
        mLl_main_tab_content = new LinearLayout[mTabTitle.length];
        mTab_text = new TextView[mTabTitle.length];
        mTab_img = new ImageView[mTabTitle.length];
        for (int i = 0; i < mTabTitle.length; i++) {
            mLl_main_tab_content[i] = (LinearLayout) mLlMainTab.getChildAt(i);
            final int currentItem = i;
            mLl_main_tab_content[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTabData();
                    mTab_text[currentItem].setTextColor(getChosenColor());
                    mTab_img[currentItem].setImageResource(img_chosen[currentItem]);
                    mLl_main_tab_content[currentItem].setClickable(false);
                    setChosenFragment(mFragments.get(currentItem));
                }
            });
            mTab_text[i] = (TextView) mLl_main_tab_content[i].getChildAt(1);
            mTab_img[i] = (ImageView) mLl_main_tab_content[i].getChildAt(0);
            mTab_text[i].setText(mTabTitle[i]);
        }
        resetTabData();
        mTab_text[0].setTextColor(getChosenColor());
        mTab_img[0].setImageResource(img_chosen[0]);
        mLl_main_tab_content[0].setClickable(false);
    }

    /**
     * 将当前选择的fragment显示(show)出来，没选择的隐藏(hide)
     *
     * @param fragment
     */
    private void setChosenFragment(Fragment fragment) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment == null) {
            mFragmentTransaction.add(R.id.fragment_container, fragment).commit();
            mCurrentFragment = fragment;
        } else if (mCurrentFragment != fragment) {
            if (!fragment.isAdded()) {
                mFragmentTransaction.hide(mCurrentFragment).add(R.id.fragment_container, fragment).commit();
            } else {
                mFragmentTransaction.hide(mCurrentFragment).show(fragment).commit();
            }
            mCurrentFragment = fragment;
        }
    }

    /**
     * 将tab样式初始化
     */
    private void resetTabData() {
        for (int i = 0; i < mTabTitle.length; i++) {
            mTab_text[i].setTextColor(ContextCompat.getColor(this, R.color.colorTabText));
            mTab_img[i].setImageResource(img_unchosen[i]);
            mLl_main_tab_content[i].setClickable(true);
        }
    }

    /**
     * 设置tab选中的颜色
     *
     * @return
     */
    public int getChosenColor() {
        return ContextCompat.getColor(this, R.color.colorTabChosen);
    }

    /**
     * 两次返回退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出小电影", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
