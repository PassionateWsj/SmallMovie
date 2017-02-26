package com.sha1607.smallmovie.mine.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -2:46
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.db.Order;

import java.util.List;

public interface OnSuccessDBListener {
    void lodaData(List<Order> datas);
}
