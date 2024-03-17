package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.kt_like.JavaPair;

public class AudiGt6VideoLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnVideo_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.btnVideo_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnVideo;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_video_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 105;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return -2;
    }

    public AudiGt6VideoLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6VideoLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6VideoLauncherIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        setMarginTop(getIconView(), 17);
    }

    /* access modifiers changed from: protected */
    public JavaPair<Integer, Integer> getViewSize() {
        return new JavaPair<>(-1, 235);
    }
}
