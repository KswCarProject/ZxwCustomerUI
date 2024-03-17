package com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswMbux1024AppLauncherIconView extends KswMbux1024BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnApps;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_mbux_1024x600_main_app;
    }

    public KswMbux1024AppLauncherIconView(Context context) {
        super(context);
    }

    public KswMbux1024AppLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswMbux1024AppLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getContext().getString(R.string.lbl_applist);
    }

    /* access modifiers changed from: protected */
    public String getSubtitle() {
        return getContext().getString(R.string.lb_apps_message);
    }
}
