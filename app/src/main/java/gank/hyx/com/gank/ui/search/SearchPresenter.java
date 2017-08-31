package gank.hyx.com.gank.ui.search;

/**
 * Created by Black.C on 2017/7/25.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View mView;

    public SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareBack() {
        
    }

    @Override
    public void prepareSearch() {

    }
}
