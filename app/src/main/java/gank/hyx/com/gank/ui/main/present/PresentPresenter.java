package gank.hyx.com.gank.ui.main.present;

/**
 * Created by Black.C on 2017/7/25.
 */

public class PresentPresenter implements PresentContract.Presenter {

    private final PresentContract.View mMainActivityView;

    public PresentPresenter(PresentContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setmPresenter(this);
    }

    @Override
    public void start() {
    }
}
