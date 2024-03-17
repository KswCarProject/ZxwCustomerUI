package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280BrowserLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280DVRLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1920.icon.KswGsUg1920And1280FileManagerLauncherIconView;

public class KswGsUg1920Pager4Layout extends KswGsUg1920BasePagerLayout {
    public KswGsUg1920Pager4Layout(Context context) {
        super(context);
    }

    public KswGsUg1920Pager4Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1920Pager4Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public View getView1() {
        return new KswGsUg1920And1280BrowserLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView2() {
        return new KswGsUg1920And1280DVRLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView3() {
        return new KswGsUg1920And1280FileManagerLauncherIconView(getContext());
    }
}
