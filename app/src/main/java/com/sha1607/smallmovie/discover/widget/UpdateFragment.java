package com.sha1607.smallmovie.discover.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.bean.DiscoverListBean;
import com.sha1607.smallmovie.discover.adatper.UpdateAdapter;
import com.sha1607.smallmovie.discover.presenter.DiscoverPresenter;
import com.sha1607.smallmovie.discover.view.DiscoverView;
import com.sha1607.smallmovie.utils.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/29 9:44
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class UpdateFragment extends Fragment implements DiscoverView {

    @BindView(R.id.container_recycler)
    RecyclerView mContainerRecycler;
    private DiscoverPresenter mPresenter;
    private List<DiscoverListBean.RecommendsListBean> mDatas = new ArrayList<>();
    private UpdateAdapter mAdapter;
    private int page = 1;
    private String mCanLoadMore;
    //    private List<HashMap<Integer, Boolean>> mMaps = new ArrayList<HashMap<Integer, Boolean>>();
    private HashMap<Integer, Boolean> maps = new HashMap<>();
    private HashMap<Integer, Boolean> newMaps = new HashMap<>();

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        mPresenter = new DiscoverPresenter(this);
        mPresenter.loadDiscoverData(page);
        ButterKnife.bind(this, view);

        mListener = new OnResultListener() {
            @Override
            public void getMap(HashMap<Integer, Boolean> map) {
                newMaps = map;
//                for (Map.Entry<Integer, Boolean> integerBooleanEntry : newMaps.entrySet()) {
//                    Integer key = integerBooleanEntry.getKey();
//                    Boolean value = integerBooleanEntry.getValue();
//                    int i = key.intValue();
//                    boolean b = value.booleanValue();
//                    Log.e(i + "" + ":::", b + "");
//                }
//                Log.e("-------", "------");
            }
        };

        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        mContainerRecycler.setLayoutManager(layout);
        mAdapter = new UpdateAdapter(getActivity(), mListener);
        mContainerRecycler.setAdapter(mAdapter);
        mContainerRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener(layout) {
            @Override
            public void onLoadMore(int currentPage) {
                if (mCanLoadMore.equals("1")) {
                    mPresenter.loadDiscoverData(++page);
                }

            }
        });
        return view;
    }

    @Override
    public void loadDiscoverData(DiscoverListBean bean) {
//        String title = bean.getRecommendsList().get(0).getFilmResources().getTitle();
//        Log.e("---",title);
        for (int i = 0; i < bean.getRecommendsList().size(); i++) {
            mDatas.add(bean.getRecommendsList().get(i));
        }

        //关键
        maps.clear();
        for (int i = (page - 1) * 10; i < mDatas.size(); i++) {
            maps.put(i, false);
        }
//        for (Map.Entry<Integer, Boolean> integerBooleanEntry : maps.entrySet()) {
//            Integer key = integerBooleanEntry.getKey();
//            Boolean value = integerBooleanEntry.getValue();
//            int i1 = key.intValue();
//            boolean b = value.booleanValue();
//            Log.e(i1 + "" + ":::", b + "");
//        }
//        Log.e("-------", "------");

        newMaps.putAll(maps);

        mAdapter.setData(mDatas, newMaps);

        mCanLoadMore = bean.getCanLoadMore();
    }

    private OnResultListener mListener;

    public OnResultListener getListener() {
        return mListener;
    }

    public void setOnResultListener(OnResultListener listener) {
        this.mListener = listener;
    }

    public interface OnResultListener {
        public void getMap(HashMap<Integer, Boolean> map);
    }
}
