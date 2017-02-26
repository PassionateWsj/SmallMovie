package com.sha1607.smallmovie.db;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/31 下午 7:37
 * name:
 * desc:
 * step:
 **********************************
 */

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(columnName = "_id", generatedId = true)
    private Long _id;
    @DatabaseField(columnName = "passWord", dataType = DataType.STRING)
    private String passWord;
    @DatabaseField(columnName = "number", dataType = DataType.LONG)
    private long number;
    @DatabaseField(columnName = "nickName", dataType = DataType.STRING)
    private String nickName;
    @DatabaseField(columnName = "sex", dataType = DataType.STRING)
    private String sex;
    @DatabaseField(columnName = "signal", dataType = DataType.STRING)
    private String signal;

    public User(Long number, String passWord, String nickName, String sex, String signal) {

        this.number = number;
        this.passWord = passWord;
        this.nickName = nickName;
        this.sex = sex;
        this.signal = signal;
    }

    public User() {

    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }
}

