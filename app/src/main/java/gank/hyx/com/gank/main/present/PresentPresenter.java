package gank.hyx.com.gank.main.present;

/**
 * Created by Black.C on 2017/7/25.
 */

public class PresentPresenter implements PresentContract.Presenter {

    private final PresentContract.View mMainActivityView;

    public PresentPresenter(PresentContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
