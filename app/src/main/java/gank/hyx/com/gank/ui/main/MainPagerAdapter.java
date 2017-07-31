package gank.hyx.com.gank.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import gank.hyx.com.gank.ui.BaseFragment;

/**
 * Created by Black.C on 2016/9/12.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private FragmentManager fm;
    private ArrayList<BaseFragment> fragmentList;

    public MainPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragmentList, Context context) {
        super(fm);
        this.fm = fm;
        this.context = context;
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
