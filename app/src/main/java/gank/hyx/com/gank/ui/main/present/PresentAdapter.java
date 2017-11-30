package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.DisplayUtil;

/**
 * Created by Black.C on 2016/9/13.
 */
public class PresentAdapter extends RecyclerView.Adapter {

    private ArrayList<CommonData.Data> dataList = new ArrayList<>();
    private Activity activity;
    private Context context;
    private RecyclerViewListClickListener mItemClickListener;

    public PresentAdapter(Activity activity, RecyclerViewListClickListener mItemClickListener) {
        this.activity = activity;
        this.context = activity;
        if (mItemClickListener != null) {
            this.mItemClickListener = mItemClickListener;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NormalViewHolder(LayoutInflater.from(activity).inflate(R.layout.fragment_present_list_item, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NormalViewHolder) {
            final CommonData.Data data = dataList.get(position);
            ((NormalViewHolder) holder).presentFragment_tag_view.setTag(data.getUrl());
            final NormalViewHolder normalViewHolder = (NormalViewHolder) holder;

            Glide.with(activity)
                    .load(data.getUrl() + "?imageView2/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                    .skipMemoryCache(true)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new GlideDrawableImageViewTarget(normalViewHolder.presentFragment_ImageView_item) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (normalViewHolder.presentFragment_tag_view.getTag().toString() != null
                                    && normalViewHolder.presentFragment_tag_view.getTag().toString().equals(data.getUrl())) {
                                getView().setImageDrawable(resource);
                            } else {
                                getRequest().pause();
                            }
                        }
                    });


        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.presentFragment_ImageView_item)
        ImageView presentFragment_ImageView_item;
        @BindView(R.id.presentFragment_tag_view)
        View presentFragment_tag_view;

        RecyclerViewListClickListener mListener;

        public NormalViewHolder(View itemView, RecyclerViewListClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

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
