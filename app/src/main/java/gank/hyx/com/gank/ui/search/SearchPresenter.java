package gank.hyx.com.gank.ui.search;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
    private SharedPreferences historySP;
    private SharedPreferences.Editor editor;
    private Activity mActivity;

    public SearchPresenter(SearchContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.mActivity = mActivity;
        historySP = mActivity.getSharedPreferences(Constant.TableName2, Context.MODE_PRIVATE);
        editor = historySP.edit();
    }

    @Override
    public void start() {
    }

    @Override
    public void prepareBack() {
        mView.back();
    }

    @Override
    public void search(final String selectOption, final String searchText) {

        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.SearchDataUrl).create(Request.class);
        Call<JsonObject> info = request.getSearchData(searchText, selectOption, 50, 1);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    SearchData searchData = new Gson().fromJson(response.body(), SearchData.class);
                    mView.searchComplete(searchData, selectOption, searchText);
                    editor.putLong(selectOption + "/" + searchText, System.currentTimeMillis());
                    editor.commit();
                    return;
                }
                mView.searchFailed();
                Toast.makeText(mActivity, "搜索失败，请重新重试搜索", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mView.searchFailed();
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
