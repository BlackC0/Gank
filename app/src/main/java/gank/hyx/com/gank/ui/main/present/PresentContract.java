package gank.hyx.com.gank.ui.main.present;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface PresentContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }

}
