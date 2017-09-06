package gank.hyx.com.gank.ui.search.empty;

import gank.hyx.com.gank.ui.main.my.MyContract;

/**
 * Created by Black.C on 2017/7/25.
 */

public class EmptyPresenter implements MyContract.Presenter {

    private final MyContract.View mView;

    public EmptyPresenter(MyContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
