package com.szchoiceway.customerui.bmw.ui;

import android.view.View;
import com.szchoiceway.customerui.bmw.BMWApplistView;
import com.szchoiceway.customerui.drag.DragAppInfo;
import com.szchoiceway.zxwlib.focus.FocusObserver;
import java.util.HashMap;

public interface AppListUIControllerIterface {
    int getLayout();

    void init(View view, BMWApplistView bMWApplistView);

    void onVisibilityChanged(int i);

    void setAppsFocusMove(int i, boolean z);

    void setData(HashMap<String, DragAppInfo> hashMap);

    void setFocusObserver(FocusObserver focusObserver);

    void setInEditMode(boolean z);

    void unInit();
}
