package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.listener.RecyclerViewListClickListener;
import gank.hyx.com.gank.network.model.CommonData;
import gank.hyx.com.gank.tool.DisplayUtil;

/**
 * Created by Black.C on 2016/9/13.
 */
public class PresentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<CommonData.Data> dataList = new ArrayList<>();
    private Activity activity;
    private RecyclerViewListClickListener mItemClickListener;

    public PresentAdapter(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_present_list_item, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            CommonData.Data data = dataList.get(position);
            if (data.getImageHeight_local() == -1) {
                normalViewHolder.presentFragment_ImageView_item.getLayoutParams().width = (DisplayUtil.getScreenWidth(activity) / 2);
                normalViewHolder.presentFragment_ImageView_item.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                Glide.with(activity)
                        .load(data.getUrl() + "?imageView/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(normalViewHolder.presentFragment_ImageView_item);

                Glide.with(activity)
                        .load(data.getUrl() + "?imageView/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                dataList.get(position).setImageHeight_local(bitmap.getHeight());
                            }

                        });
                return;
            }

            Glide.with(activity)
                    .load(data.getUrl() + "?imageView/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                    .override((DisplayUtil.getScreenWidth(activity) / 2), dataList.get(position).getImageHeight_local())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(normalViewHolder.presentFragment_ImageView_item);

        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.presentFragment_ImageView_item)
        ImageView presentFragment_ImageView_item;
        private final RecyclerViewListClickListener mListener;

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

    public void setData(ArrayList<CommonData.Data> dataList) {
        this.dataList = dataList;
    }
}
