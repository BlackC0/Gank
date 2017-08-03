package gank.hyx.com.gank.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.network.Request;
import gank.hyx.com.gank.network.RetrofitResponseHelper;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.ui.BaseActivity;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.ui.main.goods.GoodsFragment;
import gank.hyx.com.gank.ui.main.goods.GoodsPresenter;
import gank.hyx.com.gank.ui.main.my.MyFragment;
import gank.hyx.com.gank.ui.main.present.PresentFragment;
import gank.hyx.com.gank.view.NoScrollViewPager;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainActivity_NoScrollViewPager)
    NoScrollViewPager mainActivity_NoScrollViewPager;
    @BindView(R.id.mainActivity_PageBottomTabLayout_bottom)
    PageBottomTabLayout mainActivity_PageBottomTabLayout_bottom;

    private MainPagerAdapter adapter;
    private MainActivity mActivity;
    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivity = this;
        initView();
    }

    private void initView() {
        GoodsFragment goodsFragment = new GoodsFragment();
        MyFragment myFragment = new MyFragment();
        PresentFragment presentFragment = new PresentFragment();
        fragmentList.add(goodsFragment);
        fragmentList.add(myFragment);
        fragmentList.add(presentFragment);

        new GoodsPresenter(goodsFragment,mActivity);

        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList, this);
        mainActivity_NoScrollViewPager.setNoScroll(true);
        mainActivity_NoScrollViewPager.setAdapter(adapter);

        NavigationController navigationController = mainActivity_PageBottomTabLayout_bottom.material()
                .addItem(R.mipmap.home_page, "干货", getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.present, "福利", getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.my, "个人", getResources().getColor(R.color.toolbar_bg))
                .build();

        navigationController.setupWithViewPager(mainActivity_NoScrollViewPager);
    }

    //测试api
    private void test() {
        Request request = mActivity.getRetrofit(Constant.CommonDataUrl).create(Request.class);
        Call<JsonObject> info = request.getCommonData("all", 10, 1);
        info.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RetrofitResponseHelper rh = new RetrofitResponseHelper(response);
                CommonData data = new Gson().fromJson(response.body(), CommonData.class);
                data.toString();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
            }
        });
    }
}
