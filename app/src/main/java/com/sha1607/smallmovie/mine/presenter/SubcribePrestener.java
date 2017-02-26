package com.sha1607.smallmovie.mine.presenter;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -2:55
 * name:
 * desc:
 * step:
 **********************************
 */

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.db.Order;
import com.sha1607.smallmovie.mine.model.OnSuccessDBListener;
import com.sha1607.smallmovie.mine.model.SubcribeModel;
import com.sha1607.smallmovie.mine.model.SubcribeModelImpl;
import com.sha1607.smallmovie.mine.view.SubcribeView;

import java.util.List;

public class SubcribePrestener {
    private SubcribeModel mModel;
    private SubcribeView mView;
    public SubcribePrestener(SubcribeView subcribeView){
        this.mView=subcribeView;
        mModel=new SubcribeModelImpl();
    }
    public  void loadData(Dao<Order,Long> dao ){
        mModel.lodaData(dao, new OnSuccessDBListener() {
            @Override
            public void lodaData(List<Order> datas) {
                mView.loadData(datas);
            }
        });
    }
}
