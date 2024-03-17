package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280BtLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280MusicLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280NaviLauncherIconView;

public class KswGsUg1920Pager1Layout extends KswGsUg1920BasePagerLayout {
    public KswGsUg1920Pager1Layout(Context context) {
        super(context);
    }

    public KswGsUg1920Pager1Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920Pager1Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public View getView1() {
        return new KswGsUg1920And1280NaviLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView2() {
        return new KswGsUg1920And1280BtLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView3() {
        return new KswGsUg1920And1280MusicLauncherIconView(getContext());
    }
}
