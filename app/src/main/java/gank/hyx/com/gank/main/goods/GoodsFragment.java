package gank.hyx.com.gank.main.goods;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gank.hyx.com.gank.BaseFragment;
import gank.hyx.com.gank.R;

public class GoodsFragment extends BaseFragment {

    private View rootView;
    private Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragement_goods, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        return rootView;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}