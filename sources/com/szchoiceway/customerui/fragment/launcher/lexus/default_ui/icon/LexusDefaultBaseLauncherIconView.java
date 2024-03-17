package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.fragment.launcher.view.BaseLauncherIconView;

public abstract class LexusDefaultBaseLauncherIconView extends BaseLauncherIconView {
    private static final int DEFAULT_LAYOUT_ID = 1879834834;

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.lexus_default_item_launcher;
    }

    public LexusDefaultBaseLauncherIconView(Context context) {
        super(context);
    }

    public LexusDefaultBaseLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LexusDefaultBaseLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
