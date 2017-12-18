package gank.hyx.com.gank.ui.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flyn.Eyes;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.ui.search.empty.OnSelectedListener;
import gank.hyx.com.gank.ui.search.empty.SearchEmptyFragment;
import gank.hyx.com.gank.ui.search.empty.SearchEmptyPresenter;
import gank.hyx.com.gank.ui.search.list_content.OnSearchListener;
import gank.hyx.com.gank.ui.search.list_content.SearchListContentFragment;
import gank.hyx.com.gank.ui.search.list_content.SearchListContentPresenter;

public class SearchActivity extends BaseActivity implements SearchContract.View, OnSelectedListener {

    @BindView(R.id.searchActivity_editText)
    EditText searchActivity_editText;
    @BindView(R.id.searchActivity_Toolbar)
    Toolbar searchActivity_Toolbar;
    @BindView(R.id.searchActivity_FrameLayout_container)
    FrameLayout searchActivity_FrameLayout_container;
    @BindView(R.id.searchActivity_textView_search)
    TextView searchActivity_textView_search;
    private SearchContract.View mView;
    private SearchContract.Presenter mPresenter;
    private OnSearchListener listener;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Boolean hasAddFragment = false;
    private String searchOption = "all";
    private SearchEmptyFragment searchEmptyFragment;
    private SearchListContentFragment searchListContentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.toolbar_bg));
        mView = this;
        new SearchPresenter(mView, mActivity);
        initFragment();
        setContainer(searchEmptyFragment);
        searchListContentFragment = null;
    }

    private void initFragment() {
        if (searchEmptyFragment == null) {
            searchEmptyFragment = new SearchEmptyFragment();
            new SearchEmptyPresenter(searchEmptyFragment, mActivity, this);
        }
        if (searchListContentFragment == null) {
            searchListContentFragment = new SearchListContentFragment();
            new SearchListContentPresenter(searchListContentFragment, mActivity);
        }

    }

    private void setContainer(Fragment fragment) {
        synchronized (hasAddFragment) {
            if (!hasAddFragment) {
                if (fragmentManager == null) {
                    fragmentManager = getSupportFragmentManager();
                }
                transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.searchActivity_FrameLayout_container, fragment);
                transaction.commit();
                hasAddFragment = true;
                return;
            }
            transaction = fragmentManager.beginTransaction();
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
                String text = searchActivity_editText.getText().toString();
                mPresenter.search(searchOption, text == null || "".equals(text) ? "" : text);
                searchActivity_editText.setEnabled(false);
                searchActivity_textView_search.setEnabled(false);
                searchActivity_textView_search.setTextColor(getResources().getColor(R.color.gray));
                initFragment();
                setContainer(searchListContentFragment);
                searchEmptyFragment = null;
                listener.appearLoading();
                mActivity.keyboardForces();
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
    public void searchComplete(SearchData searchData, String selectOption, String searchText) {
        listener.onSearchComplete(searchData, selectOption, searchText);
        searchActivity_editText.setEnabled(true);
        searchActivity_textView_search.setEnabled(true);
        searchActivity_textView_search.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void searchFailed() {
        initFragment();
        setContainer(searchEmptyFragment);
        searchListContentFragment = null;
        searchActivity_editText.setEnabled(true);
        searchActivity_textView_search.setEnabled(true);
        searchActivity_textView_search.setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    public void onSelected(String text) {
        searchOption = text;
    }

    @Override
    public void onHistorySearch(String text) {
        String[] strings = text.split("/");
        mPresenter.search(strings[0], strings[1]);
        searchActivity_editText.setEnabled(false);
        searchActivity_textView_search.setEnabled(false);
        searchActivity_textView_search.setTextColor(getResources().getColor(R.color.gray));
        initFragment();
        setContainer(searchListContentFragment);
        searchEmptyFragment = null;
        listener.appearLoading();
        mActivity.keyboardForces();
    }

    public void setSearchCompleteListener(OnSearchListener listener) {
        this.listener = listener;
    }

}
