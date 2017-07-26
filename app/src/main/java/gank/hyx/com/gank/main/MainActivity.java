package gank.hyx.com.gank.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.application_extra.view.NoScrollViewPager;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.mainActivity_LinearLayout_title)
    LinearLayout mainActivity_LinearLayout_title;
    @BindView(R.id.mainActivity_NoScrollViewPager)
    NoScrollViewPager mainActivity_NoScrollViewPager;
    @BindView(R.id.mainActivity_PageBottomTabLayout_bottom)
    PageBottomTabLayout mainActivity_PageBottomTabLayout_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        //这个用不了
    }

    @Override
    public void gotoSearch() {

    }

    @Override
    public void gotoGoods() {

    }

    @Override
    public void gotoPresent() {

    }

    @Override
    public void gotoMy() {

    }
}
