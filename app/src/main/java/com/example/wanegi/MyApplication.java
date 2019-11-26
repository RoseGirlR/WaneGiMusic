package com.example.wanegi;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.wanegi.helps.RealmHelper;
import com.example.wanegi.utils.DataUtils;

import io.realm.Realm;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        Realm.init(this);

        RealmHelper.migration();
    }
}
