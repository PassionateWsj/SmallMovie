package com.sha1607.smallmovie.recommend.model;

import android.os.Environment;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.RecommendListBean;
import com.sha1607.smallmovie.utils.ACache;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import java.io.File;

import rx.Subscriber;

/**
 * Created by DiaoGe on 2016/10/26.
 */

public class RecommendModelImpl {


    public void getBeanData(final OnListener listener){
        String path= Environment.getExternalStorageDirectory().getPath() + "/hjjzuishuai";
        File file=new File(path);
        RecommendListBean bean = (RecommendListBean) ACache.get(file).getAsObject("hjj");
        if (bean!=null){
          listener.onSuccess(bean);
       }
        RetrofitUtils.create(RecommendApis.HOST,RecommendApis.class)
                .getRecommendList()
                .compose(RetrofitUtils.<RecommendListBean>rxSchedulerHelper())
                .subscribe(new Subscriber<RecommendListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecommendListBean recommendListBean) {
                          listener.onSuccess(recommendListBean);
                    }
                });
    }

}
