package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024DashBoardLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024OriginalCarLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024SettingsLauncherIconView;

public class KswGsUg1024Pager3Layout extends KswGsUg1024BasePagerLayout {
    public KswGsUg1024Pager3Layout(Context context) {
        super(context);
    }

    public KswGsUg1024Pager3Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1024Pager3Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public View getView1() {
        return new KswGsUg1024OriginalCarLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView2() {
        return new KswGsUg1024DashBoardLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView3() {
        return new KswGsUg1024SettingsLauncherIconView(getContext());
    }
}
