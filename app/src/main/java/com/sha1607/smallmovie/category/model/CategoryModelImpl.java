package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.apis.RecommendApis;
import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.utils.RetrofitUtils;

import rx.Subscriber;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:40
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class CategoryModelImpl implements CategoryModel {

    @Override
    public void loadData(final OnCategoryDataResultListener onCategoryDataResultListener) {
        RetrofitUtils.create(RecommendApis.HOST, RecommendApis.class)
                .getCategotyList()
                .compose(RetrofitUtils.<CategoryListBean>rxSchedulerHelper())
                .subscribe(new Subscriber<CategoryListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onCategoryDataResultListener.onCategoryDataResultFailure();
                    }

                    @Override
                    public void onNext(CategoryListBean categoryListBean) {
                        onCategoryDataResultListener.onCategoryDataResultSuccess(categoryListBean);
                    }
                });
    }
}
