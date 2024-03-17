package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.kt_like.JavaPair;

public class AudiGt6DVRLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnDvr_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.btnDvr_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnDvr;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_dvr_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 110;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return -10;
    }

    public AudiGt6DVRLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6DVRLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6DVRLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        setMarginTop(getIconView(), 10);
    }

    /* access modifiers changed from: protected */
    public JavaPair<Integer, Integer> getViewSize() {
        return new JavaPair<>(-1, 235);
    }
}
