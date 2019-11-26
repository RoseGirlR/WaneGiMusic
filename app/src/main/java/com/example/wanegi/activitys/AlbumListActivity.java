package com.example.wanegi.activitys;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.wanegi.R;
import com.example.wanegi.adapters.MusicListAdapter;
import com.example.wanegi.helps.RealmHelper;
import com.example.wanegi.models.AlbumModel;
import com.example.wanegi.models.MusicModel;
import com.example.wanegi.models.MusicSourceModel;
import com.example.wanegi.models.PlayListModel;
import com.example.wanegi.utils.ImagesUtil;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class AlbumListActivity extends BaseActivity {

    public static final String IS_ALBUM = "album";
    public static final String IS_PLAYLIST = "playList";
    private boolean isAlbum;
    private boolean isPlayList;

    public static final String ALBUM_ID = "albumId";
    public static final String PLAYLIST_ID = "playListId";
    public static final String MUSIC_ID = "musicId";

    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;

    private ImageView bg_icon, iv_icon;
    private TextView al_title, tv_info;

    private String mAlbumId;
    private String mPlayListId;
    private RealmHelper mRealmHelper;
    private AlbumModel mAlbumModel;
    private PlayListModel mPlayListModel;

//    private String mMusicId;
//    private MusicModel mMusicModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initData();
        initView();
    }

    private void initData () {
        isAlbum = getIntent().getBooleanExtra(IS_ALBUM, false);
        isPlayList = getIntent().getBooleanExtra(IS_PLAYLIST, false);

        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        mPlayListId = getIntent().getStringExtra(PLAYLIST_ID);
        mRealmHelper = new RealmHelper();
        mAlbumModel = mRealmHelper.getAlbum(mAlbumId);
        mPlayListModel = mRealmHelper.getPlayList(mPlayListId);
//        mMusicId = getIntent().getStringExtra(MUSIC_ID);
//        mMusicModel = mRealmHelper.getMusic(mMusicId);
    }

    private void initView () {
        initNavBar(true, "歌单列表", false);

        bg_icon = fd(R.id.bg_icon);
        iv_icon = fd(R.id.iv_icon);
        al_title = fd(R.id.al_title);
        tv_info = fd(R.id.tv_info);

//        String pic = ImagesUtil.getImagesUrl(10); // "https://img3.doubanio.com/view/photo/sqxs/public/p2131410670.webp"
//        Glide.with(this)
//                .load(pic)
//                .into(iv_icon);
//        Glide.with(this)
//                .load(pic)
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
//                .into(bg_icon);
        if(isAlbum==true && isPlayList==false){
            Glide.with(this)
                    .load(mAlbumModel.getPoster())
                    .into(iv_icon);
            Glide.with(this)
                    .load(mAlbumModel.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(bg_icon);
            al_title.setText(mAlbumModel.getTitle());
            tv_info.setText(mAlbumModel.getIntro());
            mAdapter = new MusicListAdapter(this, null, mAlbumModel.getList());
        }
        if(isAlbum==false && isPlayList==true){
            Glide.with(this)
                    .load(mPlayListModel.getPoster())
                    .into(iv_icon);
            Glide.with(this)
                    .load(mPlayListModel.getPoster())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                    .into(bg_icon);
            al_title.setText(mPlayListModel.getTitle());
            tv_info.setText(mPlayListModel.getIntro());
            mAdapter = new MusicListAdapter(this, null, mPlayListModel.getList());
        }

//        al_title.setText("生活千变万化 哪一种我都潇洒");
//        tv_info.setText("你常常希望喜欢的人可以成为故事的主角，但事实上，他只是坐在台下玩手机的观众。");

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRvList.setNestedScrollingEnabled(false);
//        mAdapter = new MusicListAdapter(this, null, mAlbumModel.getList());
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}
