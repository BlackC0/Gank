package gank.hyx.com.gank.ui.main.my;

/**
 * Created by Black.C on 2017/7/25.
 */

public class MyPresenter implements MyContract.Presenter {

    private final MyContract.View mMainActivityView;

    public MyPresenter(MyContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setmPresenter(this);
    }

    @Override
    public void start() {
    }
}
