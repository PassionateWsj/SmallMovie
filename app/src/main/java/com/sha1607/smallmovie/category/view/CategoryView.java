package com.sha1607.smallmovie.category.view;

import com.sha1607.smallmovie.bean.CategoryListBean;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/26 下午5:05
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public interface CategoryView {

    void onResultFailure();

    void onResultSuccess(CategoryListBean data);
}
