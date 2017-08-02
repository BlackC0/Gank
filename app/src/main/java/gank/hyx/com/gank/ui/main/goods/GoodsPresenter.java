package gank.hyx.com.gank.ui.main.goods;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseFragment;

/**
 * Created by Black.C on 2017/7/25.
 */

public class GoodsPresenter implements GoodsContract.Presenter {

    private final GoodsContract.View mView;
    private Context context;
    private SharedPreferences mSharedPreferences;
    private ArrayList<BaseFragment> fragmentLists = new ArrayList<>();
    private ArrayList<String> tabNames = new ArrayList<>();

    public GoodsPresenter(GoodsContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        this.mView.setmPresenter(this);
        this.mSharedPreferences = context.getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);
    }

    @Override
    public void start() {
        // TODO: 2017/8/1 获取数据库中有哪几个干货列表
        tabNames.clear();
        tabNames.add(Constant.ListContent_sp1);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp2,false)) tabNames.add(Constant.ListContent_sp2);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp3,false)) tabNames.add(Constant.ListContent_sp3);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp4,false)) tabNames.add(Constant.ListContent_sp4);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp5,false)) tabNames.add(Constant.ListContent_sp5);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp6,false)) tabNames.add(Constant.ListContent_sp6);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp7,false)) tabNames.add(Constant.ListContent_sp7);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp8,false)) tabNames.add(Constant.ListContent_sp8);



//        mView.initGoodsLists();
    }

    @Override
    public void prepareSearch() {
        mView.gotoSearch();
    }

    @Override
    public void prepareEditGoodsLists() {
        mView.gotoEditGoodsLists();
    }
}
