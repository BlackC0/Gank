package gank.hyx.com.gank.ui.search.list_content;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import gank.hyx.com.gank.network.Request;
import gank.hyx.com.gank.network.RetrofitResponseHelper;
import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.ui.search.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Black.C on 2017/7/25.
 */

public class SearchListContentPresenter implements SearchListContentContract.Presenter, OnSearchListener {

    private final SearchListContentContract.View mView;
    private Activity mActivity;
    private String selectOption;
    private String searchText;
    private int pager = 1;

    public SearchListContentPresenter(SearchListContentContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
        mView.setPresenter(this);
        ((SearchActivity) mActivity).setSearchCompleteListener(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareRefresh() {
        pager = 1;
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.SearchDataUrl).create(Request.class);
        Call<JsonObject> info = request.getSearchData(searchText, selectOption, 50, pager);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    SearchData searchData = new Gson().fromJson(response.body(), SearchData.class);
                    mView.refresh(searchData);
                    return;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void prepareLoadMore() {
        pager++;
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.SearchDataUrl).create(Request.class);
        Call<JsonObject> info = request.getSearchData(searchText, selectOption, 50, pager);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    SearchData searchData = new Gson().fromJson(response.body(), SearchData.class);
                    mView.loadMore(searchData);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onSearchComplete(SearchData data, String selectOption, String searchText) {
        this.selectOption = selectOption;
        this.searchText = searchText;
        this.mView.init(data);
    }

    @Override
    public void appearLoading() {
        mView.appearLoading();
    }
}
