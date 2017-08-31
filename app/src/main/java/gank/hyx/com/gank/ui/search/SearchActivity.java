package gank.hyx.com.gank.ui.search;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.tool.ReavalUtils;
import gank.hyx.com.gank.ui.BaseActivity;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    @BindView(R.id.searchActivity_FloatingActionButton)
    FloatingActionButton searchActivity_FloatingActionButton;
    @BindView(R.id.searchActivity_editText)
    EditText searchActivity_editText;
    @BindView(R.id.searchActivity_Toolbar)
    Toolbar searchActivity_Toolbar;
    @BindView(R.id.searchActivity_FrameLayout_container)
    FrameLayout searchActivity_FrameLayout_container;
    @BindView(R.id.searchActivity_RelativeLayout_container)
    FrameLayout searchActivity_RelativeLayout_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation();
            setupExitAnimation();
        } else {
        }

    }

    private void initViews() {

    }

    private void initContainer() {

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                searchActivity_FloatingActionButton.post(new Runnable() {
                    @Override
                    public void run() {
                        searchActivity_FloatingActionButton.setVisibility(View.INVISIBLE);
                    }
                });

                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        ReavalUtils.animateRevealShow(
                this, searchActivity_RelativeLayout_container,
                searchActivity_FloatingActionButton.getWidth() / 2, R.color.white,
                new ReavalUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                    }

                    @Override
                    public void onRevealShow() {

                    }

                    @Override
                    public void onRevealStart() {
                        searchActivity_Toolbar.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    // 退出动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fadeTranslation = new Fade();
        getWindow().setReturnTransition(fadeTranslation);
        fadeTranslation.setDuration(500);
    }


    @Override
    public void onBackPressed() {
        searchActivity_FloatingActionButton.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

    @OnClick({R.id.searchActivity_FloatingActionButton, R.id.searchActivity_LinearLayout_back, R.id.searchActivity_textView_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchActivity_FloatingActionButton:
                break;
            case R.id.searchActivity_LinearLayout_back:
                break;
            case R.id.searchActivity_textView_search:
                break;
        }
    }

    @Override
    public void setPresenter(SearchContract.Presenter mPresenter) {

    }

    @Override
    public void back() {

    }

    @Override
    public void search() {

    }
}
