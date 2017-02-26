package com.sha1607.smallmovie.webdetail.presenter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 -1:38
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.bean.SourceBean;
import com.sha1607.smallmovie.webdetail.model.OnSourceSuccessListener;
import com.sha1607.smallmovie.webdetail.model.SourceModel;
import com.sha1607.smallmovie.webdetail.model.SourceModelImpl;
import com.sha1607.smallmovie.webdetail.view.SourceView;

public class SourcePresenter {
    private SourceModel mSourceModel;
    private SourceView mSourceView;
    public SourcePresenter(SourceView sourceView){
        this.mSourceView=sourceView;
        mSourceModel=new SourceModelImpl();
    }
    public void loadData(String type,int num){
        mSourceModel.loadData(type, num, new OnSourceSuccessListener() {
            @Override
            public void SourceSuccess(SourceBean datas) {
               // Log.e("nnnnn",datas.getContentList().size()+"");
                mSourceView.loadData(datas);
            }
        });
    }
}
