package com.example.wanegi.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanegi.R;
import com.example.wanegi.adapters.MusicListAdapter;
import com.example.wanegi.helps.RealmHelper;
import com.example.wanegi.models.MusicSourceModel;

public class RankUtils {

    private static RecyclerView mRvList;
    private static MusicListAdapter mAdapter;

    private static RealmHelper mRealmHelper;
    private static MusicSourceModel mMusicSourceModel;

    public static void rankSetView(Context context, View view, int list_id){
        mRealmHelper = new RealmHelper();
        mMusicSourceModel = mRealmHelper.getMusicSource();

        mRvList = view.findViewById(list_id);
        mRvList.setLayoutManager(new LinearLayoutManager(context));
        mRvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        mAdapter = new MusicListAdapter(context, null, mMusicSourceModel.getHot());
        mRvList.setAdapter(mAdapter);
    }

}
