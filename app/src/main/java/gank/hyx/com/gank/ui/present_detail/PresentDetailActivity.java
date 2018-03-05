package gank.hyx.com.gank.ui.present_detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.tool.DisplayUtil;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.view.MyPopupWindows;

/**
 * Created by Black.C on 2017/9/25.
 */

public class PresentDetailActivity extends BaseActivity implements PresentDetailContract.View {


    private static final int SEND_CODE = 0x99;
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
    private String path = "";

    private static final int EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_SEND = 0x91;
    private static final int EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_DOWNLOAD = 0x92;

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
                case 2:
                    gotoHeaderPopWindow();
                    gotoMenuPopWindow();
                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        mActivity = this;
        new PresentDetailPresenter(this, mActivity);
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
        handler.sendEmptyMessageDelayed(2, 500);
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void send(String path) {
        this.path = path;
        File shareFile = new File(path);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_STREAM,
                Uri.fromFile(shareFile));
        share.setType("*/*");//此处可发送多种文件
        startActivityForResult(Intent.createChooser(share, "分享图片"), SEND_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SEND_CODE:
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                break;
        }
    }

    @Override
    public void avatarSetting() {
        Toast.makeText(mActivity,"头像设置成功！",Toast.LENGTH_SHORT).show();
        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(new Intent(Constant.AVATAR_UPDATE));
    }

    @Override
    public void download(String path) {
        Toast.makeText(mActivity, "文件下载成功，保存在shareImg\\" + desc + ".png", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collection(boolean state) {
        if (state) {
            Toast.makeText(mActivity, "收藏成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, "已经收藏了！", Toast.LENGTH_SHORT).show();
        }
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
        headerPopWindows.setFocusable(false);
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
                    headerShowTime += 20;
                    handler.sendEmptyMessage(0);
                }
            }
        } else {
            synchronized (headerLock) {
                headerShowTime = 0;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionResult(requestCode, grantResults);
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
        menuPopWindows.setOutsideTouchable(false);
        menuPopWindows.setTouchable(true);
        menuPopWindows.setAnimationStyle(R.style.menuPopupAnimation);
        menuPopWindows.setFocusable(false);
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
                    menuShowTime += 20;
                    handler.sendEmptyMessage(1);
                }
            }
        } else {
            synchronized (menuLock) {
                menuShowTime = 0;
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
                    checkPermission(0);
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_avatar_setting:
                    mPresenter.prepareAvatarSetting(url);
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_download:
                    checkPermission(1);
                    break;
                case R.id.presentDetailActivity_linearLayout_menu_collection:
                    mPresenter.prepareCollection(desc, url);
                    break;
            }
        }
    };

    private void checkPermission(int actionType) {
        switch (actionType) {
            case 0://send
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) &&
                        (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED)) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_SEND);
                    return;
                }
                mPresenter.prepareSend(url);
                break;
            case 1://download
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) &&
                        (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED)) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_DOWNLOAD);
                    return;
                }
                mPresenter.prepareDownload(desc, url);
                break;
        }
    }

    private void permissionResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_SEND:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.prepareSend(url);
                    return;
                }
                Toast.makeText(mActivity, "分享图片及下载保存需要文件访问权限，请在设置中开启相关权限", Toast.LENGTH_SHORT).show();
                break;
            case EXPORT_WRITE_EXTERNAL_STORAGE_REQUEST_CODE_DOWNLOAD:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.prepareDownload(desc, url);
                    return;
                }
                Toast.makeText(mActivity, "分享图片及下载保存需要文件访问权限，请在设置中开启相关权限", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
