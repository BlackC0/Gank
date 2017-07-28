package gank.hyx.com.gank.main;

import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.BaseActivity;
import gank.hyx.com.gank.BaseFragment;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.main.goods.GoodsFragment;
import gank.hyx.com.gank.main.my.MyFragment;
import gank.hyx.com.gank.main.present.PresentFragment;
import gank.hyx.com.gank.model.view.NoScrollViewPager;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class MainActivity extends BaseActivity{

    @BindView(R.id.mainActivity_NoScrollViewPager)
    NoScrollViewPager mainActivity_NoScrollViewPager;
    @BindView(R.id.mainActivity_PageBottomTabLayout_bottom)
    PageBottomTabLayout mainActivity_PageBottomTabLayout_bottom;

    private MainPagerAdapter adapter;
    private ArrayList<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        GoodsFragment goodsFragment = new GoodsFragment();
        MyFragment myFragment = new MyFragment();
        PresentFragment presentFragment = new PresentFragment();
        fragmentList.add(goodsFragment);
        fragmentList.add(myFragment);
        fragmentList.add(presentFragment);

        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList, this);
        mainActivity_NoScrollViewPager.setNoScroll(true);
        mainActivity_NoScrollViewPager.setAdapter(adapter);

        NavigationController navigationController = mainActivity_PageBottomTabLayout_bottom.material()
                .addItem(R.mipmap.home_page, "首页", getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.present, "福利", getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.my, "个人", getResources().getColor(R.color.toolbar_bg))
                .build();

        navigationController.setupWithViewPager(mainActivity_NoScrollViewPager);
    }
}
