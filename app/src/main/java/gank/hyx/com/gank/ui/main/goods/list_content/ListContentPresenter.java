package gank.hyx.com.gank.ui.main.goods.list_content;

/**
 * Created by BlackC on 2017/7/25.
 */

public class ListContentPresenter implements ListContentContract.Presenter {

    private final ListContentContract.View mMainActivityView;

    public ListContentPresenter(ListContentContract.View mMainActivityView) {
        this.mMainActivityView = mMainActivityView;
        mMainActivityView.setmPresenter(this);
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

    }
}
