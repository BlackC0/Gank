package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

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
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private PresentContract.Presenter mPresenter;
    private PresentAdapter adapter;

    private RefreshListenerAdapter refreshAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            mPresenter.prepareRefresh();
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            mPresenter.prepareLoadMore();
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
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        presentFragment_RecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        presentFragment_RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mStaggeredGridLayoutManager.invalidateSpanAssignments();
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(mActivity).pauseRequests();
                } else {
                    Glide.with(mActivity).resumeRequests();
                }
            }
        });
        adapter = new PresentAdapter(mActivity);
        adapter.setOnItemClickListener(new RecyclerViewListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.preparePresentDetail(position);
            }
        });
        //设置item之间的间隔
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
        adapter.setData(data.getResults());
        adapter.notifyDataSetChanged();
        presentFragment_TwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void loadMore(CommonData data, int originalSize, int increment) {
        adapter.setData(data.getResults());
        adapter.notifyDataSetChanged();
        adapter.notifyItemRangeInserted(originalSize, increment);
        presentFragment_TwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void setPresenter(PresentContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }
}