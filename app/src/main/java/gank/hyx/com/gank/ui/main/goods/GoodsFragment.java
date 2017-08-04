package gank.hyx.com.gank.ui.main.goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.ui.edit_list_content.EditListsContentActivity;

import static android.app.Activity.RESULT_OK;

public class GoodsFragment extends BaseFragment implements GoodsContract.View {

    @BindView(R.id.goodsFragment_ViewPager)
    ViewPager goodsFragment_ViewPager;
    @BindView(R.id.goodsFragment_TabLayout)
    TabLayout goodsFragment_TabLayout;
    @BindView(R.id.goodsFragment_FloatingActionButton)
    FloatingActionButton goodsFragment_FloatingActionButton;
    private View rootView;
    private Activity mActivity;
    private GoodsContract.Presenter mPresenter;

    private GoodsPagerAdapter adapter;
    private ArrayList<String> tabNames = new ArrayList<>();
    private ArrayList<BaseFragment> fragmentsLists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragement_goods, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void initGoodsLists(ArrayList<String> tabNames, ArrayList<BaseFragment> fragmentsLists) {
        goodsFragment_TabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        this.tabNames.clear();
        this.fragmentsLists.clear();
        this.tabNames.addAll(tabNames);
        this.fragmentsLists.addAll(fragmentsLists);
        adapter = new GoodsPagerAdapter(getChildFragmentManager(), this.fragmentsLists, tabNames, mActivity);
        goodsFragment_ViewPager.setAdapter(adapter);
        goodsFragment_ViewPager.setOffscreenPageLimit(3);
        goodsFragment_TabLayout.setupWithViewPager(goodsFragment_ViewPager);
    }

    @Override
    public void gotoSearch() {
        // TODO: 2017/8/1 跳转到搜索页面
    }

    @Override
    public void gotoEditListsContent() {
        Intent intent = new Intent(mActivity, EditListsContentActivity.class);
        startActivityForResult(intent, Constant.EDIT_LISTS_CONTENT);
    }

    public void setPresenter(GoodsContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }


    @OnClick({R.id.goodsFragment_ImageView_add, R.id.goodsFragment_FloatingActionButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goodsFragment_ImageView_add:
                mPresenter.prepareEditListsContent();
                break;
            case R.id.goodsFragment_FloatingActionButton:
                mPresenter.prepareSearch();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.EDIT_LISTS_CONTENT && resultCode == RESULT_OK){
            mPresenter.start();
        }

    }
}