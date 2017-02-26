package com.sha1607.smallmovie.recommend.presenter;

import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;
import com.sha1607.smallmovie.recommend.model.OnResultListener;
import com.sha1607.smallmovie.recommend.model.VideoModel;
import com.sha1607.smallmovie.recommend.model.VideoModelImpl;
import com.sha1607.smallmovie.recommend.view.VideoView;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/27 14:50
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class VideoPresenter {
    private VideoView mVideoView;
    private VideoModel mVideoModel;

    public VideoPresenter(VideoView videoView){
        this.mVideoView = videoView;
        this.mVideoModel = new VideoModelImpl();
    }
    public void loadVideoData(String id){
        mVideoModel.loadData(id, new OnResultListener() {
            @Override
            public void onVideoSuccess(RecommandVideoBean bean) {
                mVideoView.loadVideoData(bean);
            }

            @Override
            public void onCommentSuccess(RecommandVideoCommentBean bean) {
                mVideoView.loadVideoCommentData(bean);
            }
        });
    }
}
