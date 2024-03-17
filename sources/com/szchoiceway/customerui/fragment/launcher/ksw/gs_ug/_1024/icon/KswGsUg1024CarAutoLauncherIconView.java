package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswGsUg1024CarAutoLauncherIconView extends KswGsUg1024BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnCarplay;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_common_gs_ug_1024_b3_carplay;
    }

    public KswGsUg1024CarAutoLauncherIconView(Context context) {
        super(context);
    }

    public KswGsUg1024CarAutoLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1024CarAutoLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_carplay);
    }
}
