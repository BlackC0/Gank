package gank.hyx.com.gank.ui.goods_detail;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    @BindView(R.id.goodsDetailActivity_WebView_2)
    NestedScrollWebView goodsDetailActivity_WebView_2;
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
    @BindView(R.id.goodsDetailActivity_linearLayout_no_Image)
    LinearLayout goodsDetailActivity_linearLayout_no_Image;
    private String url;
    private String imgUrl;
    private String desc;
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
    }

    @Override
    public void setPresenter(GoodsDetailContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mPresenter.start();
    }

    @OnClick(R.id.goodsDetailActivity_toolbar_back)
    public void onViewClicked() {
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            imgUrl = intent.getStringExtra("imgUrl");
            desc = intent.getStringExtra("desc");
        }
        if ("".equals(imgUrl)) {
            goodsDetailActivity_linearLayout_no_Image.setVisibility(View.VISIBLE);
            goodsDetailActivity_appBarLayout.setVisibility(View.GONE);
            goodsDetailActivity_WebView.setVisibility(View.GONE);
            WebSettings webSettings = goodsDetailActivity_WebView_2.getSettings();
            webSettings.setUseWideViewPort(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setBuiltInZoomControls(true);

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setAppCachePath(goodsDetailActivity_WebView.getContext().getCacheDir().getAbsolutePath());

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//网络正常时使用默认缓存策略
            } else {
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);//网络不可用时只使用缓存
            }

            goodsDetailActivity_WebView_2.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            goodsDetailActivity_WebView_2.loadUrl(url);
            goodsDetailActivity_textView_title_2.setText(desc);
        } else {
            goodsDetailActivity_linearLayout_no_Image.setVisibility(View.GONE);
            goodsDetailActivity_appBarLayout.setVisibility(View.VISIBLE);
            goodsDetailActivity_WebView.setVisibility(View.VISIBLE);
            WebSettings webSettings = goodsDetailActivity_WebView.getSettings();
            webSettings.setUseWideViewPort(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setBuiltInZoomControls(true);

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setAppCachePath(goodsDetailActivity_WebView.getContext().getCacheDir().getAbsolutePath());

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//网络正常时使用默认缓存策略
            } else {
                webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);//网络不可用时只使用缓存
            }

            goodsDetailActivity_WebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            goodsDetailActivity_WebView.loadUrl(url);
            goodsDetailActivity_textView_title.setText(desc);
            Glide.with(mActivity).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(goodsDetailActivity_imageView_background);
        }
    }

    @Override
    public void back() {

    }
}
