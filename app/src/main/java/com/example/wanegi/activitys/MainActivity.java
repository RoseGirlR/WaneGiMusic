package com.example.wanegi.activitys;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wanegi.R;
import com.example.wanegi.adapters.MusicBlockAdapter;
import com.example.wanegi.adapters.MusicGridAdapter;
import com.example.wanegi.adapters.MusicListAdapter;
import com.example.wanegi.adapters.MusicRecordAdapter;
import com.example.wanegi.helps.RealmHelper;
import com.example.wanegi.models.MusicSourceModel;
import com.example.wanegi.utils.ImagesUtil;
import com.example.wanegi.views.GridSpaceItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

//    private Map<String,Object> block = new HashMap<>(2);
//    private boolean isLiving;

    private String[] blockTitle = {"每日推荐","歌单","排行榜","电台","直播"};
    private int[] blockImage = {R.mipmap.love,
        R.mipmap.album,
        R.mipmap.rank,
        R.mipmap.radio,
        R.mipmap.onlive};

    private RecyclerView mRvGrid, mRvBlock, mRvAlbum, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private MusicBlockAdapter mBlockAdapter;
    private MusicRecordAdapter mRecordAdapter;

    private Banner mBanner;
    private ArrayList<String> images;
    private ArrayList<String> imageTitle;

    private RealmHelper mRealmHelper;
    private MusicSourceModel mMusicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBanner = fd(R.id.banner);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.banner);
//        ImageView imageView = findViewById(R.id.banner);
//        imageView.setImageBitmap(BitMapUtils.roundBitmapByShader(bitmap,
//                (int)getResources().getDimension(R.dimen.roundBitMapBorder),
//                (int)getResources().getDimension(R.dimen.roundBitMapBorder),
//                (int)getResources().getDimension(R.dimen.roundBitMapBorder)));

        initData();
//        Banner banner = (Banner) findViewById(R.id.banner);
//        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        banner.setImages(images);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();

        initView();

    }

    private void initData() {
        images = ImagesUtil.getBitMapUrl();
        imageTitle = ImagesUtil.getBitMapTitleUrl();
        mRealmHelper = new RealmHelper();
        mMusicSourceModel = mRealmHelper.getMusicSource();
        //设置图片资源:url或本地资源
//        images = new ArrayList<>();
//        images.add("https://img3.mukewang.com/szimg/5959a60f0001cfd305400300-360-202.jpg");
//        images.add("https://img2.mukewang.com/szimg/59118b92000147bf05400300-360-202.jpg");
//        images.add("https://img1.mukewang.com/szimg/59313618000198ed05400300-360-202.jpg");
//        images.add("https://img1.mukewang.com/szimg/59eeb022000162bc05400300-360-202.jpg");
//        images.add("https://img4.mukewang.com/szimg/59eeb17200013f8605400300-360-202.jpg");
//        images.add("https://img1.mukewang.com/szimg/5d43953c09c0247612000676-360-202.png");
//        images.add("https://climg.mukewang.com/5c8097e80001960406000338-280-160.jpg");
//        //设置图片标题:自动对应
//        imageTitle = new ArrayList<>();
//        imageTitle.add("算法与数据结构--综合提升篇");
//        imageTitle.add("手把手开发一个完整即时通讯App");
//        imageTitle.add("Android首选开发语言Kotlin入门与进阶");
//        imageTitle.add("真实数据对接 从0开发前后端分离的企业级上线项目");
//        imageTitle.add("Android通用框架设计与完整电商App开发");
//        imageTitle.add("混合开发入门 实战京东移动端APP");
//        imageTitle.add("Android 网络操作与流行框架");

//        block.put("blockTitle",blockTitle);
//        block.put("blockImage",blockImage);
    }

    private void initView () {
        initNavBar(false, "W音乐", true);

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(imageTitle);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);
        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
//        mBanner.setDelayTime(1500);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImages(images)
            .setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(MainActivity.this, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                }
            })
            .start();

        // 四个版块
        mRvBlock = fd(R.id.rv_block);
        mRvBlock.setLayoutManager(new GridLayoutManager(this, blockTitle.length));
        mRvBlock.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvBlock));
        mRvBlock.setNestedScrollingEnabled(false); //取消滑动
//        List<AlbumModel> als = null;
//        int i  = 0;
//        for(AlbumModel al:mMusicSourceModel.getAlbum()){
//            als.set(i++, al);
//            if(i >= 5) break;
//        }
        mBlockAdapter = new MusicBlockAdapter(this, blockTitle, blockImage);
//        mBlockAdapter = new MusicBlockAdapter(this, block);
        mRvBlock.setAdapter(mBlockAdapter);

        // 推荐歌单
        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false); //取消滑动
        mGridAdapter = new MusicGridAdapter(this, mMusicSourceModel.getAlbum());
        mRvGrid.setAdapter(mGridAdapter);

        // 新增专辑列表（新碟）
        mRvAlbum = fd(R.id.rv_album);
        mRvAlbum.setLayoutManager(new GridLayoutManager(this, 3));
        mRvAlbum.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvAlbum));
        mRvAlbum.setNestedScrollingEnabled(false); //取消滑动
        mRecordAdapter = new MusicRecordAdapter(this, mMusicSourceModel.getPlayList());
        mRvAlbum.setAdapter(mRecordAdapter);

        /**
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdapter(this, mRvList, mMusicSourceModel.getHot());
        mRvList.setAdapter(mListAdapter);
    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load((String) path)
//                    .transform(new GlideRectRound(RankActivity.this))
                    .into(imageView);
        }

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//    }

    @Override
    protected void onDestroy() {
//        if(isLiving) return;
        super.onDestroy();
        mRealmHelper.close();
    }

}
