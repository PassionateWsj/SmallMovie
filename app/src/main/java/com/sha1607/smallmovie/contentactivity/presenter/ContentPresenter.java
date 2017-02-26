package com.sha1607.smallmovie.contentactivity.presenter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 5:01
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.contentactivity.model.ContentModel;
import com.sha1607.smallmovie.contentactivity.model.ContentModelImpl;
import com.sha1607.smallmovie.contentactivity.model.OnResultListener;
import com.sha1607.smallmovie.contentactivity.view.ContentView;

public class ContentPresenter {
    private ContentView mContentView;
    private ContentModel mContentModel;
    public ContentPresenter(ContentView contentView){
        this.mContentView=contentView;
        this.mContentModel=new ContentModelImpl();
    }
    public  void loadData(String type){
        mContentModel.loadData(type, new OnResultListener() {
            @Override
            public void onFilmSuccess(ContentFilmBean datas) {
                mContentView.loadData(datas);
            }
        });
    }



}
