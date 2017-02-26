package com.sha1607.smallmovie.category.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.category.adatper.SearchTypeViewPagerAdapter;
import com.sha1607.smallmovie.category.view.SearchResultView;
import com.sha1607.smallmovie.category.widget.SearchResultFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/27 下午10:01
 * e-mail: PassinateWsj@outlook.com
 * name: 搜索界面Activity
 * desc:
 * ****************************************************
 */

public class SearchActivity extends BaseActivity implements SearchResultView {

    @BindView(R.id.iv_search_back)
    ImageView mIvSearchBack;
    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.tv_search_btn)
    TextView mTvSearchBtn;
    @BindView(R.id.tv_search_type_article)
    TextView mTvSearchTypeArticle;
    @BindView(R.id.tv_search_type_movie)
    TextView mTvSearchTypeMovie;
    @BindView(R.id.vp_search_type)
    ViewPager mVpSearchType;
    @BindView(R.id.ib_search_content_delete)
    ImageButton mIbSearchContentDelete;
    private List<Fragment> fragmentList;
    private String searchContent;
    private SearchResultFragment mMovieFragment;
    private SearchResultFragment mArticleFragment;
    private static final String TYPE_ARTICLE = "article";
    private static final String TYPE_MOVIE = "movie";
    private String mLastSearchContent;

    @Override
    protected void loadXml() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        mIbSearchContentDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearchContent.setText("");
            }
        });
        mEtSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    mIbSearchContentDelete.setVisibility(View.INVISIBLE);
                } else {
                    mIbSearchContentDelete.setVisibility(View.VISIBLE);
                }
                searchContent = s.toString();
            }
        });
        mEtSearchContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容

                } else {
                    // 此处为失去焦点时的处理内容
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtSearchContent.getWindowToken(), 0);
                }
            }
        });
        mEtSearchContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    search();
                    mEtSearchContent.clearFocus();
                }
                return false;
            }
        });
        mVpSearchType.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    chooseAtricleTextColor();
                } else {
                    chooseMovieTextColor();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void chooseMovieTextColor() {
        mTvSearchTypeArticle.setTextColor(getResources().getColor(R.color.colorBlack));
        mTvSearchTypeMovie.setTextColor(getResources().getColor(R.color.colorTabChosen));
    }

    private void chooseAtricleTextColor() {
        mTvSearchTypeArticle.setTextColor(getResources().getColor(R.color.colorTabChosen));
        mTvSearchTypeMovie.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        mArticleFragment = SearchResultFragment.newInstance(TYPE_ARTICLE);
        mMovieFragment = SearchResultFragment.newInstance(TYPE_MOVIE);
        fragmentList.add(mArticleFragment);
        fragmentList.add(mMovieFragment);
        mVpSearchType.setAdapter(new SearchTypeViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        chooseAtricleTextColor();
    }

    @OnClick({R.id.iv_search_back, R.id.tv_search_btn, R.id.tv_search_type_article, R.id.tv_search_type_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.tv_search_btn:
                search();
                break;
            case R.id.tv_search_type_article:
                chooseAtricleTextColor();
                mVpSearchType.setCurrentItem(0);
                break;
            case R.id.tv_search_type_movie:
                chooseMovieTextColor();
                mVpSearchType.setCurrentItem(1);
                break;
        }
        mEtSearchContent.clearFocus();
    }

    /**
     * 监听编辑文本内容并搜索
     */
    private void search() {
        if (mLastSearchContent == null) {
            mArticleFragment.loadData(searchContent, this);
            mMovieFragment.loadData(searchContent, this);
        } else if (!mLastSearchContent.equals(searchContent) && mLastSearchContent != null) {
            mArticleFragment.cleanData();
            mMovieFragment.cleanData();
            mArticleFragment.loadData(searchContent, this);
            mMovieFragment.loadData(searchContent, this);
        }
        mLastSearchContent = searchContent;
    }

    @Override
    public void onSearchResultSuccess(String type, int total) {
        if (type.equals(TYPE_ARTICLE)) {
            mTvSearchTypeArticle.setText("文章(" + total + ")");
        }
        if (type.equals(TYPE_MOVIE)) {
            mTvSearchTypeMovie.setText("电影(" + total + ")");
        }
    }

}
