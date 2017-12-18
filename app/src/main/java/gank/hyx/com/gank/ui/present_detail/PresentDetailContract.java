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

        void send(String path);

        void avatarSetting();

        void download(String path);

        void collection(boolean state);

    }

    interface Presenter extends BasePresenter {

        void prepareBack();

        void prepareSend(String imgUrl);

        void prepareAvatarSetting(String imgUrl);

        void prepareDownload(String desc, String imgUrl);

        void prepareCollection(String desc, String imgUrl);

    }

}
