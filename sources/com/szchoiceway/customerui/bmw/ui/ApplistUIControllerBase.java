package com.szchoiceway.customerui.bmw.ui;

import android.content.Context;

public abstract class ApplistUIControllerBase implements AppListUIControllerIterface {
    public Context mContext;

    public ApplistUIControllerBase(Context context) {
        this.mContext = context;
    }
}
