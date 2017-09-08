package gank.hyx.com.gank.ui.search.list_content;

import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface SearchListContentContract {

    interface View extends BaseView<Presenter> {

        void init(SearchData data);

        void refresh(SearchData data);

        void loadMore(SearchData data);

        void appearLoading();

    }

    interface Presenter extends BasePresenter {
        //view层确定调用刷新的时机，并把行为传到presenter，presenter将数据找到之后，将数据返回到view层进行加载
        void prepareRefresh();

        void prepareLoadMore();

    }

}
