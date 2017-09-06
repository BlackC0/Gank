package gank.hyx.com.gank.ui.search.empty;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseFragment;

public class EmptyFragment extends BaseFragment {

    private View rootView;
    private Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}