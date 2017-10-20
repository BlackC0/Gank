package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.R;

public class PresentFragment extends BaseFragment implements PresentContract.View{

    private View rootView;
    private Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_present, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void gotoPresentDetail(String imgUrl, String desc) {

    }

    @Override
    public void refresh(String imgUrl) {

    }

    @Override
    public void loadMore(String imgUrl) {

    }

    @Override
    public void setPresenter(PresentContract.Presenter mPresenter) {

    }
}