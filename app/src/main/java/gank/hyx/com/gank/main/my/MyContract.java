package gank.hyx.com.gank.main.my;

import gank.hyx.com.gank.BasePresenter;
import gank.hyx.com.gank.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface MyContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }

}
