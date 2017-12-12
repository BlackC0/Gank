package gank.hyx.com.gank.view;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MyPopupWindows extends PopupWindow {

    private Activity activity;

    public MyPopupWindows(View view, Activity activity) {
        super(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        this.activity = activity;
    }

    public MyPopupWindows(View view, Activity activity, int width, int height) {
        super(view, width, height);
        this.activity = activity;
    }

    @Override
    public void dismiss() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1.0f; // 0.0-1.0
        activity.getWindow().setAttributes(lp);

        super.dismiss();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.8f; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void showAsDropDown(View parent, int x, int y) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.8f; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
        super.showAsDropDown(parent, x, y);
    }

}

