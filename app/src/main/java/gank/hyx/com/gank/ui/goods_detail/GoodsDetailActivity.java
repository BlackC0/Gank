package gank.hyx.com.gank.ui.goods_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.view.NestedScrollWebView;

/**
 * Created by Black.C on 2017/9/25.
 */

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailContract.View {


    @BindView(R.id.goodsDetailActivity_WebView)
    NestedScrollWebView goodsDetailActivity_WebView;
    @BindView(R.id.goodsDetailActivity_imageView_background)
    ImageView goodsDetailActivity_imageView_background;
    @BindView(R.id.goodsDetailActivity_imageView_back)
    ImageView goodsDetailActivity_imageView_back;
    @BindView(R.id.goodsDetailActivity_textView_title)
    TextView goodsDetailActivity_textView_title;
    @BindView(R.id.goodsDetailActivity_appBarLayout)
    AppBarLayout goodsDetailActivity_appBarLayout;
    @BindView(R.id.goodsDetailActivity_imageView_back_2)
    ImageView goodsDetailActivity_imageView_back_2;
    @BindView(R.id.goodsDetailActivity_textView_title_2)
    TextView goodsDetailActivity_textView_title_2;
    @BindView(R.id.goodsDetailActivity_linearLayout_title_2)
    LinearLayout goodsDetailActivity_linearLayout_title_2;
    private String url;
    private String imgUrl;
    private GoodsDetailContract.View mView;
    private GoodsDetailContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        mView = this;
        mActivity = this;
        new GoodsDetailPresenter(mView);
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            imgUrl = intent.getStringExtra("imgUrl");
        }
        goodsDetailActivity_WebView.loadUrl(url);
        if ("".equals(imgUrl)) {
            goodsDetailActivity_WebView.setVisibility(View.GONE);
        } else {
            Glide.with(mActivity).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(goodsDetailActivity_imageView_background);
        }
    }

    @Override
    public void setPresenter(GoodsDetailContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @OnClick(R.id.goodsDetailActivity_toolbar_back)
    public void onViewClicked() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void back() {

    }
}
