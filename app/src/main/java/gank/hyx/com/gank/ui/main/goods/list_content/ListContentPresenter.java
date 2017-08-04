package gank.hyx.com.gank.ui.main.goods.list_content;

/**
 * Created by BlackC on 2017/7/25.
 */

public class ListContentPresenter implements ListContentContract.Presenter {

    private final ListContentContract.View mView;

    public ListContentPresenter(ListContentContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareRefresh() {

    }

    @Override
    public void prepareLoadMore() {

    }

    @Override
    public void prepareGoodsDetail() {
        mView.gotoGoodsDetail();
    }
}
