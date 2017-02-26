package com.sha1607.smallmovie.db;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/28 下午 -1:00
 * name:
 * desc:
 * step:
 **********************************
 */
@DatabaseTable(tableName = "order")
public class Order {
    @DatabaseField(columnName = "_id",generatedId = true)
    private Long _id;
    @DatabaseField(columnName = "mImgUrl",dataType = DataType.STRING,unique = true)
    private String mImgUrl;
    @DatabaseField(columnName = "linkedId",dataType = DataType.STRING)
    private String linkedId;
    @DatabaseField(columnName = "articlesNum",dataType =DataType.STRING)
    private String articlesNum;
    @DatabaseField(columnName = "subscribeNum",dataType = DataType.STRING)
    private String subscribeNum;
    @DatabaseField(columnName = "title",dataType = DataType.STRING)
    private String title;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @DatabaseField(columnName = "flag",dataType = DataType.BOOLEAN)
    private boolean flag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Order(){

    }

    public Order(String title , String imgUrl, String linkedId, String articlesNum, String subscribeNum,boolean flag) {
        this.title=title;
        mImgUrl = imgUrl;
        this.linkedId = linkedId;
        this.articlesNum = articlesNum;
        this.subscribeNum = subscribeNum;
        this.flag=flag;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
    }

    public String getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(String linkedId) {
        this.linkedId = linkedId;
    }

    public String getArticlesNum() {
        return articlesNum;
    }

    public void setArticlesNum(String articlesNum) {
        this.articlesNum = articlesNum;
    }

    public String getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(String subscribeNum) {
        this.subscribeNum = subscribeNum;
    }
}
