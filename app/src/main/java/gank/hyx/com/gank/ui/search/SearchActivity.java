package gank.hyx.com.gank.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;

public class SearchActivity extends BaseActivity implements SearchContract.View{

    @BindView(R.id.searchActivity_FloatingActionButton)
    FloatingActionButton searchActivity_FloatingActionButton;
    @BindView(R.id.searchActivity_editText)
    EditText searchActivity_editText;
    @BindView(R.id.searchActivity_Toolbar)
    Toolbar searchActivity_Toolbar;
    @BindView(R.id.searchActivity_FrameLayout_container)
    FrameLayout searchActivity_FrameLayout_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        searchActivity_FloatingActionButton.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.searchActivity_FloatingActionButton, R.id.searchActivity_LinearLayout_back, R.id.searchActivity_textView_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchActivity_FloatingActionButton:
                break;
            case R.id.searchActivity_LinearLayout_back:
                break;
            case R.id.searchActivity_textView_search:
                break;
        }
    }

    @Override
    public void setPresenter(SearchContract.Presenter mPresenter) {

    }
}
