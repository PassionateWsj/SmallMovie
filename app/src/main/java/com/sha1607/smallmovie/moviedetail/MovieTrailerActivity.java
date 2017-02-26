package com.sha1607.smallmovie.moviedetail;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sha1607.smallmovie.R;
import com.sha1607.smallmovie.base.BaseActivity;
import com.sha1607.smallmovie.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/31 下午1:10
 * e-mail: PassinateWsj@outlook.com
 * name: 播放电影花絮的Activity
 * desc:
 * ****************************************************
 */

public class MovieTrailerActivity extends BaseActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {
    @BindView(R.id.probar)
    ProgressBar mProbar;
    @BindView(R.id.download_rate)
    TextView mDownloadRate;
    @BindView(R.id.load_rate)
    TextView mLoadRate;
    @BindView(R.id.fl_controller)
    FrameLayout mFlController;
    @BindView(R.id.vv_movie_trailer)
    VideoView mVvMovieTrailer;
    private String mResourceUrl;
    private Uri uri;
    boolean isPortrait = true;
    private long mPosition = 0;

    @Override
    protected void loadXml() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_movie_trailer);
        ButterKnife.bind(this);
        Vitamio.isInitialized(this);

    }

    @Override
    protected void initData() {
        mResourceUrl = getIntent().getStringExtra("resource_url");
        if (mResourceUrl == "") {
            // Tell the user to provide a media file URL/mResourceUrl.
            return;
        } else {
      /*
       * Alternatively,for streaming media you can use
       * mVvMovieTrailer.setVideoURI(Uri.parse(URLstring));
       */
            uri = Uri.parse(mResourceUrl);

            mVvMovieTrailer.setVideoURI(uri);
            MediaController mc = new MediaController(this, true, mFlController);
            mVvMovieTrailer.setMediaController(mc);
            mc.setVisibility(View.GONE);
            //  mVvMovieTrailer.setMediaController(new MediaController(this));
            mVvMovieTrailer.requestFocus();
            mVvMovieTrailer.setOnInfoListener(this);
            mVvMovieTrailer.setOnBufferingUpdateListener(this);
            mVvMovieTrailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // optional need Vitamio 4.0
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
            });
        }
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVvMovieTrailer.isPlaying()) {
                    mVvMovieTrailer.pause();
                    mProbar.setVisibility(View.VISIBLE);
                    mDownloadRate.setText("");
                    mLoadRate.setText("");
                    mDownloadRate.setVisibility(View.VISIBLE);
                    mLoadRate.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVvMovieTrailer.start();
                mProbar.setVisibility(View.GONE);
                mDownloadRate.setVisibility(View.GONE);
                mLoadRate.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                mDownloadRate.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isPortrait) {
                LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        DisplayUtils.dp2px(mActivity, 200)
                );
                mFlController.setLayoutParams(fl_lp);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isPortrait = true;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mLoadRate.setText(percent + "%");
    }

    @Override
    protected void onPause() {
        mPosition = mVvMovieTrailer.getCurrentPosition();
        mVvMovieTrailer.stopPlayback();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mPosition > 0) {
            mVvMovieTrailer.seekTo(mPosition);
            mPosition = 0;
        }
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
        mVvMovieTrailer.start();
    }

}
