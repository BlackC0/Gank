package gank.hyx.com.gank.ui.main.goods;

/**
 * Created by Black.C on 2017/7/25.
 */

public class GoodsPresenter implements GoodsContract.Presenter {

    private final GoodsContract.View mView;

    public GoodsPresenter(GoodsContract.View mMainActivityView) {
        this.mView = mMainActivityView;
        mMainActivityView.setmPresenter(this);
    }

    @Override
    public void start() {
        // TODO: 2017/8/1 获取数据库中有哪几个干货列表
//        mView.initGoodsLists();
    }

    @Override
    public void search() {
        mView.gotoSearch();
    }

    @Override
    public void editGoodsLists() {
        mView.gotoditGoodsLists();
    }
}
