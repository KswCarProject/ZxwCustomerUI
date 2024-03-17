package com.szchoiceway.customerui.fragment.launcher.ksw.mbux_1024.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswMbux1024SettingsLauncherIconView extends KswMbux1024BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnSettins;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_mbux_1024x600_main_set;
    }

    public KswMbux1024SettingsLauncherIconView(Context context) {
        super(context);
    }

    public KswMbux1024SettingsLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswMbux1024SettingsLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getContext().getString(R.string.lb_settings);
    }

    /* access modifiers changed from: protected */
    public String getSubtitle() {
        return getContext().getString(R.string.lb_set_system);
    }
}
