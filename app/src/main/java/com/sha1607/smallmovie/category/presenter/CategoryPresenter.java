package com.sha1607.smallmovie.category.presenter;

import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.category.model.CategoryModelImpl;
import com.sha1607.smallmovie.category.model.OnCategoryDataResultListener;
import com.sha1607.smallmovie.category.view.CategoryView;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:03
 * e-mail: PassinateWsj@outlook.com
 * name: Category的Presenter
 * desc:
 * ****************************************************
 */

public class CategoryPresenter {
    private CategoryView mCategoryView;
    private CategoryModelImpl mCategoryModel;

    public CategoryPresenter(CategoryView categoryView) {
        this.mCategoryView = categoryView;
        mCategoryModel = new CategoryModelImpl();
    }

    /**
     * 向CategoryModel发出加载数据指令，并回调结果
     */
    public void loadData() {
        mCategoryModel.loadData(new OnCategoryDataResultListener() {
            @Override
            public void onCategoryDataResultFailure() {
                // 处理错误的逻辑
                mCategoryView.onResultFailure();
            }

            @Override
            public void onCategoryDataResultSuccess(CategoryListBean data) {
                mCategoryView.onResultSuccess(data);
            }
        });
    }
}
