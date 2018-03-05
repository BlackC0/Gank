package gank.hyx.com.gank.ui.main.my;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2017/7/25.
 */

public class MyPresenter implements MyContract.Presenter {

    private final MyContract.View mView;
    private final Activity mActivity;
    private SharedPreferences collectionSP;
    private SharedPreferences.Editor editor;


    public MyPresenter(MyContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
        this.collectionSP = this.mActivity.getSharedPreferences(Constant.TableName3, Context.MODE_PRIVATE);
        this.editor = collectionSP.edit();
        mView.setPresenter(this);
    }

    @Override
    public void start() {
       String imgUrl = collectionSP.getString(Constant.Collection_sp1,"");
       String nickName = collectionSP.getString(Constant.Collection_sp2,"未命名");
       mView.initView(imgUrl,nickName);
    }

    @Override
    public void prepareCleanCache() {
        mView.cleanCache();
    }

    @Override
    public void prepareMyCollection() {
        mView.gotoMyCollection();
    }
}
