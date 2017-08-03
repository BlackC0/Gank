package gank.hyx.com.gank.ui.main.goods;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import gank.hyx.com.gank.ui.BaseFragment;

/**
 * Created by Black.C on 2016/9/12.
 */
public class GoodsPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<String> tabNames;
    private Context context;
    private FragmentManager fm;
    private ArrayList<BaseFragment> fragmentList;

    public GoodsPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragmentList, ArrayList<String> tabNames, Context context) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.fragmentList = fragmentList;
        this.tabNames = tabNames;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public String getPageTitle(int position) {
        return tabNames.get(position);
    }
}
