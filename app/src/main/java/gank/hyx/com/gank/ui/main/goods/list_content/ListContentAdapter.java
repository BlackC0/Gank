package gank.hyx.com.gank.ui.main.goods.list_content;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haozhang.lib.SlantedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2016/9/13.
 */
public class ListContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String tabName;
    private LayoutInflater layoutInflater;
    private ArrayList<CommonData.Data> data;
    private Activity activity;
    private RecyclerViewListClickListener mItemClickListener;

    public ListContentAdapter(Activity activity, String tabName) {
        this.activity = activity;
        this.tabName = tabName;
        this.layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Constant.ListContent_sp1.equals(tabName)) {
            return new HomePageViewHolder(layoutInflater.inflate(R.layout.fragment_list_content_item_all, parent, false), mItemClickListener);
        } else {
            return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_list_content_item, parent, false), mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {

        }
        if (holder instanceof HomePageViewHolder) {

        }
    }

    public static class HomePageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fragment_textView_list_content_item_time)
        TextView fragmentTextViewListContentItemTime;
        @BindView(R.id.fragment_imageView_list_content_item_avatar)
        ImageView fragmentImageViewListContentItemAvatar;
        @BindView(R.id.fragment_SlantedTextView_list_content_item_tag)
        SlantedTextView fragmentSlantedTextViewListContentItemTag;
        @BindView(R.id.fragment_textView_list_content_item_title)
        TextView fragmentTextViewListContentItemTitle;
        @BindView(R.id.fragment_textView_list_content_item_author)
        TextView fragmentTextViewListContentItemAuthor;
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

        @BindView(R.id.fragment_textView_list_content_item_title)
        TextView fragment_textView_list_content_item_title;
        @BindView(R.id.fragment_imageView_list_content_item_avatar)
        ImageView fragment_imageView_list_content_item_avatar;
        @BindView(R.id.fragment_textView_list_content_item_author)
        TextView fragment_textView_list_content_item_author;
        @BindView(R.id.fragment_textView_list_content_item_publishAt)
        TextView fragment_textView_list_content_item_publishAt;
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
        return data.size();
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

    public void setData(ArrayList<CommonData.Data> data) {
        this.data = data;
    }
}
