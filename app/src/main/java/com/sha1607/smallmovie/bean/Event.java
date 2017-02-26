package com.sha1607.smallmovie.bean;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/11/07 下午 -2:52
 * name:
 * desc:
 * step:
 **********************************
 */

public class Event {
    private String mId;
    private boolean mBoolean;

    public Event(String id, boolean aBoolean) {
        mId = id;
        mBoolean = aBoolean;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public boolean isBoolean() {
        return mBoolean;
    }

    public void setBoolean(boolean aBoolean) {
        mBoolean = aBoolean;
    }
}
