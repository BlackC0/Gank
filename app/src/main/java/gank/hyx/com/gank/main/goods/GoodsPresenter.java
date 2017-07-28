package gank.hyx.com.gank.main.goods;

/**
 * Created by Black.C on 2017/7/25.
 */

public class GoodsPresenter implements GoodsContract.Presenter {

    private final GoodsContract.View mMainActivityView;

    public GoodsPresenter(GoodsContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
