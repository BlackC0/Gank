package gank.hyx.com.gank.ui.present_detail;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface PresentDetailContract {

    interface View extends BaseView<Presenter> {

        void initView();

        void back();


    }

    interface Presenter extends BasePresenter {

        void prepareBack();
    }

}
