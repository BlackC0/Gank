package gank.hyx.com.gank.ui.goods_detail;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;

/**
 * Created by Black.C on 2017/9/25.
 */

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View {


    @BindView(R.id.GoodsDetailActivity_WebView)
    WebView GoodsDetailActivity_WebView;
    private String url;
    private GoodsDetailContract.View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        mView = this;
        mActivity = this;
        if (getIntent() != null) {
            url = getIntent().getStringExtra("url");
        }
        GoodsDetailActivity_WebView.loadUrl(url);
    }

    @Override
    public void setPresenter(GoodsDetailContract.Presenter mPresenter) {

    }
}
