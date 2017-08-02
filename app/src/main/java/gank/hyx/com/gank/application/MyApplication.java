package gank.hyx.com.gank.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by asus on 2017/8/3.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //如第一次进入没有sp在应用里 就初始化列表项
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);
        boolean hasInit = mSharedPreferences.getBoolean("hasInit", false);
        if (!hasInit) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(Constant.ListContent_sp1, true);
            editor.putBoolean(Constant.ListContent_sp2, true);
            editor.putBoolean(Constant.ListContent_sp3, true);
            editor.putBoolean(Constant.ListContent_sp4, true);
            editor.putBoolean(Constant.ListContent_sp5, true);
            editor.putBoolean(Constant.ListContent_sp6, false);
            editor.putBoolean(Constant.ListContent_sp7, false);
            editor.putBoolean(Constant.ListContent_sp8, false);
            editor.putBoolean("hasInit", true);
            editor.commit();
        }
    }
}
