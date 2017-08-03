package gank.hyx.com.gank.ui.edit_list_content;

import android.os.Bundle;

import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseActivity;

public class EditListsContentActivity extends BaseActivity {

    private EditListsContentActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lists_content);
        ButterKnife.bind(this);
        mActivity = this;
        initView();
    }

    private void initView() {
    }

}
