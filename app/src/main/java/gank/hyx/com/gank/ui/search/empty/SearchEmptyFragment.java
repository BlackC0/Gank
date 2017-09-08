package gank.hyx.com.gank.ui.search.empty;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gank.hyx.com.gank.R;
import gank.hyx.com.gank.ui.BaseFragment;

public class SearchEmptyFragment extends BaseFragment implements SearchEmptyContract.View {

    @BindView(R.id.searchEmptyFragment_flexboxLayout_search_range)
    FlexboxLayout searchEmptyFragment_flexboxLayout_search_range;
    @BindView(R.id.searchEmptyFragment_textView_clear)
    TextView searchEmptyFragment_textView_clear;
    @BindView(R.id.searchEmptyFragment_flexboxLayout_history)
    FlexboxLayout searchEmptyFragment_flexboxLayout_history;
    private SearchEmptyContract.Presenter mPresenter;
    private ArrayList<TextView> historyTexts = new ArrayList<>();
    private View rootView;
    private Activity mActivity;
    private ArrayList<String> historyOptions = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false);
        mActivity = getActivity();
        ButterKnife.bind(this, rootView);
        mPresenter.start();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void initView(ArrayList<String> selectOptions, ArrayList<String> historyOptions) {
        this.historyOptions = historyOptions;
        for (int i = 0; i < selectOptions.size(); i++) {
            if (selectOptions.get(i).equals("hasInit")) {
                continue;
            }
            searchEmptyFragment_flexboxLayout_search_range.setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
            final TextView fragment_empty_search_option_item = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_empty_search_option_item, searchEmptyFragment_flexboxLayout_search_range, false);
            fragment_empty_search_option_item.setText(selectOptions.get(i));
            if (i == 0) {
                fragment_empty_search_option_item.setSelected(true);
            }
            fragment_empty_search_option_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < searchEmptyFragment_flexboxLayout_search_range.getChildCount(); i++) {
                        searchEmptyFragment_flexboxLayout_search_range.getChildAt(i).setSelected(false);
                        searchEmptyFragment_flexboxLayout_search_range.getChildAt(i).setClickable(true);
                    }
                    fragment_empty_search_option_item.setSelected(true);
                    fragment_empty_search_option_item.setClickable(false);
                    mPresenter.selecting(fragment_empty_search_option_item.getText().toString());
                }
            });
            searchEmptyFragment_flexboxLayout_search_range.addView(fragment_empty_search_option_item);
        }

        for (int i = 0; i < historyOptions.size(); i++) {
            searchEmptyFragment_flexboxLayout_history.setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
            final TextView fragment_empty_search_option_item = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_empty_search_option_item, searchEmptyFragment_flexboxLayout_history, false);

            fragment_empty_search_option_item.setText(historyOptions.get(i).split("/")[1]);
            fragment_empty_search_option_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < searchEmptyFragment_flexboxLayout_history.getChildCount(); i++) {
                        searchEmptyFragment_flexboxLayout_history.getChildAt(i).setSelected(false);
                    }
                    fragment_empty_search_option_item.setSelected(true);
                    mPresenter.onHistorySearch(SearchEmptyFragment.this.historyOptions.get(historyTexts.indexOf(fragment_empty_search_option_item)));
                }
            });
            searchEmptyFragment_flexboxLayout_history.addView(fragment_empty_search_option_item);
            historyTexts.add(fragment_empty_search_option_item);

        }
    }

    @Override
    public void setPresenter(SearchEmptyContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }
}