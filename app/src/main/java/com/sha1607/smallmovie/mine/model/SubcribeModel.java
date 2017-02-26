package com.sha1607.smallmovie.mine.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -2:48
 * name:
 * desc:
 * step:
 **********************************
 */

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.db.Order;

public interface SubcribeModel {
    void lodaData(Dao<Order ,Long> orderDao, OnSuccessDBListener listener);
}
