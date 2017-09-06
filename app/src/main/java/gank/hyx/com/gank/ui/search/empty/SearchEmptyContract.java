package gank.hyx.com.gank.ui.search.empty;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface SearchEmptyContract {

    interface View extends BaseView<Presenter> {

        void selected(int index);

    }

    interface Presenter extends BasePresenter {

        void selecting(int index, String text);

        void onHistorySearch(String text);

    }

}
