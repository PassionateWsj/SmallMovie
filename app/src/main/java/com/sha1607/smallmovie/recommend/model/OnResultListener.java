package com.sha1607.smallmovie.recommend.model;

import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 14:39
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public interface OnResultListener {
    void onVideoSuccess(RecommandVideoBean bean);
    void onCommentSuccess(RecommandVideoCommentBean bean);
}
