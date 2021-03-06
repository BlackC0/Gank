package gank.hyx.com.gank.ui.main.goods.list_content;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haozhang.lib.SlantedTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.tool.DisplayUtil;

/**
 * Created by Black.C on 2016/9/13.
 */
public class ListContentAdapter extends RecyclerView.Adapter {

    private String tabName;
    private ArrayList<CommonData.Data> dataList = new ArrayList<>();
    private Activity activity;
    private RecyclerViewListClickListener mItemClickListener;

    public ListContentAdapter(Activity activity, String tabName, RecyclerViewListClickListener mItemClickListener) {
        this.activity = activity;
        this.tabName = tabName;
        if (mItemClickListener != null) {
            this.mItemClickListener = mItemClickListener;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (Constant.ListContent_sp1.equals(tabName)) {
            return new HomePageViewHolder(LayoutInflater.from(activity).inflate(R.layout.fragment_list_content_item_all, parent, false), mItemClickListener);
        } else {
            return new NormalViewHolder(LayoutInflater.from(activity).inflate(R.layout.fragment_list_content_item, parent, false), mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HomePageViewHolder) {
            HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;

            //设置类型
            CommonData.Data data = dataList.get(position);
            String type = data.getType();
            homePageViewHolder.fragment_SlantedTextView_list_content_item_tag.setText(type);

            //发布时间
            String publishTime = data.getPublishedAt().substring(0, 10);
            String lastTime = null;
            homePageViewHolder.fragment_linearLayout_list_content_item_time.setVisibility(View.GONE);
            if (position != 0) {
                lastTime = dataList.get(position - 1).getPublishedAt().substring(0, 10);
            }
            if (lastTime == null || !lastTime.equals(publishTime)) {
                homePageViewHolder.fragment_linearLayout_list_content_item_time.setVisibility(View.VISIBLE);
            }
            homePageViewHolder.fragment_textView_list_content_item_time.setText(publishTime);

            //设置图片
            homePageViewHolder.fragment_linearLayout_list_content_item_avatar.setVisibility(View.GONE);
            if (data.getImages().size() != 0) {
                homePageViewHolder.fragment_linearLayout_list_content_item_avatar.setVisibility(View.VISIBLE);
                Glide.with(activity).
                        load(data.getImages().get(0) + DisplayUtil.sizeOfImageforFullWidth(activity, 160))
                        .crossFade()
                        .centerCrop()
                        .into(homePageViewHolder.fragment_imageView_list_content_item_avatar);
            }

            homePageViewHolder.fragment_textView_list_content_item_title.setText(data.getDesc());
            homePageViewHolder.fragment_textView_list_content_item_author.setText(data.getWho());
            if ("".equals(data.getWho())) {
                homePageViewHolder.fragment_imageView_list_content_item_author.setVisibility(View.GONE);
            } else {
                homePageViewHolder.fragment_imageView_list_content_item_author.setVisibility(View.VISIBLE);
            }
        }


        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            CommonData.Data data = dataList.get(position);
            normalViewHolder.fragment_textView_list_content_item_title.setText(data.getDesc());

            normalViewHolder.fragment_imageView_list_content_item_avatar.setVisibility(View.GONE);
            if (data.getImages().size() != 0) {
                normalViewHolder.fragment_imageView_list_content_item_avatar.setVisibility(View.VISIBLE);
                Glide.with(activity)
                        .load(data.getImages().get(0) + "?imageView/0/w/" + DisplayUtil.dip2px(activity, 60) + "/h/" + DisplayUtil.dip2px(activity, 60))
                        .crossFade()
                        .centerCrop()
                        .into(normalViewHolder.fragment_imageView_list_content_item_avatar);
            }

            normalViewHolder.fragment_textView_list_content_item_author.setText(data.getWho().equals("") ? data.getWho() : data.getWho() + " ·");
            String publishTime = data.getPublishedAt();
            publishTime = publishTime.replace("Z", " UTC");
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(publishTime);
                long reduce = System.currentTimeMillis() - date.getTime() + 28800000;
                reduce = reduce / 1000;
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "秒前");
                    reduce = reduce / 60;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "分前");
                    reduce = reduce / 60;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "小时前");
                    reduce = reduce / 24;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "天前");
                    reduce = reduce / 30;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "月前");
                    reduce = reduce / 12;
                }
                if (reduce > 0) {
                    normalViewHolder.fragment_textView_list_content_item_publishAt.setText(reduce + "年前");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public class HomePageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fragment_list_content_linearLayout_item_time)
        LinearLayout fragment_linearLayout_list_content_item_time;
        @BindView(R.id.fragment_list_content_textView_item_time)
        TextView fragment_textView_list_content_item_time;
        @BindView(R.id.fragment_list_content_linearLayout_item_avatar)
        LinearLayout fragment_linearLayout_list_content_item_avatar;
        @BindView(R.id.fragment_list_content_imageView_item_avatar)
        ImageView fragment_imageView_list_content_item_avatar;
        @BindView(R.id.fragment_list_content_SlantedTextView_item_tag)
        SlantedTextView fragment_SlantedTextView_list_content_item_tag;
        @BindView(R.id.fragment_list_content_textView_item_title)
        TextView fragment_textView_list_content_item_title;
        @BindView(R.id.fragment_list_content_imageView_item_author)
        ImageView fragment_imageView_list_content_item_author;
        @BindView(R.id.fragment_list_content_textView_item_author)
        TextView fragment_textView_list_content_item_author;
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

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.fragment_list_content_textView_item_title)
        TextView fragment_textView_list_content_item_title;
        @BindView(R.id.fragment_list_content_imageView_item_avatar)
        ImageView fragment_imageView_list_content_item_avatar;
        @BindView(R.id.fragment_list_content_textView_item_author)
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
        return dataList.size();
    }

    public void clearData() {
        int itemCount = dataList.size();
        dataList.clear();
        this.notifyItemRangeRemoved(0, itemCount);
    }

    public void addData(List<CommonData.Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            int startPosition = dataList.size();
            this.dataList.addAll(dataList);
            this.notifyItemRangeChanged(startPosition, dataList.size());
        }
    }

}
