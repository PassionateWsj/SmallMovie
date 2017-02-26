package com.sha1607.smallmovie.webdetail.presenter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/29 下午 8:07
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.bean.AuthorBean;
import com.sha1607.smallmovie.webdetail.model.AuthorModel;
import com.sha1607.smallmovie.webdetail.model.AuthorModelImpl;
import com.sha1607.smallmovie.webdetail.model.OnAuthorSuccessListener;
import com.sha1607.smallmovie.webdetail.view.AuthorView;

public class AuthorPresenter {
    private AuthorModel mAuthorModel;
    private AuthorView mAuthorView;
    public AuthorPresenter(AuthorView authorView){
        this.mAuthorView=authorView;
        mAuthorModel=new AuthorModelImpl();
    }
    public  void lodaData(String type,int id){
        mAuthorModel.loadData(type, id, new OnAuthorSuccessListener() {
            @Override
            public void loadData(AuthorBean datas) {
                mAuthorView.loadData(datas);
            }
        });
    }
}
