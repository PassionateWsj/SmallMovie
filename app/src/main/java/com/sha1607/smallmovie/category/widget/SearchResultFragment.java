package com.sha1607.smallmovie.category.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.SearchResultBean;
import com.sha1607.smallmovie.category.adatper.SearchResultRecyclerViewAdapter;
import com.sha1607.smallmovie.category.presenter.SearchPresenter;
import com.sha1607.smallmovie.category.view.SearchFragmentView;
import com.sha1607.smallmovie.category.view.SearchResultView;
import com.sha1607.smallmovie.utils.EndlessRecyclerOnScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 上午10:29
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class SearchResultFragment extends Fragment implements SearchFragmentView {


    @BindView(R.id.recycler_view_search_result)
    RecyclerView mRecyclerViewSearchResult;
    private SearchPresenter mSearchPresenter;
    private int startIndex;
    private SearchResultRecyclerViewAdapter mAdapter;
    private String mType;
    private SearchResultView mSearchResultView;
    private LinearLayoutManager mLinearLayoutManager;
    private int mTotal;
    private String mSearchContent;

    public static SearchResultFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("type");
        mSearchPresenter = new SearchPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result_container, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewSearchResult.setLayoutManager(mLinearLayoutManager);
        mAdapter = new SearchResultRecyclerViewAdapter(getActivity());
        mRecyclerViewSearchResult.setAdapter(mAdapter);
        mRecyclerViewSearchResult.addOnScrollListener(
                new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (mTotal > (startIndex += 20)) {
                    mSearchPresenter.loadData(mType, startIndex, mSearchContent);
                }
            }
        });
    }

    public void loadData(String searchContent, SearchResultView searchResultView) {
        this.mSearchContent = searchContent;
        mSearchPresenter.loadData(mType, startIndex, searchContent);
        this.mSearchResultView = searchResultView;
    }

    @Override
    public void onSearchResultFailure() {

    }

    @Override
    public void onSearchResultSuccess(SearchResultBean data) {
        mAdapter.setData(data.getHits());
        mTotal = data.getTotal();
        mSearchResultView.onSearchResultSuccess(mType, mTotal);
    }

    public void cleanData() {
        startIndex = 0;
        mAdapter.cleanData();
    }
}
