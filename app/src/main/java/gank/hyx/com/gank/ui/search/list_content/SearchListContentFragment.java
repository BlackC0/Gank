package gank.hyx.com.gank.ui.search.list_content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.SearchData;
import gank.hyx.com.gank.ui.BaseFragment;
import gank.hyx.com.gank.ui.goods_detail.GoodsDetailActivity;

public class SearchListContentFragment extends BaseFragment implements SearchListContentContract.View {

    @BindView(R.id.searchListContentFragment_RecyclerView)
    RecyclerView searchListContentFragment_RecyclerView;
    @BindView(R.id.searchListContentFragment_SpinKitView_loading)
    SpinKitView searchListContentFragment_SpinKitView_loading;
    @BindView(R.id.searchListContentFragment_RelativeLayout_loadingBackground)
    RelativeLayout searchListContentFragment_RelativeLayout_loadingBackground;
    @BindView(R.id.searchListContentFragment_TwinklingRefreshLayout)
    TwinklingRefreshLayout searchListContentFragment_TwinklingRefreshLayout;
    private View rootView;
    private Activity mActivity;
    private LinearLayoutManager mLayoutManager;
    private SearchData data = new SearchData();
    private SearchListContentAdapter adapter;
    private SearchListContentContract.Presenter mPresenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search_list_content, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        Sprite drawable = new Circle();
        searchListContentFragment_SpinKitView_loading.setIndeterminateDrawable(drawable);
        searchListContentFragment_TwinklingRefreshLayout.setOnRefreshListener(refreshAdapter);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchListContentFragment_RecyclerView.setLayoutManager(mLayoutManager);
        adapter = new SearchListContentAdapter(mActivity);
        adapter.setOnItemClickListener(new RecyclerViewListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                gotoGoodsDetail(data.getResults().get(position).getUrl(), "", data.getResults().get(position).getDesc());
            }
        });
        searchListContentFragment_RecyclerView.setAdapter(adapter);
        searchListContentFragment_RelativeLayout_loadingBackground.setVisibility(View.VISIBLE);
        searchListContentFragment_TwinklingRefreshLayout.setEnableRefresh(false);
        searchListContentFragment_TwinklingRefreshLayout.setEnableLoadmore(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void setPresenter(SearchListContentContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void refresh(SearchData data) {
        if (data != null) {
            this.data = data;
            adapter.setData(data.getResults());
            adapter.notifyDataSetChanged();
        }
        searchListContentFragment_TwinklingRefreshLayout.finishRefreshing();
    }

    @Override
    public void loadMore(SearchData data) {
        ArrayList<SearchData.Data> legacy = this.data.getResults();
        legacy.addAll(data.getResults());
        this.data.setResults(legacy);
        adapter.setData(this.data.getResults());
        adapter.notifyDataSetChanged();
        searchListContentFragment_TwinklingRefreshLayout.finishLoadmore();
    }

    @Override
    public void appearLoading() {
        if (searchListContentFragment_RelativeLayout_loadingBackground != null) {
            searchListContentFragment_RelativeLayout_loadingBackground.setVisibility(View.VISIBLE);
            searchListContentFragment_TwinklingRefreshLayout.setEnableRefresh(false);
            searchListContentFragment_TwinklingRefreshLayout.setEnableLoadmore(false);
        }
    }

    public void gotoGoodsDetail(String url, String imgUrl, String desc) {
        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("desc", desc);
        startActivity(intent);
    }

    @Override
    public void init(SearchData data) {
        searchListContentFragment_RelativeLayout_loadingBackground.setVisibility(View.GONE);
        searchListContentFragment_TwinklingRefreshLayout.setEnableRefresh(true);
        searchListContentFragment_TwinklingRefreshLayout.setEnableLoadmore(true);
        if (data != null) {
            this.data = data;
            adapter.setData(data.getResults());
            adapter.notifyDataSetChanged();
        }
    }
}