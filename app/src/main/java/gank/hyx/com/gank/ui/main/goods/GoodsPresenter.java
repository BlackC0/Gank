package gank.hyx.com.gank.ui.main.goods;

import android.app.Activity;
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
    private Activity mActivity;
    private SharedPreferences mSharedPreferences;
    private ArrayList<BaseFragment> fragmentLists = new ArrayList<>();
    private ArrayList<String> tabNames = new ArrayList<>();

    public GoodsPresenter(GoodsContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
        this.mView.setPresenter(this);
        this.mSharedPreferences = mActivity.getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);
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

        for (int i = 0; i < tabNames.size(); i++) {
            ListContentFragment fragment = ListContentFragment.newInstance(tabNames.get(i));
            fragmentLists.add(fragment);
            new ListContentPresenter(fragment, mActivity, tabNames.get(i));
        }

        mView.initGoodsLists(tabNames, fragmentLists);
    }

    @Override
    public void prepareSearch() {
        mView.gotoSearch();
    }

    @Override
    public void prepareEditListsContent() {
        mView.gotoEditListsContent();
    }
}
