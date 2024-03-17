package com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024AppLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024CarAutoLauncherIconView;
import com.szchoiceway.customerui.fragment.launcher.ksw.gs_ug._1024.icon.KswGsUg1024VideoLauncherIconView;

public class KswGsUg1024Pager2Layout extends KswGsUg1024BasePagerLayout {
    public KswGsUg1024Pager2Layout(Context context) {
        super(context);
    }

    public KswGsUg1024Pager2Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KswGsUg1024Pager2Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public View getView1() {
        return new KswGsUg1024VideoLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView2() {
        return new KswGsUg1024AppLauncherIconView(getContext());
    }

    /* access modifiers changed from: protected */
    public View getView3() {
        return new KswGsUg1024CarAutoLauncherIconView(getContext());
    }
}
