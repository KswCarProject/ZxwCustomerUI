package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.kt_like.JavaPair;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;

public class AudiGt6NaviLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnNavi_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.btnNavi_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnNavi;
    }

    /* access modifiers changed from: protected */
    public int getImageHeight() {
        return GyroScopeWithCompassView.CARTYPE_BYD_S6_HI;
    }

    /* access modifiers changed from: protected */
    public int getImageWidth() {
        return 124;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_navi_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 124;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return 22;
    }

    public AudiGt6NaviLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6NaviLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6NaviLauncherIconView(Context context, AttributeSet attributeSet, int i) {
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
