package gank.hyx.com.gank.tool;

import android.os.Environment;

import java.io.File;

/**
 * Created by Black.C on 2017/7/28.
 */

public class Constant {

    //获取所有干货
    public static final String CommonDataUrl = "http://gank.io/api/data/";

    //获取搜索出来的干货
    public static final String SearchDataUrl = "http://gank.io/api/search/";

    //SharedPreferences  ListContent
    public static final String TableName1 = "ListContent";
    public static final String ListContent_sp1 = "all";
    public static final String ListContent_sp2 = "Android";
    public static final String ListContent_sp3 = "iOS";
    public static final String ListContent_sp4 = "休息视频";
    public static final String ListContent_sp5 = "拓展资源";
    public static final String ListContent_sp6 = "前端";
    public static final String ListContent_sp7 = "瞎推荐";
    public static final String ListContent_sp8 = "App";

    public static final String TableName2 = "HistoryMap";

    public static final String TableName3 = "Collection";
    public static final String Collection_sp1 = "avatar";
    public static final String Collection_sp2 = "nickName";


    public static final int EDIT_LISTS_CONTENT = 10000;

    public static final String shareImg = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "shareImg" + File.separator;

    public static final String AVATAR_UPDATE = "gank.hyx.com.gank.AVATAR_UPDATE";
}
