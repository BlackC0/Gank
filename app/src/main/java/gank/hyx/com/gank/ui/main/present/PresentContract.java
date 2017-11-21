package gank.hyx.com.gank.ui.main.present;

import java.util.ArrayList;

import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface PresentContract {

    interface View extends BaseView<Presenter> {

        void gotoPresentDetail(String imgUrl, String desc);

        void refresh(CommonData data);

        void loadMore(ArrayList<CommonData.Data> dataList);

    }

    interface Presenter extends BasePresenter {

        void prepareRefresh();

        void prepareLoadMore();

        void preparePresentDetail(int position);

    }

}
