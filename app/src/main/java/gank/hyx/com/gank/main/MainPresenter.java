package gank.hyx.com.gank.main;

/**
 * Created by Black.C on 2017/7/25.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainActivityView;

    public MainPresenter(MainContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void search() {

    }

    @Override
    public void goods() {

    }

    @Override
    public void my() {

    }
}
