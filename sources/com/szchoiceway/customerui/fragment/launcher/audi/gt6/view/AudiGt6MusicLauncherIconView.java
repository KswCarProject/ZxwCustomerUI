package com.szchoiceway.customerui.fragment.launcher.audi.gt6.view;

import android.content.Context;
import android.util.AttributeSet;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.kt_like.JavaPair;
import com.szchoiceway.zxwlib.GyroScopeWithCompassView;

public class AudiGt6MusicLauncherIconView extends BaseAudiGT6LauncherIconView {
    /* access modifiers changed from: protected */
    public int getIconTextFrameViewId() {
        return R.id.btnMusic_frame;
    }

    /* access modifiers changed from: protected */
    public int getIconTextViewId() {
        return R.id.btnMusic_tv;
    }

    /* access modifiers changed from: protected */
    public int getIconViewId() {
        return R.id.btnMusic;
    }

    /* access modifiers changed from: protected */
    public int getImageHeight() {
        return GyroScopeWithCompassView.CARTYPE_CRV2015_HI;
    }

    /* access modifiers changed from: protected */
    public int getImageWidth() {
        return 121;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.layout_audi_gt6_music_laucher_icon;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginStart() {
        return 115;
    }

    /* access modifiers changed from: protected */
    public int getTextMarginTop() {
        return 22;
    }

    public AudiGt6MusicLauncherIconView(Context context) {
        super(context);
    }

    public AudiGt6MusicLauncherIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AudiGt6MusicLauncherIconView(Context context, AttributeSet attributeSet, int i) {
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
