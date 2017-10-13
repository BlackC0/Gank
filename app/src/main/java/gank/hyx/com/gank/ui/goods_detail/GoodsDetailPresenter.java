package gank.hyx.com.gank.ui.goods_detail;

/**
 * Created by Black.C on 2017/7/25.
 */

public class GoodsDetailPresenter implements GoodsDetailContract.Presenter {

    private final GoodsDetailContract.View mView;

    public GoodsDetailPresenter(GoodsDetailContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initView();
    }

    @Override
    public void prepareBack() {

    }
}
