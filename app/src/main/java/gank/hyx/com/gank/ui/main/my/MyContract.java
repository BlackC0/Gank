package gank.hyx.com.gank.ui.main.my;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface MyContract {

    interface View extends BaseView<Presenter> {

        void initView(String imgUrl,String nickName);

        void gotoMyCollection();

        void cleanCache();

    }

    interface Presenter extends BasePresenter {

        void prepareCleanCache();

        void prepareMyCollection();


    }

}
