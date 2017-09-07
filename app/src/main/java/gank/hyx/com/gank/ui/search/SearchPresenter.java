package gank.hyx.com.gank.ui.search;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import gank.hyx.com.gank.network.Request;
import gank.hyx.com.gank.network.RetrofitResponseHelper;
import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Black.C on 2017/7/25.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View mView;
    private Activity mActivity;
    private int pager = 1;

    public SearchPresenter(SearchContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.mActivity = mActivity;
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareBack() {
        mView.back();
    }

    @Override
    public void search(String selectOption, String searchText) {
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.SearchDataUrl).create(Request.class);
        Call<JsonObject> info = request.getSearchData(searchText, selectOption, 50, pager);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    SearchData searchData = new Gson().fromJson(response.body(), SearchData.class);
                    mView.searchComplete(searchData);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
