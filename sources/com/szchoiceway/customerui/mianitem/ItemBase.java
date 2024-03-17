package com.szchoiceway.customerui.mianitem;

import android.content.Context;
import android.view.View;

public abstract class ItemBase {
    public abstract View getRootView(Context context, int i);

    public abstract String getTag();

    public abstract int[] getViewSize();

    public abstract void setCurrentEditMode(boolean z);

    public abstract void setEditMode(boolean z);

    public abstract void updateInfo();
}
