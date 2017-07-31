package gank.hyx.com.gank.ui.main.goods;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface GoodsContract {

    interface View extends BaseView<Presenter> {

        void gotoGoodsDetail();


    }
    interface Presenter extends BasePresenter {
        void GoodsDetail();

        //获取列表数据（）
        void getListData();



    }

}
