package com.szchoiceway.customerui.fragment.launcher.lexus.default_ui.icon;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;

public class LexusDefaultMusicLauncherIconView extends LexusDefaultBaseLauncherIconView {
    /* access modifiers changed from: protected */
    public int getBackgroundImageViewId() {
        return R.id.btnMusic;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundResId() {
        return R.drawable.lexus_default_ui_main_icon_music;
    }

    public LexusDefaultMusicLauncherIconView(Context context) {
        super(context);
    }

    public LexusDefaultMusicLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LexusDefaultMusicLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public String getTile() {
        return getContext().getString(R.string.lb_music);
    }
}
