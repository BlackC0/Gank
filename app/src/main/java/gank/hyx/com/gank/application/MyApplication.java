package gank.hyx.com.gank.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.liulishuo.filedownloader.FileDownloader;
import com.squareup.leakcanary.LeakCanary;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by asus on 2017/8/3.
 */

public class MyApplication extends Application {

    public static MyApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        initListContentOption();
        FileDownloader.init(mainApplication);

    }

    private void initListContentOption() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        //如第一次进入没有sp在应用里 就初始化列表项
        SharedPreferences listContentSp = getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);
        SharedPreferences collectionSp = getSharedPreferences(Constant.TableName3, Context.MODE_PRIVATE);
        boolean hasInit = listContentSp.getBoolean("hasInit", false);
        if (!hasInit) {
            SharedPreferences.Editor editor = listContentSp.edit();
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

            collectionSp.edit().putString(Constant.Collection_sp1, "");
            collectionSp.edit().putString(Constant.Collection_sp2, "未命名");
            editor.commit();

        }


    }
}
