package com.sha1607.smallmovie.mine.model;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/30 下午 -2:49
 * name:
 * desc:
 * step:
 **********************************
 */

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.sha1607.smallmovie.db.Order;

import java.sql.SQLException;
import java.util.List;

public class SubcribeModelImpl implements SubcribeModel {

    @Override
    public void lodaData(Dao<Order, Long> orderDao, OnSuccessDBListener listener) {
        try {
            List<Order> orders = orderDao.queryForAll();
            Log.e("shujuku",orders.size()+"");
            listener.lodaData(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
