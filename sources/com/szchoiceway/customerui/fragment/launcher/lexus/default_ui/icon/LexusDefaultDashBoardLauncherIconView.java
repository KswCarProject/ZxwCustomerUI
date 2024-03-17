package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class LexusDefaultDashBoardLauncherIconView extends LexusDefaultBaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnDashBoard;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.lexus_default_ui_main_icon_dashboard;
    }

    public LexusDefaultDashBoardLauncherIconView(Context context) {
        super(context);
    }

    public LexusDefaultDashBoardLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LexusDefaultDashBoardLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_dash_board);
    }
}
