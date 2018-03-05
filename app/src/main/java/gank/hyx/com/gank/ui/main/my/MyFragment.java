package gank.hyx.com.gank.ui.main.my;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.Constant;
import gank.hyx.com.gank.tool.DisplayUtil;
import gank.hyx.com.gank.ui.BaseFragment;

public class MyFragment extends BaseFragment implements MyContract.View {

    @BindView(R.id.myFragment_imageView_avatar)
    ImageView myFragment_imageView_avatar;
    @BindView(R.id.myFragment_textView_nickName)
    TextView myFragment_textView_nickName;
    private View rootView;
    private Activity mActivity;
    private MyContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        mPresenter.start();
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void initView(String imgUrl, String nickName) {
        //取出头像变量 以及昵称名 出来
        if (!imgUrl.equals("")) {

            Glide.with(mActivity)
                    .load(imgUrl + DisplayUtil.sizeOfImage(mActivity, 51, 51))
                    .error(R.mipmap.avatar_original)
                    .crossFade()
                    .centerCrop()
                    .into(myFragment_imageView_avatar);

        }

        myFragment_textView_nickName.setText(nickName);

        registerReceiver();

    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.AVATAR_UPDATE);

        LocalBroadcastManager.getInstance(mActivity).registerReceiver(receiver,filter);
    }

    @Override
    public void gotoMyCollection() {

    }

    @Override
    public void cleanCache() {

    }

    @Override
    public void setPresenter(MyContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @OnClick({R.id.myFragment_linearLayout_clean_cache, R.id.myFragment_linearLayout_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myFragment_linearLayout_clean_cache:
                mPresenter.prepareCleanCache();
                break;
            case R.id.myFragment_linearLayout_collection:
                mPresenter.prepareMyCollection();
                break;
        }
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constant.AVATAR_UPDATE.equals(action)){
                mPresenter.start();
            }
        }
    };

}