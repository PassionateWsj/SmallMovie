package com.sha1607.smallmovie.db;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/28 下午 -1:24
 * name:
 * desc:
 * step:
 **********************************
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBHleper extends OrmLiteSqliteOpenHelper {
    private static  final String DB_NAME="hjjzuishuai";
    private static  final int DB_VERSION=1;




    public DBHleper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Order.class);
            TableUtils.createTable(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
    private static  DBHleper mInstance=null;
    public static synchronized DBHleper getInstance(Context context){
        if(mInstance==null){
            synchronized (DBHleper.class){
                if (mInstance==null){
                    mInstance=new DBHleper(context);
                }
            }
        }
        return  mInstance;
    }
    public Dao<Order,Long> getOrderDao() throws SQLException {
       return getDao(Order.class);
    }
    public Dao<User,Long> getUserDao() throws SQLException {
        return getDao(User.class);
    }
}
