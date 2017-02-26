package com.sha1607.smallmovie.category.model;

import com.sha1607.smallmovie.bean.CategoryListBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:42
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */
public interface OnCategoryDataResultListener {
    void onCategoryDataResultFailure();

    void onCategoryDataResultSuccess(CategoryListBean data);

}
