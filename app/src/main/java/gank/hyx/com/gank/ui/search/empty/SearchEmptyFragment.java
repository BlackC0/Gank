package gank.hyx.com.gank.ui.search.empty;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseFragment;

public class SearchEmptyFragment extends BaseFragment {

    private View rootView;
    private Activity mActivity;
    private OnSelectedListener listener;


    public static SearchEmptyFragment newInstance(OnSelectedListener listener) {
        Bundle args = new Bundle();
        SearchEmptyFragment fragment = new SearchEmptyFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

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