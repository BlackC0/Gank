package gank.hyx.com.gank.ui.present_detail;

import android.os.Bundle;

import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;

/**
 * Created by Black.C on 2017/9/25.
 */

public class PresentDetailActivity extends BaseActivity implements PresentDetailContract.View {

    private PresentDetailContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_detail);
        ButterKnife.bind(this);
        mActivity = this;
        new PresentDetailPresenter(this);
    }


    @Override
    public void initView() {

    }

    @Override
    public void back() {

    }

    @Override
    public void setPresenter(PresentDetailContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mPresenter.start();
    }
}
