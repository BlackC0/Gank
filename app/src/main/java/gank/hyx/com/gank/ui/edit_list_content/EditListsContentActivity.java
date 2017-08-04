package gank.hyx.com.gank.ui.edit_list_content;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;

public class EditListsContentActivity extends BaseActivity implements EditListsContentContract.View {

    @BindView(R.id.editListContentActivity_SwitchButton_Android)
    SwitchButton editListContentActivity_SwitchButton_Android;
    @BindView(R.id.editListContentActivity_SwitchButton_iOS)
    SwitchButton editListContentActivity_SwitchButton_iOS;
    @BindView(R.id.editListContentActivity_SwitchButton_video)
    SwitchButton editListContentActivity_SwitchButton_video;
    @BindView(R.id.editListContentActivity_SwitchButton_resource)
    SwitchButton editListContentActivity_SwitchButton_resource;
    @BindView(R.id.editListContentActivity_SwitchButton_web)
    SwitchButton editListContentActivity_SwitchButton_web;
    @BindView(R.id.editListContentActivity_SwitchButton_recommend)
    SwitchButton editListContentActivity_SwitchButton_recommend;
    @BindView(R.id.editListContentActivity_SwitchButton_app)
    SwitchButton editListContentActivity_SwitchButton_app;
    private Context mActivity;
    private EditListsContentContract.Presenter mPresenter;
    private EditListsContentContract.View mView;
    private ArrayList<SwitchButton> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lists_content);
        ButterKnife.bind(this);
        mView = this;
        mActivity = this;
        new EditListsContentPresenter(mView, mActivity);
        setResult(RESULT_CANCELED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttons.add(editListContentActivity_SwitchButton_Android);
        buttons.add(editListContentActivity_SwitchButton_iOS);
        buttons.add(editListContentActivity_SwitchButton_video);
        buttons.add(editListContentActivity_SwitchButton_resource);
        buttons.add(editListContentActivity_SwitchButton_web);
        buttons.add(editListContentActivity_SwitchButton_recommend);
        buttons.add(editListContentActivity_SwitchButton_app);
        mPresenter.start();
    }

    @Override
    public void back(boolean isChanged) {
        if (isChanged) {
            setResult(RESULT_OK);
        }
        finish();
    }

    @Override
    public void setBtnChecked(int index, boolean isChecked) {
        buttons.get(index).setChecked(isChecked);
    }

    @Override
    public void setPresenter(EditListsContentContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @OnClick({R.id.editListContentActivity_RelativeLayout_back, R.id.editListContentActivity_SwitchButton_Android, R.id.editListContentActivity_SwitchButton_iOS, R.id.editListContentActivity_SwitchButton_video, R.id.editListContentActivity_SwitchButton_resource, R.id.editListContentActivity_SwitchButton_web, R.id.editListContentActivity_SwitchButton_recommend, R.id.editListContentActivity_SwitchButton_app})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editListContentActivity_RelativeLayout_back:
                mPresenter.prepareBack();
                break;
            case R.id.editListContentActivity_SwitchButton_Android:
                mPresenter.editListContent(1, editListContentActivity_SwitchButton_Android.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_iOS:
                mPresenter.editListContent(2, editListContentActivity_SwitchButton_iOS.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_video:
                mPresenter.editListContent(3, editListContentActivity_SwitchButton_video.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_resource:
                mPresenter.editListContent(4, editListContentActivity_SwitchButton_resource.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_web:
                mPresenter.editListContent(5, editListContentActivity_SwitchButton_web.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_recommend:
                mPresenter.editListContent(6, editListContentActivity_SwitchButton_recommend.isChecked());
                break;
            case R.id.editListContentActivity_SwitchButton_app:
                mPresenter.editListContent(7, editListContentActivity_SwitchButton_app.isChecked());
                break;
        }
    }
}
