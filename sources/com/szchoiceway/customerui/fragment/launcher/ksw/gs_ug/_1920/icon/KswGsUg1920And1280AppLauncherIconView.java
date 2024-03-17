package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswGsUg1920And1280AppLauncherIconView extends KswGsUg1920And1280BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnApps;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_common_gs_ug_1920_b2_apps;
    }

    public KswGsUg1920And1280AppLauncherIconView(Context context) {
        super(context);
    }

    public KswGsUg1920And1280AppLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920And1280AppLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lbl_applist);
    }
}