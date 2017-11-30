package gank.hyx.com.gank.ui.goods_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.DisplayUtil;
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
    private GoodsDetailContract.Presenter mPresenter;
    private int pixel = 0;
    private boolean isBlack = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                if (pixel == 0) {
                                    if ("".equals(imgUrl) || imgUrl == null) {
                                        return;
                                    }
                                    Bitmap background = Glide.with(mActivity)
                                            .load(Uri.parse(imgUrl))
                                            .asBitmap()
                                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                                    pixel = background.getPixel(10, 10);
                                }
                                int red = Color.red(pixel);
                                int green = Color.green(pixel);
                                int blue = Color.blue(pixel);
                                if ((red >= 127 && green >= 127) || (red >= 127 && blue >= 127) || (green >= 127 && blue >= 127)) {
                                    mHandler.sendEmptyMessage(1);
                                } else {
                                    mHandler.sendEmptyMessage(2);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                case 1:
                    isBlack = true;
                    goodsDetailActivity_imageView_back.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                    goodsDetailActivity_textView_title.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 2:
                    goodsDetailActivity_imageView_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    goodsDetailActivity_textView_title.setTextColor(getResources().getColor(R.color.white));
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        mActivity = this;
        new GoodsDetailPresenter(this);
    }

    @Override
    public void setPresenter(GoodsDetailContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mPresenter.start();
    }


    @OnClick({R.id.goodsDetailActivity_linearLayout_title_2, R.id.goodsDetailActivity_toolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goodsDetailActivity_linearLayout_title_2:
                mPresenter.prepareBack();
                break;
            case R.id.goodsDetailActivity_toolbar_back:
                mPresenter.prepareBack();
                break;
        }
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
            Glide.with(mActivity)
                    .load(imgUrl + DisplayUtil.sizeOfImageforFullWidth(mActivity, 200))
                    .crossFade()
                    .centerCrop()
                    .into(goodsDetailActivity_imageView_background);

            goodsDetailActivity_appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (verticalOffset == 0) {//完全展开状态
                        mHandler.sendEmptyMessage(0);
                        return;
                    }
                    if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {//折叠状态
                        mHandler.sendEmptyMessage(2);
                        return;
                    }
                    //中间状态
                    if (isBlack) {
                        float offset = Math.abs(verticalOffset) == 0 ? 0 : ((float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange());
                        if (!"".equals(imgUrl)) {
                            goodsDetailActivity_imageView_back.setColorFilter(Color.rgb((int) (255 * (offset)), (int) (255 * (offset)), (int) (255 * (offset))), PorterDuff.Mode.SRC_ATOP);
                            goodsDetailActivity_textView_title.setTextColor(Color.rgb((int) (255 * (offset)), (int) (255 * (offset)), (int) (255 * (offset))));
                        }
                    }
                }
            });

        }
    }

    @Override
    public void back() {
        finish();
    }

}
