package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.kt_like.JavaPair;

public class AudiGt6OriginalCarLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnOriginalCar_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.btnOriginalCar_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnOriginalCar;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_original_car_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 120;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return 20;
    }

    public AudiGt6OriginalCarLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6OriginalCarLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6OriginalCarLauncherIconView(Context context, AttributeSet attributeSet, int i) {
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
