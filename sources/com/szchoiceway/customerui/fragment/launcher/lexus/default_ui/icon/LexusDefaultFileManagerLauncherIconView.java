package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class LexusDefaultFileManagerLauncherIconView extends LexusDefaultBaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnFile;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.lexus_default_ui_main_icon_file;
    }

    public LexusDefaultFileManagerLauncherIconView(Context context) {
        super(context);
    }

    public LexusDefaultFileManagerLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LexusDefaultFileManagerLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_gs_file_title);
    }
}
