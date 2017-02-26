package com.sha1607.smallmovie.recommend.view;

import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 14:52
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public interface VideoView {
    void loadVideoData(RecommandVideoBean bean);
    void loadVideoCommentData(RecommandVideoCommentBean bean);
}
