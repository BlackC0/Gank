package gank.hyx.com.gank.ui.main.present;

/**
 * Created by Black.C on 2017/7/25.
 */

public class PresentPresenter implements PresentContract.Presenter {

    private final PresentContract.View mView;

    public PresentPresenter(PresentContract.View mView) {
        this.mView = mView;
        mView.setmPresenter(this);
    }

    @Override
    public void start() {
    }
}
