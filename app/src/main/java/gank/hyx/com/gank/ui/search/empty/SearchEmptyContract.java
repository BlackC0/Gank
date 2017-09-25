package gank.hyx.com.gank.ui.search.empty;

import java.util.ArrayList;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface SearchEmptyContract {

    interface View extends BaseView<Presenter> {

        void initView(ArrayList<String> selectOptions, ArrayList<String> historyOptions);

    }

    interface Presenter extends BasePresenter {

        void selecting(String text);

        void onHistorySearch(String text);

        void onClearHistory();

    }

}
