package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.view.View;

public abstract class RecycleItemBase {
    public static final int MODE_EFFICT = 0;
    public static final int MODE_PERSONAL = 2;
    public static final int MODE_SPORT = 1;
    public int m_iModeSet = 17;

    public abstract View getSetView(Context context, boolean z);

    public abstract String getTag();

    public abstract int[] getViewSize();

    public abstract void updataFocusState(boolean z);

    public abstract void updateInfo();
}
