package com.example.wanegi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wanegi.R;
import com.example.wanegi.utils.RankUtils;

/**
 * Created by caobotao on 16/1/4.
 */
public class YearRankFragment extends Fragment {

    private Context mContext;

    public YearRankFragment(Context context){
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4, container, false);
        RankUtils.rankSetView(mContext, view, R.id.year_rank_list);
        return view;
    }
}
