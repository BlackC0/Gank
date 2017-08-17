package gank.hyx.com.gank.ui.main.goods.list_content;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2016/9/13.
 */
public class ListContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private String tabName;

    private LayoutInflater layoutInflater;
    private CommonData data;
    private Activity activity;
    private RecyclerViewListClickListener mItemClickListener;


    public ListContentAdapter(Activity activity, String tabName) {
        this.activity = activity;
        this.tabName = tabName;
        this.layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Constant.ListContent_sp1.equals(tabName)){
            return new HomePageViewHolder(layoutInflater.inflate(R.layout.fragment_list_content_item_all, parent, false), mItemClickListener);
        } else {
            return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_list_content_item, parent, false), mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {

        }
    }

    public static class HomePageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewListClickListener mListener;

        public HomePageViewHolder(View itemView, RecyclerViewListClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        /**
         * 点击监听
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }

    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewListClickListener mListener;

        public NormalViewHolder(View itemView, RecyclerViewListClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        /**
         * 点击监听
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }

    }

    @Override
    public int getItemCount() {
        return data.getResults().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * adapter 设置按钮监听
     *
     * @param listener
     */
    public void setOnItemClickListener(RecyclerViewListClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setData(CommonData data) {
        this.data = data;
    }
}
