package gank.hyx.com.gank.ui.main.goods;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.ui.main.goods.list_content.ListContentFragment;
import gank.hyx.com.gank.ui.main.goods.list_content.ListContentPresenter;

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
        tabNames.clear();
        fragmentLists.clear();
        tabNames.add(Constant.ListContent_sp1);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp2, false))
            tabNames.add(Constant.ListContent_sp2);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp3, false))
            tabNames.add(Constant.ListContent_sp3);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp4, false))
            tabNames.add(Constant.ListContent_sp4);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp5, false))
            tabNames.add(Constant.ListContent_sp5);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp6, false))
            tabNames.add(Constant.ListContent_sp6);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp7, false))
            tabNames.add(Constant.ListContent_sp7);
        if (mSharedPreferences.getBoolean(Constant.ListContent_sp8, false))
            tabNames.add(Constant.ListContent_sp8);

        for (String tabName : tabNames) {
            ListContentFragment fragment = ListContentFragment.newInstance(tabName);
            fragmentLists.add(fragment);
            new ListContentPresenter(fragment);
        }

        mView.initGoodsLists(tabNames,fragmentLists);
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
