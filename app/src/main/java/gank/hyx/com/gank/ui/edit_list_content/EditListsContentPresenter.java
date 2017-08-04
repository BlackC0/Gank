package gank.hyx.com.gank.ui.edit_list_content;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import gank.hyx.com.gank.tool.Constant;

/**
 * Created by Black.C on 2017/7/25.
 */

public class EditListsContentPresenter implements EditListsContentContract.Presenter {

    private final EditListsContentContract.View mView;
    private Context context;
    private SharedPreferences mSharedPreferences;
    private ArrayList<Boolean> edits = new ArrayList<>();
    private boolean isChanged = false;

    public EditListsContentPresenter(EditListsContentContract.View mView, Context context) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.mSharedPreferences = context.getSharedPreferences(Constant.TableName1, Context.MODE_PRIVATE);

    }

    @Override
    public void start() {
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp2, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp3, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp4, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp5, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp6, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp7, false));
        edits.add(mSharedPreferences.getBoolean(Constant.ListContent_sp8, false));

        mView.setBtnChecked(0, edits.get(0));
        mView.setBtnChecked(1, edits.get(1));
        mView.setBtnChecked(2, edits.get(2));
        mView.setBtnChecked(3, edits.get(3));
        mView.setBtnChecked(4, edits.get(4));
        mView.setBtnChecked(5, edits.get(5));
        mView.setBtnChecked(6, edits.get(6));

    }

    @Override
    public void editListContent(int index, boolean isEnable) {
        if (!isChanged) {
            isChanged = true;
        }
        edits.set(index, isEnable);
    }

    @Override
    public void prepareBack() {
        if (isChanged) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(Constant.ListContent_sp2, edits.get(1));
            editor.putBoolean(Constant.ListContent_sp3, edits.get(2));
            editor.putBoolean(Constant.ListContent_sp4, edits.get(3));
            editor.putBoolean(Constant.ListContent_sp5, edits.get(4));
            editor.putBoolean(Constant.ListContent_sp6, edits.get(5));
            editor.putBoolean(Constant.ListContent_sp7, edits.get(6));
            editor.putBoolean(Constant.ListContent_sp8, edits.get(7));
            editor.commit();
        }
        mView.back(isChanged);
    }
}
