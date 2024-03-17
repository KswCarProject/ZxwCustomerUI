package com.szchoiceway.customerui.bmw.ui;

import android.content.Context;

public abstract class UIControllerBase implements UIControllerIterface {
    public Context mContext;

    public UIControllerBase(Context context) {
        this.mContext = context;
    }
}
