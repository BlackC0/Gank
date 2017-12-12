package gank.hyx.com.gank.ui.present_detail;

/**
 * Created by Black.C on 2017/7/25.
 */

public class PresentDetailPresenter implements PresentDetailContract.Presenter {

    private final PresentDetailContract.View mView;

    public PresentDetailPresenter(PresentDetailContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
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
    public void prepareSend() {
        mView.send();
    }

    @Override
    public void prepareAvatarSetting() {
        mView.avatarSetting();
    }

    @Override
    public void prepareDownload() {
        mView.download();
    }

    @Override
    public void prepareCollection() {
        mView.collection();
    }
}
