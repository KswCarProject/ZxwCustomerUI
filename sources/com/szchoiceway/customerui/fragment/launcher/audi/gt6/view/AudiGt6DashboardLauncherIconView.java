package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;

public class AudiGt6DashboardLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnDashBoard_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.dashBoard_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnDashBoard;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_dashboard_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 108;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return 19;
    }

    public AudiGt6DashboardLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6DashboardLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6DashboardLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        setMarginTop(getIconView(), 10);
        getIconTextView().setMaxWidth(getRealWidthSize(GyroScopeWithCompassView.CARTYPE_yinglang_LO_NEW));
    }
}
