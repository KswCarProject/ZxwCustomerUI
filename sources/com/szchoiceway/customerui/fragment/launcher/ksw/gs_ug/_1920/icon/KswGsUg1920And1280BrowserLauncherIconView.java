package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswGsUg1920And1280BrowserLauncherIconView extends KswGsUg1920And1280BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnBrowser;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_common_gs_ug_1920_d1_browser;
    }

    public KswGsUg1920And1280BrowserLauncherIconView(Context context) {
        super(context);
    }

    public KswGsUg1920And1280BrowserLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920And1280BrowserLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_Browser);
    }
}
