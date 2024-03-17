package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class KswGsUg1920And1280DashBoardLauncherIconView extends KswGsUg1920And1280BaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnDashBoard;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.ksw_common_gs_ug_1920_c2_dashboard;
    }

    public KswGsUg1920And1280DashBoardLauncherIconView(Context context) {
        super(context);
    }

    public KswGsUg1920And1280DashBoardLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920And1280DashBoardLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_dash_board);
    }
}
