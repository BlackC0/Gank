package gank.hyx.com.gank.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.application_extra.view.NoScrollViewPager;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.mainActivity_NoScrollViewPager)
    NoScrollViewPager mainActivity_NoScrollViewPager;
    @BindView(R.id.mainActivity_PageBottomTabLayout_bottom)
    PageBottomTabLayout mainActivity_PageBottomTabLayout_bottom;

    private MainContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new MainPresenter(this);
        mainActivity_PageBottomTabLayout_bottom.material()
                .addItem(R.mipmap.home_page,"首页",getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.present, "福利",getResources().getColor(R.color.toolbar_bg))
                .addItem(R.mipmap.my, "个人",getResources().getColor(R.color.toolbar_bg))
                .build();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void gotoGoods() {
        mainActivity_NoScrollViewPager.setCurrentItem(0);
    }

    @Override
    public void gotoPresent() {
        mainActivity_NoScrollViewPager.setCurrentItem(1);
    }

    @Override
    public void gotoMy() {
        mainActivity_NoScrollViewPager.setCurrentItem(2);
    }
}
