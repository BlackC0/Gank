package gank.hyx.com.gank.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Request {

    /**
     * 获取主页面 干货列表数据
     *
     * @param type  类型：  all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * @param count 数据量
     * @param page  页数
     * @return
     */
    @GET("{type}/{count}/{page}")
    Call<JsonObject> getCommonData(@Path("type") String type, @Path("count") int count, @Path("page") int page);


    /**
     * 搜索
     *
     * @param query    类型：  搜索的关键词
     * @param category 类型：  all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * @param count    数据量
     * @param page     页数
     * @return
     */
    @GET("query/{query}/category/{category}/count/{count}/page/{page}")
    Call<JsonObject> getSearchData(@Path("query") String query, @Path("category") String category, @Path("count") int count, @Path("page") int page);


}