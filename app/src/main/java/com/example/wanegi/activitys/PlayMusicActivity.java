package com.example.wanegi.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.wanegi.R;
import com.example.wanegi.helps.RealmHelper;
import com.example.wanegi.models.MusicModel;
import com.example.wanegi.utils.ImagesUtil;
import com.example.wanegi.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";

    private ImageView mIvBg;
    private TextView mTvName, mTvAuthor;
    private PlayMusicView mPlayMusicView;
    private String mMusicId;
    private RealmHelper mRealmHelper;
    private MusicModel mMusicModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_play_music);

//        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initData();
        initView();
    }

    private void initData () {
        mMusicId = getIntent().getStringExtra(MUSIC_ID);
        mRealmHelper = new RealmHelper();
        mMusicModel = mRealmHelper.getMusic(mMusicId);
    }

    private void initView () {
        mIvBg = fd(R.id.iv_bg);
        mTvName = fd(R.id.tv_name);
        mTvAuthor = fd(R.id.tv_author);

//        String str = ImagesUtil.getImagesUrl(10);
//
//        Glide.with(this)
//                .load(str) //"https://img1.mukewang.com/533e4d660001312002000200-100-100.jpg"
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
//                .into(mIvBg);
//        mTvName.setText("歌名");
//        mTvAuthor.setText("歌手");
        Glide.with(this)
                .load(mMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mIvBg);
        mTvName.setText(mMusicModel.getName());
        mTvAuthor.setText(mMusicModel.getAuthor());

        mPlayMusicView = fd(R.id.play_music_view);
//        mPlayMusicView.setMusicIcon(str);
//        mPlayMusicView.playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
//      http://ip.h5.ra01.sycdn.kuwo.cn/a20b767a1c83ea9f067992a1e38a042c/5dd3a6be/resource/n2/128/79/72/3758896318.mp3
//      https://img.tukuppt.com/newpreview_music/00/10/93/5d819c599372871530.mp3
//        mPlayMusicView.setMusicIcon(mMusicModel.getPoster());
        mPlayMusicView.setMusic(mMusicModel);
        mPlayMusicView.playMusic();

    }


    /**
     * 后退按钮点击事件
     */
    @SuppressLint("NewApi")
    public void onBackClick(View view) {
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayMusicView.destroy();
        mRealmHelper.close();
    }
}
