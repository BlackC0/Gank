package gank.hyx.com.gank.ui.edit_list_content;

import gank.hyx.com.gank.ui.BasePresenter;
import gank.hyx.com.gank.ui.BaseView;

/**
 * Created by Black.C on 2017/7/28.
 */

public interface EditListsContentContract {

    interface View extends BaseView<Presenter> {

        void back(boolean isChanged);

        void setBtnChecked(int index,boolean isChecked);

    }

    interface Presenter extends BasePresenter {

        void editListContent(int index, boolean isEnable);

        void prepareBack();

    }

}
