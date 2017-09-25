package gank.hyx.com.gank.ui.main.goods.list_content;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import gank.hyx.com.gank.network.Request;
import gank.hyx.com.gank.network.RetrofitResponseHelper;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BlackC on 2017/7/25.
 */

public class ListContentPresenter implements ListContentContract.Presenter {

    private final ListContentContract.View mView;
    private final String tabName;
    private Activity mActivity;
    private int pager = 1;
    private CommonData data;

    public ListContentPresenter(ListContentContract.View mView, Activity mActivity, String tabName) {
        this.mView = mView;
        this.mActivity = mActivity;
        this.tabName = tabName;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.CommonDataUrl).create(Request.class);
        Call<JsonObject> info = request.getCommonData(tabName, 50, pager);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    data = initResponseJson(response.body());
                    mView.refresh(data);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void prepareRefresh() {
        pager = 1;
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.CommonDataUrl).create(Request.class);
        Call<JsonObject> info = request.getCommonData(tabName, 50, pager);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    data = initResponseJson(response.body());
                    mView.refresh(data);
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
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.CommonDataUrl).create(Request.class);
        Call<JsonObject> info = request.getCommonData(tabName, 50, pager);
        info.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    ArrayList<CommonData.Data> legacy = data.getResults();
                    CommonData commonData = initResponseJson(response.body());
                    legacy.addAll(commonData.getResults());
                    data.setResults(legacy);
                    mView.loadMore(data);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void prepareGoodsDetail(int position) {
        mView.gotoGoodsDetail(data.getResults().get(position).getUrl());
    }

    //将福利排除出去
    private CommonData initResponseJson(JsonObject responseBody) {
        CommonData commonData = new CommonData();
        commonData.setError(responseBody.get("error").getAsBoolean());
        ArrayList<CommonData.Data> dataList = new ArrayList<>();
        JsonArray jsonArray = responseBody.getAsJsonArray("results");
        if (jsonArray.size() != 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String type = jsonObject.get("type").getAsString();
                if ("福利".equals(type)) {
                    continue;
                }
                CommonData.Data data = new CommonData.Data();
                data.set_id(jsonObject.get("_id").getAsString());
                data.setCreatedAt(jsonObject.get("createdAt").getAsString());
                data.setDesc(jsonObject.get("desc").getAsString());
                ArrayList<String> images = new ArrayList<>();
                JsonElement element = jsonObject.get("images");
                if (element != null) {
                    JsonArray imageArray = jsonObject.get("images").getAsJsonArray();
                    for (int j = 0; j < imageArray.size(); j++) {
                        images.add(imageArray.get(j).getAsString());
                    }
                }
                data.setImages(images);
                data.setPublishedAt(jsonObject.get("publishedAt").getAsString());
                data.setSource(jsonObject.get("source").getAsString());
                data.setType(type);
                data.setUrl(jsonObject.get("url").getAsString());
                data.setUsed(jsonObject.get("used").getAsBoolean());
                JsonElement who = jsonObject.get("who");
                data.setWho(who.isJsonNull() ? "" : who.getAsString());
                dataList.add(data);
            }
        }
        commonData.setResults(dataList);
        return commonData;
    }
}
