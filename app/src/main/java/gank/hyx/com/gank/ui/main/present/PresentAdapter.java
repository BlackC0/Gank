package gank.hyx.com.gank.ui.main.present;

import android.app.Activity;
import android.content.Context;
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
public class PresentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<CommonData.Data> dataList = new ArrayList<>();
    private Activity activity;
    private Context context;
    private RecyclerViewListClickListener mItemClickListener;
    private final static Object lock = new Object();
    private int vhCount = 0;


    public PresentAdapter(Activity activity) {
        this.activity = activity;
        this.context = activity;
        this.layoutInflater = LayoutInflater.from(activity);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        synchronized (lock){
//            vhCount++;
//        }
        return new NormalViewHolder(layoutInflater.inflate(R.layout.fragment_present_list_item, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            final NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
//            synchronized (lock){
//                if (vhCount >= 20) {
//                    vhCount--;
////                    normalViewHolder.setIsRecyclable(false);
//                }
//            }

            CommonData.Data data = dataList.get(position);

            final int imgPosition = position;
            if (data.getImageHeight_local() == -1) {
                Glide.with(activity)
                        .load(data.getUrl() + "?imageView2/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                            @Override
                            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                dataList.get(imgPosition).setImageHeight_local(bitmap.getHeight());
                                bitmap.recycle();

                                Glide.with(activity)
                                        .load(dataList.get(imgPosition).getUrl() + "?imageView2/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                                        .skipMemoryCache(true)
                                        .crossFade()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .override((DisplayUtil.getScreenWidth(activity) / 2), dataList.get(imgPosition).getImageHeight_local())
                                        .into(normalViewHolder.presentFragment_ImageView_item);

                            }

                        });
                return;
            }

            Glide.with(activity)
                    .load(data.getUrl() + "?imageView2/0/w/" + (DisplayUtil.getScreenWidth(activity) / 2))
                    .skipMemoryCache(true)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override((DisplayUtil.getScreenWidth(activity) / 2), dataList.get(position).getImageHeight_local())
                    .into(normalViewHolder.presentFragment_ImageView_item);

        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.presentFragment_ImageView_item)
        ImageView presentFragment_ImageView_item;
        private RecyclerViewListClickListener mListener;

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
