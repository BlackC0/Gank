package gank.hyx.com.gank.ui.search.list_content;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.SearchData;

/**
 * Created by Black.C on 2016/9/13.
 */
public class SearchListContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater layoutInflater;
    private ArrayList<SearchData.Data> dataList = new ArrayList<>();
    private Activity activity;
    private RecyclerViewListClickListener mItemClickListener;

    public SearchListContentAdapter(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_search_list_content_item, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            SearchData.Data data = dataList.get(position);
            normalViewHolder.fragment_textView_search_list_content_item_title.setText(data.getDesc());
            normalViewHolder.fragment_textView_search_list_content_item_selectOption.setText("".equals(data.getType()) ? data.getType() : data.getType() + " ·");
            normalViewHolder.fragment_textView_search_list_content_item_author.setText("".equals(data.getWho()) ? data.getWho() : data.getWho() + " ·");
            String publishTime = data.getPublishedAt();
            publishTime = publishTime.replace("Z", " UTC");
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(publishTime);
                long reduce = System.currentTimeMillis() - date.getTime() + 8 * 1000 * 60 * 60;
                reduce = reduce / 1000;
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "秒前");
                    reduce = reduce / 60;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "分前");
                    reduce = reduce / 60;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "小时前");
                    reduce = reduce / 24;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "天前");
                    reduce = reduce / 30;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "月前");
                    reduce = reduce / 12;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_search_list_content_item_publishAt.setText(reduce + "年前");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewListClickListener mListener;
        @BindView(R.id.fragment_search_list_content_textView_item_title)
        TextView fragment_textView_search_list_content_item_title;
        @BindView(R.id.fragment_search_list_content_textView_item_selectOption)
        TextView fragment_textView_search_list_content_item_selectOption;
        @BindView(R.id.fragment_search_list_content_textView_item_author)
        TextView fragment_textView_search_list_content_item_author;
        @BindView(R.id.fragment_search_list_content_textView_item_publishAt)
        TextView fragment_textView_search_list_content_item_publishAt;

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
        return dataList.size();
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

    public void setData(ArrayList<SearchData.Data> dataList) {
        this.dataList = dataList;
    }
}
