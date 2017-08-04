package gank.hyx.com.gank.ui.main.my;

/**
 * Created by Black.C on 2017/7/25.
 */

public class MyPresenter implements MyContract.Presenter {

    private final MyContract.View mView;

    public MyPresenter(MyContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
