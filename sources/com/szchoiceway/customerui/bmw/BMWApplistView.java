package com.szchoiceway.customerui.bmw;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.szchoiceway.customerui.bmw.ui.ApplistUIController;
import com.szchoiceway.customerui.bmw.ui.ApplistUIControllerBase;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.customerui.drag.DragAppListInfo;
import com.szchoiceway.customerui.fragment.AppListView;
import com.szchoiceway.zxwlib.focus.FocusObserver;

public class BMWApplistView extends AppListView {
    private static final String TAG = "BMWApplistView";
    BMWApplistCallBack mBMWApplistCallBack;
    private ApplistUIControllerBase mUIController;

    public interface BMWApplistCallBack {
        void addItem(DragAppInfo dragAppInfo);
    }

    /* access modifiers changed from: protected */
    public void initEvent(View view) {
    }

    public BMWApplistView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public int inflateLayout() {
        ApplistUIController applistUIController = new ApplistUIController(this.mContext);
        this.mUIController = applistUIController;
        return applistUIController.getLayout();
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.init(view, this);
        }
    }

    public void refreshAppList() {
        super.refreshAppList();
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.setData(DragAppListInfo.mAppFragmentItemDetailsMap);
        }
    }

    public void setAppsFocusMove(int i, boolean z) {
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.setAppsFocusMove(i, z);
        }
    }

    public void setFocusObserver(FocusObserver focusObserver) {
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.setFocusObserver(focusObserver);
        }
    }

    public void setInEditMode(boolean z) {
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.setInEditMode(z);
        }
    }

    public void addItem(DragAppInfo dragAppInfo) {
        BMWApplistCallBack bMWApplistCallBack = this.mBMWApplistCallBack;
        if (bMWApplistCallBack != null) {
            bMWApplistCallBack.addItem(dragAppInfo);
        }
    }

    public void setBMWApplistCallBack(BMWApplistCallBack bMWApplistCallBack) {
        this.mBMWApplistCallBack = bMWApplistCallBack;
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Log.i(TAG, "onVisibilityChanged: " + i);
        ApplistUIControllerBase applistUIControllerBase = this.mUIController;
        if (applistUIControllerBase != null) {
            applistUIControllerBase.onVisibilityChanged(i);
        }
    }
}
