package gank.hyx.com.gank.ui.present_detail;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2017/7/25.
 */

public class PresentDetailPresenter implements PresentDetailContract.Presenter {

    private final PresentDetailContract.View mView;
    private SharedPreferences collectionSP;
    private SharedPreferences.Editor editor;
    private Activity mActivity;

    public PresentDetailPresenter(PresentDetailContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
        this.collectionSP = this.mActivity.getSharedPreferences(Constant.TableName3, Context.MODE_PRIVATE);
        this.editor = collectionSP.edit();
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initView();
    }

    @Override
    public void prepareBack() {
        mView.back();
    }

    @Override
    public void prepareSend(String imgUrl) {
        File file = new File(Constant.shareImg);
        if (!file.exists()) {
            file.mkdirs();
        }

        final String path = Constant.shareImg + System.currentTimeMillis() + ".png";
        FileDownloader.getImpl().create(imgUrl)
                .setPath(path)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Log.d("hyx", "");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        mView.send(path);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }
                })
                .start();
    }

    @Override
    public void prepareAvatarSetting(String imgUrl) {
        editor.putString(Constant.Collection_sp1, imgUrl);
        editor.commit();
        mView.avatarSetting();
    }


    @Override
    public void prepareDownload(String desc, String imgUrl) {
        File file = new File(Constant.shareImg);
        if (!file.exists()) {
            file.mkdirs();
        }

        final String path = Constant.shareImg + desc + ".png";
        FileDownloader.getImpl().create(imgUrl)
                .setPath(path)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        mView.download(path);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }
                })
                .start();
    }

    @Override
    public void prepareCollection(String desc, String imgUrl) {
        String data = collectionSP.getString(desc, "");
        if (imgUrl.equals(data)) {
            mView.collection(false);
        } else {
            editor.putString(desc, imgUrl);
            editor.commit();
            mView.collection(true);
        }
    }

}
