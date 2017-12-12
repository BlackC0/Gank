package gank.hyx.com.gank.ui.present_detail;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.DisplayUtil;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.view.MyPopupWindows;

/**
 * Created by Black.C on 2017/9/25.
 */

public class PresentDetailActivity extends BaseActivity implements PresentDetailContract.View {

    @BindView(R.id.presentDetailActivity_ImageView_item)
    ImageView presentDetailActivity_ImageView_item;

    private PresentDetailContract.Presenter mPresenter;
    private String url;
    private String desc;

    private boolean headerShow;
    private int headerShowTime = 0;
    private static final Object headerLock = new Object();
    private boolean menuShow;
    private int menuShowTime = 0;
    private static final Object menuLock = new Object();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (headerShowTime > 0) {
                        synchronized (headerLock) {
                            headerShowTime--;
                            sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        synchronized (headerLock) {
                            headerShowTime = 0;
                            headerShow = false;
                            dismissHeaderPopWindow();
                        }
                    }
                    break;
                case 1:
                    if (menuShowTime > 0) {
                        synchronized (menuLock) {
                            menuShowTime--;
                            sendEmptyMessageDelayed(1, 1000);
                        }
                    } else {
                        synchronized (menuLock) {
                            menuShowTime = 0;
                            menuShow = false;
                            dismissMenuPopWindow();
                        }
                    }
                    break;
            }
        }
    };

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
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("imgUrl");
            desc = intent.getStringExtra("desc");
        }

        Glide.with(mActivity)
                .load(url + "?imageView2/0/w/" + DisplayUtil.getScreenWidth(mActivity))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(presentDetailActivity_ImageView_item);

        initHeaderPopWindows();
        initMenuPopWindows();
    }

    @Override
    public void back() {

    }

    @Override
    public void send() {

    }

    @Override
    public void avatarSetting() {

    }

    @Override
    public void download() {

    }

    @Override
    public void collection() {

    }

    @Override
    public void setPresenter(PresentDetailContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        this.mPresenter.start();
    }

    @OnClick({R.id.presentDetailActivity_relativeLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.presentDetailActivity_relativeLayout:
                gotoHeaderPopWindow();
                gotoMenuPopWindow();
                break;
        }
    }

    private MyPopupWindows headerPopWindows;
    private RelativeLayout presentDetailActivity_relativeLayout_back;
    private TextView presentDetailActivity_textView_title;

    private void initHeaderPopWindows() {
        View headerPop = mActivity.getLayoutInflater().inflate(
                R.layout.activity_present_detail_header, null, false);

        presentDetailActivity_relativeLayout_back = (RelativeLayout) headerPop.findViewById(R.id.presentDetailActivity_relativeLayout_back);
        presentDetailActivity_textView_title = (TextView) headerPop.findViewById(R.id.presentDetailActivity_textView_title);
        presentDetailActivity_relativeLayout_back.setOnClickListener(popOnClicker);
        presentDetailActivity_textView_title.setText(desc);

        headerPopWindows = new MyPopupWindows(headerPop, mActivity, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        headerPopWindows.setBackgroundDrawable(new BitmapDrawable());
        headerPopWindows.setOutsideTouchable(false);
        headerPopWindows.setTouchable(true);
        headerPopWindows.setAnimationStyle(R.style.headerPopupAnimation);
        headerPopWindows.setFocusable(true);
    }

    private void dismissHeaderPopWindow() {
        if (headerPopWindows != null && headerPopWindows.isShowing()) {
            headerPopWindows.dismiss();
        }
    }

    private void gotoHeaderPopWindow() {
        if (headerShow != true) {
            if (headerPopWindows != null && !headerPopWindows.isShowing()) {
                headerPopWindows.showAtLocation(presentDetailActivity_ImageView_item, Gravity.TOP, 0, 0);
                headerShow = true;
                synchronized (headerLock) {
                    headerShowTime += 5;
                    handler.sendEmptyMessage(0);
                }
            }
        } else {
            synchronized (headerLock) {
                headerShowTime += 5;
            }
        }
    }

    private MyPopupWindows menuPopWindows;
    private LinearLayout presentDetailActivity_linearLayout_menu_send;
    private LinearLayout presentDetailActivity_linearLayout_menu_avatar_setting;
    private LinearLayout presentDetailActivity_linearLayout_menu_download;
    private LinearLayout presentDetailActivity_linearLayout_menu_collection;

    private void initMenuPopWindows() {
        View menuPop = mActivity.getLayoutInflater().inflate(
                R.layout.activity_present_detail_menu, null, false);

        presentDetailActivity_linearLayout_menu_send = (LinearLayout) menuPop.findViewById(R.id.presentDetailActivity_linearLayout_menu_send);
        presentDetailActivity_linearLayout_menu_avatar_setting = (LinearLayout) menuPop.findViewById(R.id.presentDetailActivity_linearLayout_menu_avatar_setting);
        presentDetailActivity_linearLayout_menu_download = (LinearLayout) menuPop.findViewById(R.id.presentDetailActivity_linearLayout_menu_download);
        presentDetailActivity_linearLayout_menu_collection = (LinearLayout) menuPop.findViewById(R.id.presentDetailActivity_linearLayout_menu_collection);

        presentDetailActivity_linearLayout_menu_send.setOnClickListener(popOnClicker);
        presentDetailActivity_linearLayout_menu_avatar_setting.setOnClickListener(popOnClicker);
        presentDetailActivity_linearLayout_menu_download.setOnClickListener(popOnClicker);
        presentDetailActivity_linearLayout_menu_collection.setOnClickListener(popOnClicker);

        menuPopWindows = new MyPopupWindows(menuPop, mActivity, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        menuPopWindows.setBackgroundDrawable(new BitmapDrawable());
        headerPopWindows.setAnimationStyle(R.style.menuPopupAnimation);
        menuPopWindows.setOutsideTouchable(false);
        menuPopWindows.setTouchable(true);
        menuPopWindows.setFocusable(true);
    }

    private void dismissMenuPopWindow() {
        if (menuPopWindows != null && menuPopWindows.isShowing()) {
            menuPopWindows.dismiss();
        }
    }

    private void gotoMenuPopWindow() {
        if (menuShow != true) {
            if (menuPopWindows != null && !menuPopWindows.isShowing()) {
                menuPopWindows.showAtLocation(presentDetailActivity_ImageView_item, Gravity.BOTTOM, 0, 0);
                menuShow = true;
                synchronized (menuLock) {
                    menuShowTime += 5;
                    handler.sendEmptyMessage(1);
                }
            }
        } else {
            synchronized (menuLock) {
                menuShowTime += 5;
            }
        }
    }

    public View.OnClickListener popOnClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.presentDetailActivity_relativeLayout_back:
                    mPresenter.prepareBack();
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_send:
                    mPresenter.prepareSend();
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_avatar_setting:
                    mPresenter.prepareAvatarSetting();
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_download:
                    mPresenter.prepareDownload();
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_collection:
                    mPresenter.prepareCollection();
                    break;
            }
        }
    };


}
