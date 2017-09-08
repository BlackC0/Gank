package gank.hyx.com.gank.ui.search;

import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void back();

        void searchComplete(SearchData searchData, String selectOption, String searchText);

        void searchFailed();

    }

    interface Presenter extends BasePresenter {

        void prepareBack();

        void search(String selectOption, String searchText);

    }

}
