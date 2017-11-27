package gank.hyx.com.gank.ui.main.goods.list_content;

import java.util.ArrayList;

import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface ListContentContract {

    interface View extends BaseView<Presenter> {

        void gotoGoodsDetail(String url,String imgUrl,String desc);

        void refresh(CommonData data);

        void loadMore(ArrayList<CommonData.Data> dataList);

    }

    interface Presenter extends BasePresenter {
        //view层确定调用刷新的时机，并把行为传到presenter，presenter将数据找到之后，将数据返回到view层进行加载
        void prepareRefresh();

        void prepareLoadMore();

        void prepareGoodsDetail(int position);

    }

}
