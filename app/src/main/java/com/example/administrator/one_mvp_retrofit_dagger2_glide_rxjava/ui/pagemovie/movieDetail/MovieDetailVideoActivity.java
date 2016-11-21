package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MovieDetailVideoActivity extends BaseActivity {

    @Bind(R.id.custom_videoplayer_standard)
    JCVideoPlayerStandard jcVideoPlayerStandard;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_movie_detail_video;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String videoUrl = getIntent().getStringExtra(Const.MOVIE_VIDEO_URL) != null?getIntent().getStringExtra(Const.MOVIE_VIDEO_URL):"http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
        String videoTitle = getIntent().getStringExtra(Const.MOVIE_VIDEO_TITLE) != null?getIntent().getStringExtra(Const.MOVIE_VIDEO_TITLE):"测试视频";
        String videoCover = getIntent().getStringExtra(Const.MOVIE_VIDEO_INNER_COVER) != null?getIntent().getStringExtra(Const.MOVIE_VIDEO_INNER_COVER):"http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640";
        jcVideoPlayerStandard.setUp(videoUrl, JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN, videoTitle);
        Glide.with(this).load(videoCover).thumbnail(0.1f).dontAnimate().placeholder(R.drawable.default_indi_bg).into(jcVideoPlayerStandard.thumbImageView);
    }

    @OnClick({R.id.back,R.id.fullscreen})
    public void backFinish(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.fullscreen:
                //由于是全屏播放，所以不存在点击放大，重写点击事件，覆盖之前的逻辑。
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
