package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswGsUg1024VideoLauncherIconView extends KswGsUg1024BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnVideo;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_common_gs_ug_1024_b1_video;
    }

    public KswGsUg1024VideoLauncherIconView(Context context) {
        super(context);
    }

    public KswGsUg1024VideoLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1024VideoLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_video);
    }
}
