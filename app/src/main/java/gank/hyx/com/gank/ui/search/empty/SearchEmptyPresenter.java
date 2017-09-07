package gank.hyx.com.gank.ui.search.empty;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2017/7/25.
 */

public class SearchEmptyPresenter implements SearchEmptyContract.Presenter {

    private final SearchEmptyContract.View mView;
    private SharedPreferences listContentSP;
    private SharedPreferences historySP;
    private ArrayList<String> selectOptions = new ArrayList<>();
    private ArrayList<String> historyOptions = new ArrayList<>();
    private OnSelectedListener listener;

    public SearchEmptyPresenter(SearchEmptyContract.View mView, Activity activity, OnSelectedListener listener) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.listener = listener;
        this.listContentSP = activity.getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);
        this.historySP = activity.getSharedPreferences(Constant.TableName2, Context.MODE_PRIVATE);
    }

    @Override
    public void start() {
        selectOptions.add(Constant.ListContent_sp1);
        selectOptions.add(Constant.ListContent_sp2);
        selectOptions.add(Constant.ListContent_sp3);
        selectOptions.add(Constant.ListContent_sp4);
        selectOptions.add(Constant.ListContent_sp5);
        selectOptions.add(Constant.ListContent_sp6);
        selectOptions.add(Constant.ListContent_sp7);
        selectOptions.add(Constant.ListContent_sp8);

        HashMap<String, Long> historyMap = (HashMap<String, Long>) historySP.getAll();
        Set<Map.Entry<String, Long>> historySet = historyMap.entrySet();
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(historySet);
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o1.getValue() < o2.getValue())
                    return -1;
                else if (o1.getValue() > o2.getValue())
                    return 1;
                return 0;
            }
        });

        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Long> entry = list.get(i);
            historyOptions.add(entry.getKey());
        }
        mView.initView(selectOptions, historyOptions);
    }

    @Override
    public void selecting(String text) {
        listener.onSelected(text);
    }

    @Override
    public void onHistorySearch(String text) {
        listener.onHistorySearch(text);
    }
}
