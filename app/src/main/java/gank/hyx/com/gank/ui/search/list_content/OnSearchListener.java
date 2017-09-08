package gank.hyx.com.gank.ui.search.list_content;

import gank.hyx.com.gank.network.model.SearchData;

/**
 * Created by Black.C on 2017/9/8.
 */

public interface OnSearchListener {

    void onSearchComplete(SearchData data, String selectOption, String searchText);

    void appearLoading();

}
