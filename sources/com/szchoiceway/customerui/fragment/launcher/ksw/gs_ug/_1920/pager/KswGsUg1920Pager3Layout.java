package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280DashBoardLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280OriginalCarLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280SettingsLauncherIconView;

public class KswGsUg1920Pager3Layout extends KswGsUg1920BasePagerLayout {
    public KswGsUg1920Pager3Layout(Context context) {
        super(context);
    }

    public KswGsUg1920Pager3Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920Pager3Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public View getView1() {
        return new KswGsUg1920And1280OriginalCarLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView2() {
        return new KswGsUg1920And1280DashBoardLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView3() {
        return new KswGsUg1920And1280SettingsLauncherIconView(getContext());
    }
}
