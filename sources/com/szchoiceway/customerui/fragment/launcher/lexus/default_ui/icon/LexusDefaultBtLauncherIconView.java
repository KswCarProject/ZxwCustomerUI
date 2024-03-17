package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class LexusDefaultBtLauncherIconView extends LexusDefaultBaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnBt;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.lexus_default_ui_main_icon_bt;
    }

    public LexusDefaultBtLauncherIconView(Context context) {
        super(context);
    }

    public LexusDefaultBtLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LexusDefaultBtLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_bt_phone);
    }
}
