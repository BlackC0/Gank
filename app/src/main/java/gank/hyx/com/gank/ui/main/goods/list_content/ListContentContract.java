package gank.hyx.com.gank.ui.main.goods.list_content;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface ListContentContract {

    interface View extends BaseView<Presenter> {

        void gotoGoodsDetail();

        // TODO: 2017/8/2 模型还没确定 需要看api
        void refresh();

        // TODO: 2017/8/2 同上
        void loadMore();

    }

    interface Presenter extends BasePresenter {
        //view层确定调用刷新的时机，并把行为传到presenter，presenter将数据找到之后，将数据返回到view层进行加载
        void prepareRefresh();

        void prepareLoadMore();

        void prepareGoodsDetail();

    }

}
