package gank.hyx.com.gank.ui.search.list_content;

/**
 * Created by Black.C on 2017/7/25.
 */

public class SearchListContentPresenter implements SearchListContentContract.Presenter {

    private final SearchListContentContract.View mView;

    public SearchListContentPresenter(SearchListContentContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareRefresh() {
        // TODO: 2017/9/7
    }

    @Override
    public void prepareLoadMore() {
        // TODO: 2017/9/7
    }
}
