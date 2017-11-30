package gank.hyx.com.gank.ui.main.present;

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
 * Created by Black.C on 2017/7/25.
 */

public class PresentPresenter implements PresentContract.Presenter {

    private final PresentContract.View mView;
    private Activity mActivity;
    private int pager = 1;
    private CommonData data;

    public PresentPresenter(PresentContract.View mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        Request request = ((BaseActivity) mActivity).getRetrofit(Constant.CommonDataUrl).create(Request.class);
        Call<JsonObject> info = request.getCommonData("福利", 50, pager);
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
        Call<JsonObject> info = request.getCommonData("福利", 50, pager);
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
        Call<JsonObject> info = request.getCommonData("福利", 50, pager);
        info.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                if (rh.isResponseOK()) {
                    CommonData commonData = initResponseJson(response.body());
                    data.getResults().addAll(commonData.getResults());
                    mView.loadMore(commonData.getResults());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(mActivity, "网络连接出错，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void preparePresentDetail(int position) {



    }

    private CommonData initResponseJson(JsonObject responseBody) {
        CommonData commonData = new CommonData();
        commonData.setError(responseBody.get("error").getAsBoolean());
        ArrayList<CommonData.Data> dataList = new ArrayList<>();
        JsonArray jsonArray = responseBody.getAsJsonArray("results");
        if (jsonArray.size() != 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String type = jsonObject.get("type").getAsString();
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
                JsonElement publishedAt = jsonObject.get("publishedAt");
                data.setPublishedAt(publishedAt.isJsonNull() ? "" : publishedAt.getAsString());

                JsonElement source = jsonObject.get("source");
                boolean isNull = source == null || source.isJsonNull();
                data.setSource(isNull ? "" : source.getAsString());

                data.setType(type);
                JsonElement url = jsonObject.get("url");
                isNull = url == null || url.isJsonNull();
                data.setUrl(isNull ? "" : url.getAsString());

                JsonElement used = jsonObject.get("used");
                isNull = used == null || used.isJsonNull();
                data.setUsed(isNull ? false : used.getAsBoolean());

                JsonElement who = jsonObject.get("who");
                isNull = who == null || who.isJsonNull();
                data.setWho(isNull ? "" : who.getAsString());

                dataList.add(data);
            }
        }
        commonData.setResults(dataList);
        return commonData;
    }

}
