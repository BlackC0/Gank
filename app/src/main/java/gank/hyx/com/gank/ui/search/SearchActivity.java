package gank.hyx.com.gank.ui.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.ui.search.empty.OnSelectedListener;
import gank.hyx.com.gank.ui.search.empty.SearchEmptyFragment;
import gank.hyx.com.gank.ui.search.empty.SearchEmptyPresenter;

public class SearchActivity extends BaseActivity implements SearchContract.View, OnSelectedListener {

    @BindView(R.id.searchActivity_editText)
    EditText searchActivity_editText;
    @BindView(R.id.searchActivity_Toolbar)
    Toolbar searchActivity_Toolbar;
    @BindView(R.id.searchActivity_FrameLayout_container)
    FrameLayout searchActivity_FrameLayout_container;
    private SearchContract.View mView;
    private SearchContract.Presenter mPresenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Boolean hasAddFragment = false;
    private SearchEmptyFragment searchEmptyFragment;
    private String searchOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mView = this;
        new SearchPresenter(mView, mActivity);
        initFragment();
        setContainer(searchEmptyFragment);
    }

    private void initFragment() {
        searchEmptyFragment = new SearchEmptyFragment();
        new SearchEmptyPresenter(searchEmptyFragment, mActivity, this);
    }

    private void setContainer(Fragment fragment) {
        synchronized (hasAddFragment) {
            if (!hasAddFragment) {
                if (fragmentManager == null || transaction == null) {
                    fragmentManager = getSupportFragmentManager();
                    transaction = fragmentManager.beginTransaction();
                }
                transaction.add(R.id.searchActivity_FrameLayout_container, fragment);
                transaction.commit();
                hasAddFragment = true;
                return;
            }
            transaction.replace(R.id.searchActivity_FrameLayout_container, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        mPresenter.prepareBack();
    }

    @OnClick({R.id.searchActivity_LinearLayout_back, R.id.searchActivity_textView_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchActivity_LinearLayout_back:
                mPresenter.prepareBack();
                break;
            case R.id.searchActivity_textView_search:
                mPresenter.search(searchOption, searchActivity_editText.getText().toString());
                // TODO: 2017/9/7 点了之后就是搜索，这时就应该切换fragment，且出现loading
                break;
        }
    }

    @Override
    public void setPresenter(SearchContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void back() {
        SearchActivity.super.onBackPressed();
    }

    @Override
    public void searchComplete(SearchData searchData) {
        // TODO: 2017/9/7 通过某种方式将数据传输到子fragment

    }

    @Override
    public void onSelected(String text) {
        searchOption = text;
    }

    @Override
    public void onHistorySearch(String text) {
        String[] strings = text.split("|");
        mPresenter.search(strings[0], strings[1]);
        // TODO: 2017/9/7 点了之后就是搜索，这时就应该切换fragment，且出现loading
    }
}
