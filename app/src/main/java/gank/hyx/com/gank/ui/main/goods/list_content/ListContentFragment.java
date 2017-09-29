package gank.hyx.com.gank.ui.main.goods.list_content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.ui.goods_detail.GoodsDetailActivity;

public class ListContentFragment extends BaseFragment implements ListContentContract.View {

    @BindView(R.id.listContentFragment_RecyclerView)
    RecyclerView listContentFragment_RecyclerView;
    @BindView(R.id.listContentFragment_TwinklingRefreshLayout)
    TwinklingRefreshLayout listContentFragment_TwinklingRefreshLayout;
    private View rootView;
    private Activity mActivity;
    private String tabName;
    private ListContentContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private ListContentAdapter adapter;


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

    public static ListContentFragment newInstance(String tabName) {
        Bundle args = new Bundle();
        args.putString("tabName", tabName);
        ListContentFragment fragment = new ListContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        tabName = args.getString("tabName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_content, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        listContentFragment_TwinklingRefreshLayout.setOnRefreshListener(refreshAdapter);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listContentFragment_RecyclerView.setLayoutManager(mLayoutManager);
        adapter = new ListContentAdapter(mActivity, tabName);
        adapter.setOnItemClickListener(new RecyclerViewListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.prepareGoodsDetail(position);
            }
        });
        listContentFragment_RecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void gotoGoodsDetail(String url,String imgUrl) {
        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("imgUrl", imgUrl);
        startActivity(intent);
    }

    @Override
    public void refresh(CommonData data) {
        adapter.setData(data.getResults());
        adapter.notifyDataSetChanged();
        listContentFragment_TwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void loadMore(CommonData data) {
        adapter.setData(data.getResults());
        adapter.notifyDataSetChanged();
        listContentFragment_TwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void setPresenter(ListContentContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }
}