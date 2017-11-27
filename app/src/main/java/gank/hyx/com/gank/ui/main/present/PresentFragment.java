package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.ui.BaseFragment;

public class PresentFragment extends BaseFragment implements PresentContract.View {

    @BindView(R.id.presentFragment_RecyclerView)
    RecyclerView presentFragment_RecyclerView;
    @BindView(R.id.presentFragment_TwinklingRefreshLayout)
    TwinklingRefreshLayout presentFragment_TwinklingRefreshLayout;
    private View rootView;
    private Activity mActivity;
    private PresentContract.Presenter mPresenter;
    private PresentAdapter adapter;


    private boolean isLoadingMore = false;

    private RefreshListenerAdapter refreshAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            mPresenter.prepareRefresh();
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            if (!isLoadingMore) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadingMore = true;
                        mPresenter.prepareLoadMore();
                    }
                }, 500);
            }
        }

        @Override
        public void onFinishLoadMore() {
            isLoadingMore = false;
            super.onFinishLoadMore();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_present, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onResume() {
        mPresenter.start();
        super.onResume();
    }

    private void initView() {
        presentFragment_TwinklingRefreshLayout.setOnRefreshListener(refreshAdapter);
        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        presentFragment_RecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        adapter = new PresentAdapter(mActivity, new RecyclerViewListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.preparePresentDetail(position);
            }
        });
//        //设置item之间的间隔
        presentFragment_RecyclerView.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void gotoPresentDetail(String imgUrl, String desc) {

    }

    @Override
    public void refresh(CommonData data) {
        adapter.clearData();
        adapter.addData(data.getResults());
        presentFragment_TwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void loadMore(ArrayList<CommonData.Data> dataList) {
        adapter.addData(dataList);
        presentFragment_TwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void setPresenter(PresentContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }
}